package com.app.saybings.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.saybings.data.database.entities.MovementEntityDB

@Dao
interface MovementDao {

    @Query("SELECT * FROM movement_table ORDER BY id DESC")
    suspend fun getAllMovements(): List<MovementEntityDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovement(movement: MovementEntityDB)
}