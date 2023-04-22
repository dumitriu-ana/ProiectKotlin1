package com.example.licenta2.ui.produse

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.licenta2.persistence.dao.ProdusDao
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Incasare
import com.example.licenta2.persistence.entities.Produs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProduseViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Produse"
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