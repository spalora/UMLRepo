package com.bu.simplehealth.modules.exercises.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bu.simplehealth.R
import com.bu.simplehealth.database.entity.Exercise

class ExerciseDetailAdapter(private val exercises: List<Exercise>) : RecyclerView.Adapter<ExerciseDetailAdapter.ViewHolder>() {

    // holder class to hold reference
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(exercise: Exercise, position: Int) {
            itemView.findViewById<AppCompatTextView>(R.id.exerciseName).text = exercise.exerciseName

            itemView.setOnClickListener {
                val bundle = bundleOf("data" to exercise.exerciseUrl)
                itemView.findNavController().navigate(R.id.action_exerciseDetailFragment_to_webViewFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(exercises[position], position)
    }

    override fun getItemCount(): Int = exercises.size
}