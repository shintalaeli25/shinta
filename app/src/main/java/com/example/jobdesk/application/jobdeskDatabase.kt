package com.example.jobdesk.application

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jobdesk.dao.JobdeskDao
import com.example.jobdesk.model.Jobdesk


@Database(entities = [Jobdesk::class], version = 1, exportSchema = false)
abstract class jobdeskDatabase: RoomDatabase() {
    abstract fun JobdeskDao():JobdeskDao

    companion object {
        private var INSTANCE: jobdeskDatabase? = null

        fun getDatabase(context: Context): jobdeskDatabase {
            return INSTANCE ?: synchronized(this){
             val instance = Room.databaseBuilder(
                 context.applicationContext,
                 jobdeskDatabase::class.java,
                 "jobdesk_database"
             )
                 .allowMainThreadQueries()
                 .build()

                INSTANCE= instance
                instance
            }
        }
    }
}