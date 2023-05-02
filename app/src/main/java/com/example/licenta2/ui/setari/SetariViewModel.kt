package com.example.licenta2.ui.setari

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Client
import com.example.licenta2.persistence.entities.Factura
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SetariViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Setari"
    }
    val text: LiveData<String> = _text

    private lateinit var appDatabase: AppDatabase
    fun initDatabase(context: Context)
    {
        appDatabase = AppDatabase.getDatabase(context)
        Log.e("ana", appDatabase.hashCode().toString())
    }

    fun stergereIncasari() {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.incasareDao().stergereToateIncasarile()
        }
    }
    fun stergereProduse() {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.produsDao().stergereToateProdusele()
        }
    }
    fun stergereClienti() {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.clientDao().stergereTotiClientii()
        }
    }
    fun stergereFacturi() {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.facturaDao().stergereToateFacturile()
        }
    }
}