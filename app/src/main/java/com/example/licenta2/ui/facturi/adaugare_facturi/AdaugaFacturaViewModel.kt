package com.maxIdFactura.licenta2.ui.facturi.adaugare_facturi

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.licenta2.persistence.dao.FacturaDao
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Client
import com.example.licenta2.persistence.entities.Factura
import com.example.licenta2.persistence.entities.FacturaProdusCross
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdaugaFacturaViewModel : ViewModel() {
    private lateinit var appDatabase: AppDatabase
    fun initDatabase(context: Context)
    {
        appDatabase = AppDatabase.getDatabase(context)
        Log.e("ana", appDatabase.hashCode().toString())
    }

    private val _text = MutableLiveData<String>().apply {
        value = "Adauga Factura"
    }
    val text: LiveData<String> = _text

    fun insertFactura(factura: Factura) {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.facturaDao().insertFactura(factura)
        }
    }
    fun insertFacturaProdus(facturaProdusCross: FacturaProdusCross) {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.facturaClientCrossDao().insertFacturaProdusCross(facturaProdusCross)
        }
    }

    fun getMaxFacturaId(): Int {
        var idm =0;
        viewModelScope.launch(Dispatchers.IO){
           idm = appDatabase.facturaDao().getMaxFacturaId()
        }
        return idm;
    }


}