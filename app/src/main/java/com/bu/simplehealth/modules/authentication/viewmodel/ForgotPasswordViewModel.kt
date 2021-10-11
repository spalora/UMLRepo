package com.bu.simplehealth.forgot_password

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bu.simplehealth.Util
import com.bu.simplehealth.database.SimpleHealthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.security.NoSuchAlgorithmException
import kotlin.coroutines.CoroutineContext

open class ForgotPasswordViewModel(private val repository: SimpleHealthRepository) : ViewModel() {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)
    private val _userUpdate = MutableLiveData<Int>()
    val userUpdate: LiveData<Int> = _userUpdate

    //TODO:Seema: Place this method into common class to reuse from both reset password and signup flow.
    @VisibleForTesting
    fun performValidation(account : com.bu.simplehealth.model.Account): String {
        if (account.userName.isNullOrEmpty() || account.password.isNullOrEmpty() || account.confirmPassword.isNullOrEmpty()) {
            return "Please enter all required fields"
        } else if (!account.password.contentEquals(account.confirmPassword)) {
            return "Password and confirm password much match"
        }
        return ""
    }

    fun performPasswordReset(account : com.bu.simplehealth.model.Account) {
        scope.launch {
            if (repository.getUser(account.userName) == null) {
                _userUpdate.postValue(0)
                return@launch
            } else {
                var salt: ByteArray? = ByteArray(0)
                try {
                    salt = Util.getSalt()
                } catch (e: NoSuchAlgorithmException) {
                    e.printStackTrace()
                }
                val securePwd = Util.getSecurePwd(account.password, salt)
                (repository.updatePassword(securePwd, account.userName,salt))
                _userUpdate.postValue(repository.updatePassword(securePwd, account.userName,salt))
                return@launch
            }
        }
    }

}

class ForgotPasswordViewModelFactory(private val repository: SimpleHealthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForgotPasswordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ForgotPasswordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}