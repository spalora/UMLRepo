package com.bu.simplehealth.modules.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bu.simplehealth.MainActivity
import com.bu.simplehealth.R
import com.bu.simplehealth.SharePreferenceData

class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.setTitle(getString(R.string.my_profile))
        (activity as MainActivity?)?.setProfileIconVisibility(View.GONE)
        (activity as MainActivity?)?.setBackButtonVisibility(View.VISIBLE)
        (activity as MainActivity?)?.setSaveIconVisibility(View.GONE)
        (activity as MainActivity?)?.setLogoutIconVisibility(View.VISIBLE)
        (activity as MainActivity?)?.setBackClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        (activity as MainActivity?)?.setLogoutClickListener {
            SharePreferenceData.clearAllPreference(context)
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }

}