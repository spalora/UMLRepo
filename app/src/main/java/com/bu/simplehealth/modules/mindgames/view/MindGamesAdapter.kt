package com.bu.simplehealth.modules.mindgames.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bu.simplehealth.R
import com.bu.simplehealth.database.entity.MindGame

class MindGamesAdapter(private val mindGames: List<MindGame>) : RecyclerView.Adapter<MindGamesAdapter.ViewHolder>() {

    // holder class to hold reference
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(mindGame: MindGame, position: Int) {
            itemView.findViewById<AppCompatTextView>(R.id.exerciseName).text = mindGame.gameName

            itemView.setOnClickListener {
                val bundle = bundleOf("data" to mindGame.gameUrl)
                itemView.findNavController().navigate(R.id.action_mindGamesFragment_to_webViewFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(mindGames[position], position)
    }

    override fun getItemCount(): Int = mindGames.size
}