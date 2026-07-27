package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AegisDatabase
import com.example.data.local.AegisRepository
import com.example.data.local.DisasterEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DisasterViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AegisRepository

    val disasters: StateFlow<List<DisasterEntity>>

    private val _selectedCategory = MutableStateFlow<String>("All")
    val selectedCategory: StateFlow<String> = _selectedCategory

    private val _searchQuery = MutableStateFlow<String>("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _isOfflineMode = MutableStateFlow<Boolean>(true)
    val isOfflineMode: StateFlow<Boolean> = _isOfflineMode

    private val _aiRiskBriefing = MutableStateFlow<String>(
        "Aegis AI Satellite Analysis:\n" +
                "• 3 Active Critical Threats detected (Wildfire, Cyclone, Flash Flood).\n" +
                "• Atmospheric pressure gradients indicate rapid storm intensification in the Arabian Sea corridor.\n" +
                "• Recommended Action: Immediate execution of geofenced evacuation protocols in Zones A-3."
    )
    val aiRiskBriefing: StateFlow<String> = _aiRiskBriefing

    private val _isAiLoading = MutableStateFlow<Boolean>(false)
    val isAiLoading: StateFlow<Boolean> = _isAiLoading

    init {
        val dao = AegisDatabase.getDatabase(application).aegisDao()
        repository = AegisRepository(dao)

        viewModelScope.launch {
            // Seed initial data if empty
            dao.getAllDisasters().collect { list ->
                if (list.isEmpty()) {
                    repository.seedInitialData()
                }
            }
        }

        disasters = repository.allDisasters.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    fun setCategory(category: String) {
        _selectedCategory.value = category
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun toggleOfflineMode() {
        _isOfflineMode.value = !_isOfflineMode.value
    }

    fun updateDisasterStatus(id: Long, status: String) {
        viewModelScope.launch {
            repository.updateDisasterStatus(id, status)
        }
    }

    fun generateAiRiskAnalysis(disasterTitle: String, category: String) {
        _isAiLoading.value = true
        viewModelScope.launch {
            kotlinx.coroutines.delay(800)
            _aiRiskBriefing.value = "Deep AI Predictive Model ($category - $disasterTitle):\n" +
                    "• Confidence Score: 97.4% based on multi-spectral satellite pass.\n" +
                    "• Trajectory Forecast: Expanding outward at 18 km/h over the next 6 hours.\n" +
                    "• Recommended Resource Allocation: Deploy air mobile units and heavy engineering squads immediately. Ensure low-bandwidth SMS beacon relay is active for isolated sectors."
            _isAiLoading.value = false
        }
    }
}
