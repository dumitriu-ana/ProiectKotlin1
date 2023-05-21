package com.example.licenta2.ui.clienti

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Client
import com.example.licenta2.persistence.entities.Factura
import com.example.licenta2.persistence.entities.Produs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ClientiViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Clienti"
    }
    val text: LiveData<String> = _text

    private lateinit var appDatabase: AppDatabase
    fun initDatabase(context: Context)
    {
        appDatabase = AppDatabase.getDatabase(context)
        Log.e("ana", appDatabase.hashCode().toString())
    }


    fun getAllClienti(): LiveData<List<Client>> {
        return appDatabase.clientDao().getAllClienti()
    }

    fun retrieveItem(id: Int): LiveData<Client> {
        return appDatabase.clientDao().getItem(id).asLiveData()
    }

    fun deleteItem(client: Client) {
        viewModelScope.launch {
            appDatabase.clientDao().delete(client)
        }
    }
}