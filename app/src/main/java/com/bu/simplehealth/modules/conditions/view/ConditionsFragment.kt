package com.bu.simplehealth.modules.conditions.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckedTextView
import android.widget.ListView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.bu.simplehealth.MainActivity
import com.bu.simplehealth.R
import com.bu.simplehealth.SharePreferenceData
import com.bu.simplehealth.SimpleHealthApplication
import com.bu.simplehealth.database.entity.UserConditionRef
import com.bu.simplehealth.modules.conditions.viewmodel.ConditionsModelFactory
import com.bu.simplehealth.modules.conditions.viewmodel.ConditionsViewModel
import kotlinx.android.synthetic.main.fragment_conditions.*
import java.util.ArrayList


class ConditionsFragment : Fragment() {
    var selectedConditionNames: HashMap<String, Int> = HashMap<String, Int>()
    var userConditionRefList: List<UserConditionRef> = ArrayList()

    private val conditionViewModel: ConditionsViewModel by viewModels {
        ConditionsModelFactory((activity?.application as SimpleHealthApplication).repository)
    }

    private var listview: ListView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_conditions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.setTitle(getString(R.string.conditions))
        (activity as MainActivity?)?.setProfileIconVisibility(View.GONE)
        (activity as MainActivity?)?.setBackButtonVisibility(View.VISIBLE)
        (activity as MainActivity?)?.setLogoutIconVisibility(View.GONE)
        (activity as MainActivity?)?.setBackClickListener {
            Navigation.findNavController(view).popBackStack()
        }
        (activity as MainActivity?)?.setSaveIconVisibility(View.VISIBLE)
        listview = view.findViewById(R.id.conditionsList)
        val loggedInUser = SharePreferenceData.getSharedPrefString(context, "user", "")
        conditionViewModel.getConditions(loggedInUser)
        conditionViewModel.conditions.observe(viewLifecycleOwner, { list ->
            val adapter = context?.let { ArrayAdapter(it, android.R.layout.simple_list_item_multiple_choice, android.R.id.text1, list.keys.toList()) }
            listview?.adapter = adapter
            var pos = 0;
            for ((key, value) in list) {
                if (value) {
                    listview?.setItemChecked(pos, true)
                    selectedConditionNames.put(key, pos)
                } else {
                    listview?.setItemChecked(pos, false)
                }
                pos++
            }

        }
        )


        listview?.setOnItemClickListener { adapterView, view, i, l ->
            val checkedTv: CheckedTextView = view.findViewById<CheckedTextView>(android.R.id.text1)
            if (checkedTv.isChecked) {
                selectedConditionNames.put(checkedTv.text.toString(), i + 1)
            } else {
                selectedConditionNames.remove(checkedTv.text.toString())
            }
        }
        (activity as MainActivity?)?.setSaveClickListener{
            userConditionRefList.drop(userConditionRefList.size)
            val loggedInUser = SharePreferenceData.getSharedPrefString(context, "user", "")
            for (key in selectedConditionNames.keys) {
                if (loggedInUser != null) {
                    val userConditionRef = UserConditionRef(key, loggedInUser)
                    userConditionRefList += userConditionRef
                }
            }
            conditionViewModel.insertUserConditions(userConditionRefList, loggedInUser)
            Navigation.findNavController(view).popBackStack()
        }


    }
}