package com.bu.simplehealth.modules.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bu.simplehealth.Util
import com.bu.simplehealth.database.SimpleHealthRepository
import com.bu.simplehealth.database.entity.Condition
import com.bu.simplehealth.database.entity.Exercise
import com.bu.simplehealth.database.entity.MindGame
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeScreenViewModel (private val repository: SimpleHealthRepository): ViewModel() {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    /**
     * This methos is to insert exercise  on app start, ontime insertion.
     * This method should go to exercise View model ::Placing here for initial testing
     */
    fun insertConditions() {
        scope.launch {
            var condition: List<Condition>? = repository.getConditions()
            /*For this app, first time installation inserting alerts into DB*/
            if (condition?.isEmpty() == true) {
                repository.insertConditions(Util.insertConditions());
                condition = repository.getConditions();
            }
        }
    }

    /**
     * This methos is to insert mind game on app start, ontime insertion.
     * This method should go to mindgame View model ::Placing here for initial testing
     */
    fun insertMindGames() {
        scope.launch {
            var mindgames: List<MindGame>? = repository.getMindGames()
            /*For this app, first time installation inserting alerts into DB*/
            if (mindgames?.isEmpty() == true) {
                repository.insertMindGameData(Util.insertMindGames());
                mindgames = repository.getMindGames();
            }
        }
    }

    /**
     * This methos is to insert exercise  on app start, ontime insertion.
     * This method should go to exercise View model ::Placing here for initial testing
     */
    fun insertExercises() {
        scope.launch {
            var exercise: List<Exercise>? = repository.getExercises()
            /*For this app, first time installation inserting alerts into DB*/
            if (exercise?.isEmpty() == true) {
                repository.insertExercises(Util.insertExercises());
                exercise = repository.getExercises();
            }
        }
    }

    class HomeScreenViewModelFactory(private val repository: SimpleHealthRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeScreenViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}