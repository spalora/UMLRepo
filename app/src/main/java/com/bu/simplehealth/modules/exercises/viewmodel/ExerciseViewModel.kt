package com.bu.simplehealth.modules.exercises.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bu.simplehealth.Util
import com.bu.simplehealth.database.SimpleHealthRepository
import com.bu.simplehealth.database.entity.Exercise
import com.bu.simplehealth.database.entity.User
import com.bu.simplehealth.modules.authentication.viewmodel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ExerciseViewModel(private val repository: SimpleHealthRepository) : ViewModel() {

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>> = _categories

    private val _exercises = MutableLiveData<List<Exercise>>()
    val exercises: LiveData<List<Exercise>> = _exercises

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    fun populateExerciseCategories() {
        scope.launch {
            val exercise: List<String>? = repository.getExerciseCategories()
            exercise?.let {
                _categories.postValue(it)
            }
        }
    }

    fun populateExercises(category: String) {
        scope.launch {
            val exercise: List<Exercise>? = repository.getExercisesCategories(category)
            exercise?.let {
                _exercises.postValue(it)
            }
        }
    }
}


class ExerciseViewModelFactory(private val repository: SimpleHealthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExerciseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}