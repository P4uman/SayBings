package com.app.saybings.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.saybings.domain.model.MovementModel
import com.app.saybings.domain.model.MovementType

@Entity(tableName = "movement_table")
data class MovementEntityDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "movementType") val type: Int,
    @ColumnInfo(name = "movement") val movement: Float
    )

fun MovementEntityDB.toDomain() =
    MovementModel(
        movement= movement,
        type = MovementType.fromInt(type)
    )