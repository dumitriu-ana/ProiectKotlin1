package com.example.licenta2.ui.incasari.adaugare_incasare

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Incasare
import com.example.licenta2.persistence.entities.Produs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AdaugaIncasareViewModel : ViewModel() {

    private lateinit var appDatabase: AppDatabase
    fun initDatabase(context: Context)
    {
        appDatabase = AppDatabase.getDatabase(context)
        Log.e("ana", appDatabase.hashCode().toString())
    }


    private val _text = MutableLiveData<String>().apply {
        value = "Adauga Incasare"
    }
    val text: LiveData<String> = _text




    fun insertIncasare(incasare: Incasare) {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.incasareDao().insertIncasare(incasare)
        }
    }
}