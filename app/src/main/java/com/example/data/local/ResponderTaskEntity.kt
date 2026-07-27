package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "responder_tasks")
data class ResponderTaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String,
    val urgencyRank: Int, // 1 (Highest) to 5
    val location: String,
    val assignedTeam: String,
    val status: String, // Dispatched, En Route, On Site, Completed
    val details: String,
    val timestamp: Long
)
