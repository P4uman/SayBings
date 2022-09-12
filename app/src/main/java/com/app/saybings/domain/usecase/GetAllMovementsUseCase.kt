package com.app.saybings.domain.usecase

import com.app.saybings.data.database.MovementsRepository

class GetAllMovementsUseCase(private val repository: MovementsRepository) {

    suspend operator fun invoke() = repository.getAllMovements()
}