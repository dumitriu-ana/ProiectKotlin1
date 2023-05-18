package com.example.licenta2

import android.content.Context

class MySharedPreferences(context: Context) {
    val PREFERENCE_NAME = "MySharedPref"
    private val sharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    fun isDarkThemeEnabled(): Boolean {
        return sharedPreferences.getBoolean("isDarkThemeEnabled", false)
    }

    fun setDarkThemeEnabled(isEnabled: Boolean) {
        editor.putBoolean("isDarkThemeEnabled", isEnabled)
        editor.apply()
    }
}