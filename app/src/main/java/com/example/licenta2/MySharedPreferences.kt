package com.example.licenta2

import android.content.Context
import com.google.android.gms.maps.GoogleMap

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


    fun setLanguage(language: String) {
        editor.putString("selectedLanguage", language)
        editor.apply()
    }
    fun getLanguage(): String? {
        return sharedPreferences.getString("selectedLanguage", null)
    }

    fun setMapMode(selectedMapMode: Int) {
        editor.putInt("selectedMapMode", selectedMapMode)
        editor.apply()
    }
    fun getMapMode(): Int {
        return sharedPreferences.getInt("selectedMapMode", GoogleMap.MAP_TYPE_NORMAL)
    }

    fun setMapZoom(selectedMapZoom: Float) {
        editor.putFloat("selectedMapZoom", selectedMapZoom)
        editor.apply()
    }
    fun getMapZoom(): Float {
        return sharedPreferences.getFloat("selectedMapZoom", 15f)
    }
}