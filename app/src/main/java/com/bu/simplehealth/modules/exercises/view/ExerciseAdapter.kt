package com.bu.simplehealth.modules.exercises.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bu.simplehealth.R

class ExerciseAdapter(private val categories: List<String>) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    // holder class to hold reference
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(category: String, position: Int) {
            itemView.findViewById<AppCompatTextView>(R.id.exerciseName).text = category

            itemView.setOnClickListener {
                val bundle = bundleOf("category" to category)
                itemView.findNavController().navigate(R.id.action_exercisesFragment_to_exercisesDetailFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(categories[position], position)
    }

    override fun getItemCount(): Int = categories.size
}