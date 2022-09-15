package com.app.saybings.common.composition

import android.content.Context
import androidx.annotation.UiThread
import androidx.room.Room
import com.app.saybings.data.database.MovementsRepository
import com.app.saybings.data.database.SayBingsDatabase
import com.app.saybings.domain.usecase.AddMovementUseCase
import com.app.saybings.domain.usecase.GetAllMovementsUseCase
import com.app.saybings.view.addMovementActivity.AddMovementViewModel
import com.app.saybings.view.homeActivity.HomeViewModel

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

    private val addMovementUseCase: AddMovementUseCase
        get() =
        AddMovementUseCase(movementsRepository)

    private val getAllMovementUseCase: GetAllMovementsUseCase
        get() =
        GetAllMovementsUseCase(movementsRepository)


    // ViewModels

    val homeViewModel: HomeViewModel get() = HomeViewModel(getAllMovementUseCase)
    val addMovementViewModel: AddMovementViewModel get() = AddMovementViewModel(addMovementUseCase)
}