package com.bu.simplehealth.modules.authentication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.bu.simplehealth.*
import com.bu.simplehealth.model.Account
import com.bu.simplehealth.modules.authentication.viewmodel.LoginViewModel
import com.bu.simplehealth.modules.authentication.viewmodel.LoginViewModelFactory
import com.bu.simplehealth.modules.authentication.viewmodel.SignUpViewModel
import com.bu.simplehealth.modules.authentication.viewmodel.SignUpViewModelFactory
import kotlinx.android.synthetic.main.fragment_login_signup.*
import kotlinx.android.synthetic.main.login_screen.*
import kotlinx.android.synthetic.main.signup_screen.*

/**
 * @author Sandeep Agrawal
 * Fragment to handle signin and signup
 */

class LoginSignUpFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory((activity?.application as SimpleHealthApplication).repository)
    }

    private val signupViewModel: SignUpViewModel by viewModels {
        SignUpViewModelFactory((activity?.application as SimpleHealthApplication).repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.supportActionBar?.hide()
        sign_up?.setOnClickListener { signup ->

            context?.let {
                signup.setBackgroundColor(ContextCompat.getColor(it, android.R.color.white))
                sign_up_tv.setTextColor(ContextCompat.getColor(it, android.R.color.black))
                if (signup_view?.visibility == View.GONE) {
                    signup_view?.visibility = View.VISIBLE
                }

                sign_in.setBackgroundColor(ContextCompat.getColor(it, R.color.secondaryLightColor))
                sign_in_tv.setTextColor(ContextCompat.getColor(it, R.color.secondaryColor))
                login_view?.visibility = View.GONE
            }
            signup.isEnabled = false
            sign_in.isEnabled = true
        }

        sign_in?.setOnClickListener { signin ->

            context?.let {
                signin.setBackgroundColor(ContextCompat.getColor(it, android.R.color.white))
                sign_in_tv.setTextColor(ContextCompat.getColor(it, android.R.color.black))
                if (login_view?.visibility == View.GONE) {
                    login_view?.visibility = View.VISIBLE
                }

                sign_up.setBackgroundColor(ContextCompat.getColor(it, R.color.secondaryLightColor))
                sign_up_tv.setTextColor(ContextCompat.getColor(it, R.color.secondaryColor))
                signup_view?.visibility = View.GONE
            }
            signin.isEnabled = false
            sign_up.isEnabled = true
        }

        sign_in_btn?.setOnClickListener {
            val user = Account(usernameEt.text.toString(),passwordEt.text.toString())
            if ((loginViewModel.performValidation(user).isNotEmpty())) {
                Toast.makeText(context, loginViewModel.performValidation(user), Toast.LENGTH_SHORT).show()
            } else {
                loginViewModel.loginUser(usernameEt.text.toString())
            }
        }

        loginViewModel.user.observe(viewLifecycleOwner, {
            if (it != null) {
                activity?.runOnUiThread {
                    if (remember_checkbox.isChecked) {
                        SharePreferenceData.setSharedPrefString(context, "user", it.userName)
                    }
                    val securePwd = Util.getSecurePwd(passwordEt.text.toString(), it.salt)
                    if (securePwd.equals(it.password)) {
                        SharePreferenceData.setSharedPrefString(context, "user", it.userName)
                        Navigation.findNavController(view).navigate(R.id.action_loginSignupFragment_to_homeFragment)
                    } else {
                        Toast.makeText(context, "Please correct username or password", Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                Toast.makeText(context, "Please correct username or password", Toast.LENGTH_SHORT).show()
            }
        })

        sign_up_btn.setOnClickListener {
            val user = Account(username.text.toString(),password.text.toString(),confirmPasswordEt.text.toString(),fullNameEt.text.toString(),emailEt.text.toString(),dobEt.text.toString())
            val message = signupViewModel.performValidation(user)
             if (message.isNotEmpty()) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            } else {
                signupViewModel.performSignUp(user)
            }
        }

        signupViewModel.user.observe(viewLifecycleOwner, {
            if (it == null) {
                Toast.makeText(context, "User " + "'" + username.text.toString() + "'" + " already exists", Toast.LENGTH_SHORT).show()
            } else {
                hideKeyboard()
                SharePreferenceData.setSharedPrefString(context, "user", it.userName)
                Navigation.findNavController(view).navigate(R.id.action_loginSignupFragment_to_homeFragment)
            }
        })

        remember_me_tv?.setOnClickListener {
            remember_checkbox.isChecked = remember_checkbox.isChecked != true
        }

        forgot_password_btn?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginSignupFragment_to_forgotPasswordFragment);
        }
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

}