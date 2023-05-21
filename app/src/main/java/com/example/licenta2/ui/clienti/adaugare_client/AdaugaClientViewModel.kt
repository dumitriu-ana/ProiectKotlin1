package com.example.licenta2.ui.clienti.adaugare_client

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Client
import com.example.licenta2.persistence.entities.Produs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdaugaClientViewModel : ViewModel() {
    private lateinit var appDatabase: AppDatabase
    fun initDatabase(context: Context)
    {
        appDatabase = AppDatabase.getDatabase(context)
        Log.e("ana", appDatabase.hashCode().toString())
    }

    private val _text = MutableLiveData<String>().apply {
        value = "Adauga Client"
    }
    val text: LiveData<String> = _text


    fun insertClient(client: Client) {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.clientDao().insertClient(client)
        }
    }

    fun retrieveItem(id: Int): LiveData<Client> {
        return appDatabase.clientDao().getItem(id).asLiveData()
    }

    fun updateClient(client: Client) {
        viewModelScope.launch {
            appDatabase.clientDao().update(client)
        }
    }


    fun updateClient(
        itemId: Int,
        itemCif: String,
        itemDenumire: String,
        itemCui: String,
        itemPlatitorTVA: Boolean,
        itemLocalitate: String,
        itemJudet: String,
        itemAdresa: String,
        itemNume: String,
        itemTelefon: String,
        itemEmail: String
    ) {
        val updatedItem:Client = getUpdatedClientEntry(itemId, itemCif, itemDenumire, itemCui, itemPlatitorTVA,
        itemLocalitate, itemJudet, itemAdresa, itemNume, itemTelefon, itemEmail)
        updateClient(updatedItem)
    }

fun getUpdatedClientEntry(
    itemId: Int,
    itemCif: String,
    itemDenumire: String,
    itemCui: String,
    itemPlatitorTVA: Boolean,
    itemLocalitate: String,
    itemJudet: String,
    itemAdresa: String,
    itemNume: String,
    itemTelefon: String,
    itemEmail: String
): Client {
    return Client(
        idClient = itemId,
        cif = itemCif,
        denumire = itemDenumire,
        regCom = itemCui,
        platitorDeTVA = itemPlatitorTVA,
        localitate = itemLocalitate,
        judet = itemJudet,
        adresa = itemAdresa,
        nume = itemNume,
        telefon = itemTelefon,
        email = itemEmail
    )

}
}