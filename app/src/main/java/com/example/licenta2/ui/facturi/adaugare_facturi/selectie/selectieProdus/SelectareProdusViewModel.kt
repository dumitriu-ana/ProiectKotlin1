package com.example.licenta2.ui.facturi.adaugare_facturi.selectie.selectieProdus

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Produs

class SelectareProdusViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Selectare produs"
    }
    val text: LiveData<String> = _text

    private lateinit var appDatabase: AppDatabase
    fun initDatabase(context: Context)
    {
        appDatabase = AppDatabase.getDatabase(context)
        Log.e("ana", appDatabase.hashCode().toString())
    }

    fun getAllProduse(): LiveData<List<Produs>> {
        return appDatabase.produsDao().getAllProduse()
    }
}