package com.example.licenta2.ui.produse.adaugare_produse

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Produs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdaugaProdusViewModel : ViewModel() {
    private lateinit var appDatabase: AppDatabase
    fun initDatabase(context: Context)
    {
        appDatabase = AppDatabase.getDatabase(context)
        Log.e("ana", appDatabase.hashCode().toString())
    }

    private val _text = MutableLiveData<String>().apply {
        value = "Adauga Produs"
    }
    val text: LiveData<String> = _text
    fun insertProdus(produs: Produs) {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.produsDao().insertProdus(produs)
        }
    }

}