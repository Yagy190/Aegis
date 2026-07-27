package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "disasters")
data class DisasterEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String,
    val category: String, // Wildfire, Flood, Cyclone, Earthquake, Drought, Landslide
    val severity: String, // Critical, High, Watch, Advisory
    val locationName: String,
    val coordinates: String,
    val timestamp: Long,
    val description: String,
    val affectedPopulation: String,
    val status: String, // Active, Mitigating, Resolved
    val dataSource: String, // NASA FIRMS, NOAA GOES, USGS, GDACS
    val confidenceScore: Int, // e.g., 94%
    val isOfflineSaved: Boolean = false
)
