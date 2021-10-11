package com.bu.simplehealth

import android.app.Application
import com.bu.simplehealth.database.AppDataBase
import com.bu.simplehealth.database.SimpleHealthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SimpleHealthApplication : Application() {

    // No need to cancel this scope as it'll be torn down with the process
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { AppDataBase.getDatabase(this, applicationScope) }
    val repository by lazy { SimpleHealthRepository(database.simpleHealthDao()) }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: SimpleHealthApplication
        fun app(): SimpleHealthApplication {
            return instance
        }
    }
}