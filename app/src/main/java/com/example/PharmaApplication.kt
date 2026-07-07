package com.example

import android.app.Application
import androidx.room.Room
import com.example.data.PharmaDatabase
import com.example.data.PharmaRepository

class PharmaApplication : Application() {
    val database: PharmaDatabase by lazy {
        Room.databaseBuilder(
            this,
            PharmaDatabase::class.java,
            "pharma_database"
        ).fallbackToDestructiveMigration().build()
    }

    val repository: PharmaRepository by lazy {
        PharmaRepository(database.pharmaDao())
    }
}
