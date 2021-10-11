package com.bu.simplehealth.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bu.simplehealth.database.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author Seema Palora
 * This class is to create Database
 */
@Database(entities = [User::class,Medication::class,MindGame::class,Exercise::class,Condition::class,UserConditionRef::class], version = 7)
abstract class AppDataBase : RoomDatabase() {

    abstract fun simpleHealthDao(): SimpleHealthDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        "SimpleHealth-db")
                        .fallbackToDestructiveMigration()
                        .addCallback(SimpleHealthDbCallback(scope))
                        .build()
                INSTANCE = instance
                instance
            }
        }

        private class SimpleHealthDbCallback(
                private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.simpleHealthDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(userDao: SimpleHealthDao) {
            userDao.deleteAll()
        }
    }


}