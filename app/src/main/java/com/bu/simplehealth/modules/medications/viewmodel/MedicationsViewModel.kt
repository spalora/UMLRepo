package com.bu.simplehealth.modules.medications.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bu.simplehealth.database.SimpleHealthRepository
import com.bu.simplehealth.database.entity.Medication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * @author Seema Palora
 */
class MedicationsViewModel(private val repository: SimpleHealthRepository) : ViewModel() {
    private val _medications = MutableLiveData<Medication?>()
    val medications: LiveData<Medication?> = _medications
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    fun saveMedications(medication:Medication) {
        scope.launch {
             repository.insertUserMedicationData(medication)
            _medications.postValue(repository.getUserMedication(medication.name))
            }

    }

    class MedicationsModelFactory(private val repository: SimpleHealthRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MedicationsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MedicationsViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}