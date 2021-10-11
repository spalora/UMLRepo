package com.bu.simplehealth.modules.conditions.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bu.simplehealth.Util
import com.bu.simplehealth.database.SimpleHealthRepository
import com.bu.simplehealth.database.entity.Condition
import com.bu.simplehealth.database.entity.UserConditionRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ConditionsViewModel(private val repository: SimpleHealthRepository) : ViewModel() {
    private val _conditions = MutableLiveData<HashMap<String, Boolean>>()
    val conditions: LiveData<HashMap<String, Boolean>> = _conditions
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)


    fun getConditions(user_name: String?) {
        scope.launch {
            var conditionNames: ArrayList<String?> = ArrayList()
            val condition: List<Condition>? = repository.getConditions()
            condition?.let {
                for (item in it) {

                    conditionNames.add(item.conditionName)

                }
                getSelectedConditions(user_name, conditionNames)
            }
        }
    }

    private fun getSelectedConditions(user_name: String?, conditionNames: ArrayList<String?>) {
        var selectedConditionNamesMap: HashMap<String, Boolean> = HashMap<String, Boolean>()
        for (cond in conditionNames) {
            selectedConditionNamesMap.put(cond.toString(), false)
        }
        scope.launch {
            val userConditions = repository.getUserConditions(user_name)
            userConditions?.let {
                for (cond in conditionNames) {
                    if(userConditions.contains(cond)){
                        selectedConditionNamesMap.put(cond.toString(), true)
                    }

                }
                _conditions.postValue(selectedConditionNamesMap)

            }
        }
    }



        fun insertUserConditions(userConditionRef: List<UserConditionRef>, user_name: String?) {
            scope.launch {
                repository.deleteUserConditions(user_name)
                repository.insertUserConditions(userConditionRef)
            }
        }

    }

    class ConditionsModelFactory(private val repository: SimpleHealthRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ConditionsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ConditionsViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }