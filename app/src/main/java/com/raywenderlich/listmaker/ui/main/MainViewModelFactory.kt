package com.raywenderlich.listmaker.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// constructor for Shared preferences
class MainViewModelFactory(private val sharedPreferences:
                           SharedPreferences
) : ViewModelProvider.Factory {
    // returns shared view model
    override fun <T : ViewModel?> create(modelClass: Class<T>):
            T {
        return MainViewModel(sharedPreferences) as T
    }
}