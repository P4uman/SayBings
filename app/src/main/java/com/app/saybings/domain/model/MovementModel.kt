package com.app.saybings.domain.model

import com.app.saybings.data.database.entities.MovementEntityDB

data class MovementModel(
    val movement: Float,
    val type: MovementType
)

fun MovementModel.toData() =
    MovementEntityDB(
        movement = movement,
        type = type.value
    )


enum class MovementType(val value: Int) {
    INCOME(0),
    EXPENDING(1);

    companion object {
        fun fromInt(type: Int) = values().first { it.value == type }
    }
}