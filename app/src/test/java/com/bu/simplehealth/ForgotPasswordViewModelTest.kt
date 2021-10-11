package com.bu.simplehealth

import com.bu.simplehealth.forgot_password.ForgotPasswordViewModel
import com.bu.simplehealth.model.Account
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ForgotPasswordViewModelTest : TestCase() {


    private lateinit var viewModelTest: ForgotPasswordViewModel

    @Before
    fun setupBase() {
        viewModelTest = mock(ForgotPasswordViewModel::class.java)
    }

    @Test
    fun `test empty username`() {
        val user = Account("", "password", "password")
        val value = viewModelTest.performValidation(user)
        assertEquals("Please enter all required fields", value)
    }

    @Test
    fun `test empty password`() {
        val user = Account("username", "", "password")
        val value = viewModelTest.performValidation(user)
        assertEquals("Please enter all required fields", value)
    }

    @Test
    fun `test password not match`() {
        val user = Account("username", "password1", "password")
        val value = viewModelTest.performValidation(user)
        assertEquals("Password and confirm password much match", value)
    }

    @Test
    fun `test all correct fields`() {
        val user = Account("username", "password", "password")
        val value = viewModelTest.performValidation(user)
        assertEquals("", value)
    }

}