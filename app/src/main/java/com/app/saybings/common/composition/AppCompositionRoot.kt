package com.app.saybings.common.composition

import android.content.Context
import androidx.annotation.UiThread
import androidx.room.Room
import com.app.saybings.data.database.MovementsRepository
import com.app.saybings.data.database.SayBingsDatabase
import com.app.saybings.domain.usecase.AddMovementUseCase
import com.app.saybings.domain.usecase.GetAllMovementsUseCase

@UiThread
class AppCompositionRoot(
    private val context: Context,
    private val databaseName: String
) {
    private val database: SayBingsDatabase by lazy {
        Room.databaseBuilder(context, SayBingsDatabase::class.java, databaseName).build()
    }

    private val  movementsRepository: MovementsRepository
        get() =
        MovementsRepository(database.getMovementDao())

    public val addMovementUseCase: AddMovementUseCase
        get() =
        AddMovementUseCase(movementsRepository)

    public val getAllMovementUseCase: GetAllMovementsUseCase
        get() =
        GetAllMovementsUseCase(movementsRepository)
}