package com.bu.simplehealth.modules.authentication.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bu.simplehealth.Util
import com.bu.simplehealth.database.SimpleHealthRepository
import com.bu.simplehealth.database.entity.User
import com.bu.simplehealth.model.Account
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.security.NoSuchAlgorithmException
import kotlin.coroutines.CoroutineContext

open class SignUpViewModel(private val repository: SimpleHealthRepository) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    @VisibleForTesting
    fun performValidation(account: Account): String {
        return if (account.fullName.isEmpty() || account.userName.isEmpty() || account.email.isEmpty() || account.dob.isEmpty() || account.password.isEmpty() || account.confirmPassword.isEmpty()) {
            "Please enter all required fields"
        } else if (!account.password.contentEquals(account.confirmPassword)) {
            "Password and confirm password much match"
        } else if (!performStrengthValidation(account.password))
            "Password must contain 1 number"
        else {
            ""
        }
    }

    @VisibleForTesting
    fun performStrengthValidation(password: String): Boolean {
        val regex = Regex(".*[0-9].*")
        return password.matches(regex)
    }

    fun performSignUp(user: Account) {
        scope.launch {
            if (repository.getUser(user.userName) != null) {
                _user.postValue(null)
                return@launch
            } else {
                var salt = ByteArray(0)
                try {
                    salt = Util.getSalt()
                } catch (e: NoSuchAlgorithmException) {
                    e.printStackTrace()
                }
                val securePwd = Util.getSecurePwd(user.password, salt)
                (repository.insertUserData(User(user.userName, user.fullName, securePwd, user.email, user.dob, salt)))
                _user.postValue(repository.getUser(user.userName))
                return@launch
            }
        }
    }
}

class SignUpViewModelFactory(private val repository: SimpleHealthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SignUpViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}