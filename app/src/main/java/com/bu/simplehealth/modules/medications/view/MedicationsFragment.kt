package com.bu.simplehealth.modules.medications.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.bu.simplehealth.MainActivity
import com.bu.simplehealth.R
import com.bu.simplehealth.SharePreferenceData
import com.bu.simplehealth.SimpleHealthApplication
import com.bu.simplehealth.database.entity.Medication
import com.bu.simplehealth.modules.medications.viewmodel.MedicationsViewModel
import kotlinx.android.synthetic.main.fragment_medications.*

/**
 * @author Seema Palora
 */
class MedicationsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_medications, container, false)
    }

    private val medicationsViewModel: MedicationsViewModel by viewModels {
        MedicationsViewModel.MedicationsModelFactory((activity?.application as SimpleHealthApplication).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.setTitle(getString(R.string.medications))
        (activity as MainActivity?)?.setProfileIconVisibility(View.GONE)
        (activity as MainActivity?)?.setBackButtonVisibility(View.VISIBLE)
        (activity as MainActivity?)?.setSaveIconVisibility(View.VISIBLE)
        (activity as MainActivity?)?.setLogoutIconVisibility(View.GONE)
        (activity as MainActivity?)?.setBackClickListener{
            Navigation.findNavController(view).popBackStack()
        }
        var selectedStrength:RadioButton
        var strength:String=""
        var frequency:String=""
        strength_radio_grp?.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
             selectedStrength = view.findViewById(checkedId)
            strength =selectedStrength.text.toString()

        }
        )
        var selectedFrequency:RadioButton
        frequency_radio_grp?.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
             selectedFrequency = view.findViewById(checkedId)
            frequency =selectedFrequency.text.toString()

        }
        )
        (activity as MainActivity?)?.setSaveClickListener{
            val medicationName=medication_name.text.toString()
            val loggedInUser = SharePreferenceData.getSharedPrefString(context, "user", "")
            if (loggedInUser != null) {
                val medications = Medication(medicationName, strength,frequency, loggedInUser)
                medicationsViewModel.saveMedications(medications)
            }

        }

        medicationsViewModel.medications.observe(viewLifecycleOwner, {
            if (it != null) {
                activity?.runOnUiThread {
                        Toast.makeText(context, "Medication data saved successfully.", Toast.LENGTH_SHORT).show()
                    }

            } else {
                Toast.makeText(context, "Error saving medication data,Please try again later.", Toast.LENGTH_SHORT).show()
            }
            Navigation.findNavController(view).popBackStack()
        })
    }



}