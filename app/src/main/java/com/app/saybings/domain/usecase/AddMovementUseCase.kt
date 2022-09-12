package com.app.saybings.domain.usecase

import com.app.saybings.data.database.MovementsRepository
import com.app.saybings.domain.model.MovementModel

class AddMovementUseCase(private val repository: MovementsRepository) {

    suspend operator fun invoke(movement: MovementModel) {
        repository.insertMovement(movement)
    }
}