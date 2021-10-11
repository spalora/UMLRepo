package com.bu.simplehealth.modules.mindgames.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bu.simplehealth.Util
import com.bu.simplehealth.database.SimpleHealthRepository
import com.bu.simplehealth.database.entity.MindGame
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MindGamesViewModel(private val repository: SimpleHealthRepository) : ViewModel() {

    private val _mindGames = MutableLiveData<List<MindGame>>()
    val mindGames: LiveData<List<MindGame>> = _mindGames
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)


    fun populateMindGames() {
        scope.launch {
            val mindGames: List<MindGame>? = repository.getMindGames()
            mindGames?.let {
                _mindGames.postValue(it)
            }
        }
    }
}

class MindGamesViewModelFactory(private val repository: SimpleHealthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MindGamesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MindGamesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
