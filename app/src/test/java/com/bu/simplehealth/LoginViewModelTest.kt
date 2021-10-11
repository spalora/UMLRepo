package com.bu.simplehealth

import com.bu.simplehealth.model.Account
import com.bu.simplehealth.modules.authentication.viewmodel.LoginViewModel
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LoginViewModelTest : TestCase() {


    private lateinit var viewModelTest: LoginViewModel

    @Before
    fun setupBase() {
        viewModelTest = mock(LoginViewModel::class.java)
    }

    @Test
    fun `test empty username`() {
        val account = Account()
        account.userName = ""
        account.password = "password123"
        val value = viewModelTest.performValidation(account)
        assertEquals("Please complete all required fields", value)
    }

    @Test
    fun `test empty password`() {
        val account = Account()
        account.userName = "username"
        account.password = ""
        val value = viewModelTest.performValidation(account)
        assertEquals("Please complete all required fields", value)
    }

    @Test
    fun `test all correct fields`() {
        val account = Account()
        account.userName = "username"
        account.password = "password123"
        val value = viewModelTest.performValidation(account)
        assertEquals("", value)
    }

    @Test
    fun `test password strength`() {
        val value = viewModelTest.performStrengthValidation("password123")
        assertEquals(true, value)
    }


}