package com.app.saybings.data.database

import com.app.saybings.data.database.dao.MovementDao
import com.app.saybings.data.database.entities.toDomain
import com.app.saybings.domain.model.MovementModel
import com.app.saybings.domain.model.toData

class MovementsRepository(
    private val movementDao: MovementDao
) {
    suspend fun getAllMovements(): List<MovementModel> =
        movementDao.getAllMovements().map { it.toDomain() }

    suspend fun insertMovement(movement: MovementModel) {
        movementDao.insertMovement(movement.toData())
    }
}