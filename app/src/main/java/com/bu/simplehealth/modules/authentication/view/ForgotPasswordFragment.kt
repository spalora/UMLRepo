package com.bu.simplehealth.modules.authentication.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.bu.simplehealth.R
import com.bu.simplehealth.SimpleHealthApplication
import com.bu.simplehealth.forgot_password.ForgotPasswordViewModel
import com.bu.simplehealth.forgot_password.ForgotPasswordViewModelFactory
import com.bu.simplehealth.model.Account
import kotlinx.android.synthetic.main.fragment_forgot_password.*

import com.bu.simplehealth.MainActivity


class ForgotPasswordFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }

    private val forgotPasswordViewModel: ForgotPasswordViewModel by viewModels {
        ForgotPasswordViewModelFactory((activity?.application as SimpleHealthApplication).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.setTitle(getString(R.string.forgot_password))
        (activity as MainActivity?)?.supportActionBar?.show()
        (activity as MainActivity?)?.setProfileIconVisibility(View.GONE)
        (activity as MainActivity?)?.setSaveIconVisibility(View.GONE)
        (activity as MainActivity?)?.setLogoutIconVisibility(View.GONE)
        reset_btn?.setOnClickListener {
            val user = Account(fp_username.text.toString(), fp_password.text.toString(), fp_confirmPasswordEt.text.toString())
            val message = forgotPasswordViewModel.performValidation(user)
            if (message.isNotEmpty()) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            } else {
                forgotPasswordViewModel.performPasswordReset(user)
            }
        }
        (activity as MainActivity?)?.setBackClickListener {
            try {
                Navigation.findNavController(view).popBackStack()
            } catch (e: Exception) {
                Log.d("Exception", e.printStackTrace().toString())
            }
        }

        forgotPasswordViewModel.userUpdate.observe(viewLifecycleOwner, {
            if (it == 1) {
                Toast.makeText(context, getString(R.string.password_updated), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, getString(R.string.enter_valid_username_password), Toast.LENGTH_SHORT).show()
            }
        })
    }
}