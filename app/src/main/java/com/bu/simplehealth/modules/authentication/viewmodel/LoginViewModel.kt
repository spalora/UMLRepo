package com.bu.simplehealth.modules.authentication.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bu.simplehealth.database.SimpleHealthRepository
import com.bu.simplehealth.database.entity.User
import com.bu.simplehealth.model.Account
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class LoginViewModel(private val repository: SimpleHealthRepository) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    /**
     * Called from activity on login button click
     */
    @VisibleForTesting
    fun performValidation(account: Account): String {

        if (account.userName.isBlank() || account.password.isBlank()) {
            return "Please complete all required fields"
        }
        return if (performStrengthValidation(account.password)) {
            ""
        } else {
            "Password must contain 1 number"
        }
    }

    @VisibleForTesting
    fun performStrengthValidation(password: String): Boolean {
        val regex = Regex(".*[0-9].*")
        return password.matches(regex)
    }

    fun loginUser(username: String) {
        scope.launch {
            _user.postValue(repository.getUser(username))
        }
    }

}

class LoginViewModelFactory(private val repository: SimpleHealthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}