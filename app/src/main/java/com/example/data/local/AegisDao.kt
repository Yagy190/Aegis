package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AegisDao {
    @Query("SELECT * FROM disasters ORDER BY timestamp DESC")
    fun getAllDisasters(): Flow<List<DisasterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDisaster(disaster: DisasterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDisasters(disasters: List<DisasterEntity>)

    @Query("UPDATE disasters SET status = :status WHERE id = :id")
    suspend fun updateDisasterStatus(id: Long, status: String)

    @Query("SELECT * FROM responder_tasks ORDER BY urgencyRank ASC")
    fun getAllResponderTasks(): Flow<List<ResponderTaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResponderTask(task: ResponderTaskEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResponderTasks(tasks: List<ResponderTaskEntity>)

    @Query("UPDATE responder_tasks SET status = :status WHERE id = :id")
    suspend fun updateResponderTaskStatus(id: Long, status: String)
}
