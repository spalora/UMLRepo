package com.bu.simplehealth.modules.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.bu.simplehealth.MainActivity
import com.bu.simplehealth.R
import com.bu.simplehealth.SimpleHealthApplication
import com.bu.simplehealth.modules.home.viewmodel.HomeScreenViewModel

class HomeFragment : Fragment() {

    private val homeScreenViewModel: HomeScreenViewModel by viewModels {
        HomeScreenViewModel.HomeScreenViewModelFactory((activity?.application as SimpleHealthApplication).repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.supportActionBar?.show()
        (activity as MainActivity?)?.setTitle(getString(R.string.app_name))
        (activity as MainActivity?)?.setProfileIconVisibility(View.VISIBLE)
        (activity as MainActivity?)?.setBackButtonVisibility(View.GONE)
        (activity as MainActivity?)?.setSaveIconVisibility(View.GONE)
        (activity as MainActivity?)?.setLogoutIconVisibility(View.GONE)
        homeScreenViewModel.insertConditions()
        homeScreenViewModel.insertExercises()
        homeScreenViewModel.insertMindGames()
        view.findViewById<CardView>(R.id.medicationLayout).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_medicationFragment)
        }

        view.findViewById<CardView>(R.id.mindGamesLayout).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_mindGamesFragment)
        }

        view.findViewById<CardView>(R.id.conditionLayout).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_conditionsFragment)
        }

        view.findViewById<CardView>(R.id.exerciseLayout).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_exercisesFragment)
        }

        (activity as MainActivity?)?.setProfileClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_profileFragment)
        }


    }

}