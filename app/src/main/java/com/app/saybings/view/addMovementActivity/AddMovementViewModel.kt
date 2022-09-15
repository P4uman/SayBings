package com.app.saybings.view.addMovementActivity

import com.app.saybings.base.view.BaseViewModel
import com.app.saybings.domain.model.MovementModel
import com.app.saybings.domain.model.MovementType
import com.app.saybings.domain.usecase.AddMovementUseCase
import kotlinx.coroutines.launch

class AddMovementViewModel(
    private val addMovementUseCase: AddMovementUseCase
) : BaseViewModel() {

    fun addMovement(movementValue: Float, isSaving: Boolean) {
        coroutineScope.launch {
            addMovementUseCase.invoke(
                MovementModel(
                    movementValue,
                    if (isSaving) {
                        MovementType.INCOME
                    } else {
                        MovementType.EXPENDING
                    }
                )
            )
        }
    }
}