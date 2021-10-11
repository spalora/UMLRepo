package com.bu.simplehealth.modules.exercises.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bu.simplehealth.MainActivity
import com.bu.simplehealth.R
import com.bu.simplehealth.SimpleHealthApplication
import com.bu.simplehealth.modules.exercises.viewmodel.ExerciseViewModel
import com.bu.simplehealth.modules.exercises.viewmodel.ExerciseViewModelFactory
import kotlinx.android.synthetic.main.fragment_forgot_password.*


class ExerciseDetailFragment : Fragment() {

    private val exerciseViewModel: ExerciseViewModel by viewModels {
        ExerciseViewModelFactory((activity?.application as SimpleHealthApplication).repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.supportActionBar?.show()
        (activity as MainActivity?)?.setProfileIconVisibility(View.GONE)
        (activity as MainActivity?)?.setBackButtonVisibility(View.VISIBLE)
        (activity as MainActivity?)?.setLogoutIconVisibility(View.GONE)

        val category = arguments?.getString("category")
        (activity as MainActivity?)?.setBackClickListener {
            try {
                Navigation.findNavController(view).popBackStack()
            } catch (e: Exception) {
                Log.d("Exception", e.printStackTrace().toString())
            }
        }
        (activity as MainActivity?)?.setTitle(category)
        val recyclerView = view.findViewById<RecyclerView>(R.id.exerciseRv)
        var adapter: ExerciseDetailAdapter

        exerciseViewModel.exercises.observe(viewLifecycleOwner, {
            adapter = ExerciseDetailAdapter(it)
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
        })

        if (category != null) {
            exerciseViewModel.populateExercises(category)
        }
    }
}