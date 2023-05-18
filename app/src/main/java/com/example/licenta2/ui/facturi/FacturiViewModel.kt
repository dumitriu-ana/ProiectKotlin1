package com.example.licenta2.ui.facturi

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Client
import com.example.licenta2.persistence.entities.Factura
import com.example.licenta2.persistence.entities.FacturaCuProduse

class FacturiViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Facturi"
    }
    val text: LiveData<String> = _text

    private lateinit var appDatabase: AppDatabase
    fun initDatabase(context: Context)
    {
        appDatabase = AppDatabase.getDatabase(context)
        Log.e("ana", appDatabase.hashCode().toString())
    }

    fun getAllFacturi(): LiveData<List<Factura>> {
        return appDatabase.facturaDao().getAllFacturi()
    }
}