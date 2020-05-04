package com.example.reminder.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.reminder.model.Reminder
import com.example.reminder.repositories.ReminderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val reminderRepository: ReminderRepository = ReminderRepository(application.applicationContext)

    val reminders: LiveData<List<Reminder>> = reminderRepository.getAllReminders()

    /**
     * Count example to illistrate LiveData besides the RecyclerView
     * Note: use of encapsulation
     */

    private val _count: MutableLiveData<Int> = MutableLiveData(0)

    val count: LiveData<Int>
        get() = _count

    fun increment() {
        _count.value = _count.value?.plus(1)
    }

    private val ioScope = CoroutineScope(Dispatchers.IO)

    fun insertReminder(reminder: Reminder) {
        ioScope.launch {
            reminderRepository.insertReminder(reminder)
        }
    }

    fun deleteReminder(reminder: Reminder) {
        ioScope.launch {
            reminderRepository.deleteReminder(reminder)
        }
    }
}