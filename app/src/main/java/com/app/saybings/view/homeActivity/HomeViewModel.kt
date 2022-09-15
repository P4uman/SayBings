package com.app.saybings.view.homeActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.saybings.base.view.BaseViewModel
import com.app.saybings.domain.model.MovementModel
import com.app.saybings.domain.model.MovementType
import com.app.saybings.domain.usecase.GetAllMovementsUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAllMovementUseCase: GetAllMovementsUseCase
) : BaseViewModel() {

    private val _balance = MutableLiveData<Float>()
    val balance: LiveData<Float> = _balance

    fun updateBalance(){
        coroutineScope.launch {
            handleUpdateBalance(getAllMovementUseCase.invoke())
        }
    }

    private fun handleUpdateBalance(movementList: List<MovementModel>) {
        var balance = 0.0f

        movementList.forEach {
            balance += when (it.type) {
                MovementType.INCOME -> it.movement
                MovementType.EXPENDING -> -it.movement
            }
        }

        _balance.postValue(balance)
    }
}