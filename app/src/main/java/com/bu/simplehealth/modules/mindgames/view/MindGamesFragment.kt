package com.bu.simplehealth.modules.mindgames.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bu.simplehealth.MainActivity
import com.bu.simplehealth.R
import com.bu.simplehealth.SimpleHealthApplication
import com.bu.simplehealth.modules.mindgames.viewmodel.MindGamesViewModel
import com.bu.simplehealth.modules.mindgames.viewmodel.MindGamesViewModelFactory
import kotlinx.android.synthetic.main.fragment_forgot_password.*

class MindGamesFragment : Fragment() {

    private val mindGamesViewModel: MindGamesViewModel by viewModels {
        MindGamesViewModelFactory((activity?.application as SimpleHealthApplication).repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mind_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.supportActionBar?.show()
        (activity as MainActivity?)?.setTitle(getString(R.string.mind_games))
        (activity as MainActivity?)?.setProfileIconVisibility(View.GONE)
        (activity as MainActivity?)?.setBackButtonVisibility(View.VISIBLE)
        (activity as MainActivity?)?.setLogoutIconVisibility(View.GONE)
        var adapter: MindGamesAdapter

        val recyclerView = view.findViewById<RecyclerView>(R.id.mindGamesRv)

        mindGamesViewModel.mindGames.observe(viewLifecycleOwner, {
            adapter = MindGamesAdapter(it)
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
        })

        (activity as MainActivity?)?.setBackClickListener {
            try {
                Navigation.findNavController(view).popBackStack()
            } catch (e: Exception) {
                Log.d("Exception", e.printStackTrace().toString())
            }
        }

        mindGamesViewModel.populateMindGames()
    }
}