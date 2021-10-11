package com.bu.simplehealth

import com.bu.simplehealth.model.Account
import com.bu.simplehealth.modules.authentication.viewmodel.SignUpViewModel
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class SignupViewModelTest : TestCase() {

    private lateinit var viewModelTest: SignUpViewModel

    @Before
    fun setupBase() {
        viewModelTest = mock(SignUpViewModel::class.java)
    }

    @Test
    fun `test empty name`() {
        val account = Account()
        account.userName = "sagrawal2418"
        account.email = "sandeepagrawal2418@gmail.com"
        account.dob = "01/11/1911"
        account.password = "password"
        account.confirmPassword = "password"
        val value = viewModelTest.performValidation(account)
        assertEquals("Please enter all required fields", value)
    }

    @Test
    fun `test empty username`() {
        val account = Account()
        account.fullName = "Sandeep Agrawal"
        account.userName = ""
        account.email = "sandeepagrawal2418@gmail.com"
        account.dob = "01/11/1911"
        account.password = "password"
        account.confirmPassword = "password"
        val value = viewModelTest.performValidation(account)
        assertEquals("Please enter all required fields", value)
    }

    @Test
    fun `test empty email`() {
        val account = Account()
        account.fullName = "Sandeep Agrawal"
        account.userName = ""
        account.email = ""
        account.dob = "01/11/1911"
        account.password = "password"
        account.confirmPassword = "password"
        val value = viewModelTest.performValidation(account)
        assertEquals("Please enter all required fields", value)
    }

    @Test
    fun `test empty dob`() {
        val account = Account()
        account.fullName = "Sandeep Agrawal"
        account.userName = ""
        account.email = "sandeepagrawal2418@gmail.com"
        account.dob = ""
        account.password = "password1"
        account.confirmPassword = "password1"
        val value = viewModelTest.performValidation(account)
        assertEquals("Please enter all required fields", value)
    }

    @Test
    fun `test empty password`() {
        val account = Account()
        account.fullName = "Sandeep Agrawal"
        account.userName = ""
        account.email = "sandeepagrawal2418@gmail.com"
        account.dob = "01/11/1911"
        account.password = ""
        account.confirmPassword = "password1"
        val value = viewModelTest.performValidation(account)
        assertEquals("Please enter all required fields", value)
    }

    @Test
    fun `test password and confirm password must match`() {
        val account = Account()
        account.fullName = "Sandeep Agrawal"
        account.userName = "sagrawal2418"
        account.email = "sandeepagrawal2418@gmail.com"
        account.dob = "01/11/1911"
        account.password = "password"
        account.confirmPassword = "password1"
        val value = viewModelTest.performValidation(account)
        assertEquals("Password and confirm password much match", value)
    }

    @Test
    fun `test all correct fields`() {
        val account = Account()
        account.fullName = "Sandeep Agrawal"
        account.userName = "sagrawal2418"
        account.email = "sandeepagrawal2418@gmail.com"
        account.dob = "01/11/1911"
        account.password = "password1"
        account.confirmPassword = "password1"
        val value = viewModelTest.performValidation(account)
        assertEquals("", value)
    }

}