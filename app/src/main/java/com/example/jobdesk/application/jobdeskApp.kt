package com.example.jobdesk.application

import android.app.Application
import com.example.jobdesk.repository.JobdeskRepository

class jobdeskApp: Application() {
    val database by lazy { jobdeskDatabase.getDatabase(this) }
    val repository by lazy { JobdeskRepository(database.JobdeskDao()) }
}