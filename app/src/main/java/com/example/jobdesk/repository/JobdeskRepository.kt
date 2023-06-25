package com.example.jobdesk.repository

import com.example.jobdesk.dao.JobdeskDao
import com.example.jobdesk.model.Jobdesk
import kotlinx.coroutines.flow.Flow

class JobdeskRepository(private val JobdeskDao : JobdeskDao ) {
    val allJobdesk: Flow<List<Jobdesk>> = JobdeskDao.getAllJobdesk()
    suspend fun insertJobdesk(jobdesk: Jobdesk){
        JobdeskDao.inserJobdesk(jobdesk)
    }

    suspend fun deletetJobdesk(jobdesk: Jobdesk){
        JobdeskDao.deleteJobdesk(jobdesk)
    }

    suspend fun updateJobdesk(jobdesk: Jobdesk){
        JobdeskDao.updateJobdesk(jobdesk)
    }
}