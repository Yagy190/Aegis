package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AegisDatabase
import com.example.data.local.AegisRepository
import com.example.data.local.ResponderTaskEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ResponderViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AegisRepository

    val responderTasks: StateFlow<List<ResponderTaskEntity>>

    init {
        val dao = AegisDatabase.getDatabase(application).aegisDao()
        repository = AegisRepository(dao)

        responderTasks = repository.allResponderTasks.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    fun updateTaskStatus(id: Long, status: String) {
        viewModelScope.launch {
            repository.updateResponderTaskStatus(id, status)
        }
    }

    fun addNewResponderTask(title: String, urgency: Int, location: String, team: String, details: String) {
        viewModelScope.launch {
            val task = ResponderTaskEntity(
                title = title,
                urgencyRank = urgency,
                location = location,
                assignedTeam = team,
                status = "Dispatched",
                details = details,
                timestamp = System.currentTimeMillis()
            )
            repository.insertResponderTask(task)
        }
    }
}
