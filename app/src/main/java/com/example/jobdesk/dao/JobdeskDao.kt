package com.example.jobdesk.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.jobdesk.model.Jobdesk
import kotlinx.coroutines.flow.Flow

@Dao
interface JobdeskDao {
    @Query("SELECT * FROM `jobdesk_table` ORDER BY id ASC")
    fun getAllJobdesk(): Flow<List<Jobdesk>>

    @Insert
    suspend fun inserJobdesk(jobdesk: Jobdesk)

    @Delete
    suspend fun deleteJobdesk(jobdesk: Jobdesk)

    @Update fun updateJobdesk(jobdesk: Jobdesk)
}
