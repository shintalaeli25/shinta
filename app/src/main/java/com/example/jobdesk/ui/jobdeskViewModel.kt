package com.example.jobdesk.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.jobdesk.model.Jobdesk
import com.example.jobdesk.repository.JobdeskRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class jobdeskViewModel(private val repository: JobdeskRepository): ViewModel() {
    val allJobdesk: LiveData<List<Jobdesk>> = liveData()

    private fun liveData() = repository.allJobdesk.asLiveData()

    fun insert(jobdesk: Jobdesk) = viewModelScope.launch {
        repository.insertJobdesk(jobdesk)
    }

    fun delete(jobdesk: Jobdesk) = viewModelScope.launch {
        repository.deletetJobdesk(jobdesk)
    }

    fun update(jobdesk: Jobdesk) = viewModelScope.launch {
        repository.updateJobdesk(jobdesk)
    }
}

class JobdeskViewModelFactory(private val repository: JobdeskRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((jobdeskViewModel::class.java))){
            return jobdeskViewModel(repository) as T
        }
        throw IllegalArgumentException("unknow ViewModelClass")
    }
}