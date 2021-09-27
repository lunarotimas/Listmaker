package com.raywenderlich.listmaker.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.raywenderlich.listmaker.TaskList

// constructor with shared preferences
class MainViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {
    // tell classes when a list is added
    lateinit var onListAdded: (() -> Unit)
    // lists property that stays empty unless called
    val lists: MutableList<TaskList> by lazy {
        retrieveLists()
    }
    // retrieve and Task list
    private fun retrieveLists(): MutableList<TaskList> {
        val sharedPreferencesContents = sharedPreferences.all
        val taskLists = ArrayList<TaskList>()
        for (taskList in sharedPreferencesContents) {
            val itemsHashSet = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, itemsHashSet)
            taskLists.add(list)
        }
        return taskLists
    }
    // 5
    fun saveList(list: TaskList) {
        sharedPreferences.edit().putStringSet(list.name,
            list.tasks.toHashSet()).apply()
        lists.add(list)
        onListAdded.invoke()
    }
}