package com.app.saybings.view.homeActivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.app.saybings.data.database.MovementsRepository
import com.app.saybings.data.database.SayBingsDatabase
import com.app.saybings.databinding.ActivityHomeBinding
import com.app.saybings.domain.model.MovementModel
import com.app.saybings.domain.model.MovementType
import com.app.saybings.domain.usecase.AddMovementUseCase
import com.app.saybings.domain.usecase.GetAllMovementsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


private const val DATABASE_NAME = "SayBingsDB"

class HomeActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var binding: ActivityHomeBinding
    private lateinit var getAllMovementUseCase: GetAllMovementsUseCase
    private lateinit var addMovementUseCase: AddMovementUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val db = Room.databaseBuilder(this, SayBingsDatabase::class.java, DATABASE_NAME).build()

        getAllMovementUseCase = GetAllMovementsUseCase(MovementsRepository(db.getMovementDao()))
        addMovementUseCase = AddMovementUseCase(MovementsRepository(db.getMovementDao()))

        coroutineScope.launch {
            updateBalance(getAllMovementUseCase.invoke())
        }

        initListeners()
    }

    private fun initListeners() {
        binding.btnAdd.setOnClickListener {
            coroutineScope.launch {
                addMovementUseCase.invoke(
                    getMovement()
                )

                updateBalance(getAllMovementUseCase.invoke())
            }
        }

        binding.btnCheck.setOnClickListener {
            coroutineScope.launch {
                Log.d("Movements: ", getAllMovementUseCase.invoke().toString())
            }
        }
    }

    private fun getMovement(): MovementModel =
        MovementModel(
            binding.edtxtMovement.text.toString().toFloat(),
            if (binding.swSavingSpending.isChecked) {
                MovementType.INCOME
            } else {
                MovementType.EXPENDING
            }
        )

    private fun updateBalance(movements: List<MovementModel>) {
        var balance = 0.0f

        movements.forEach {
            balance += when (it.type) {
                MovementType.INCOME -> it.movement
                MovementType.EXPENDING -> -it.movement
            }
        }

        binding.txtSavings.text = balance.toString()
    }
}