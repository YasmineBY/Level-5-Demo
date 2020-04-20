package com.example.reminder.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.reminder.model.Reminder

@Dao
interface ReminderDao {

    @Query("SELECT * FROM reminderTable")
    suspend fun getAllReminders(): LiveData<List<Reminder>>

    @Insert
    fun insertReminder(reminder: Reminder)

    @Delete
    suspend fun deleteReminder(reminder: Reminder)

    @Update
    suspend fun updateReminder(reminder: Reminder)

}

