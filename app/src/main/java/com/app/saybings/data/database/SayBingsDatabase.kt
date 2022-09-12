package com.app.saybings.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.saybings.data.database.dao.MovementDao
import com.app.saybings.data.database.entities.MovementEntityDB

private const val DATABASE_VERSION = 1

@Database(entities = [MovementEntityDB::class], version = DATABASE_VERSION)
abstract class SayBingsDatabase : RoomDatabase() {

    abstract fun getMovementDao(): MovementDao
}