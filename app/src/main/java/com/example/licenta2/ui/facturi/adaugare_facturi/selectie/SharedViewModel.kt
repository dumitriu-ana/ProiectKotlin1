package com.example.licenta2.ui.facturi.adaugare_facturi.selectie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.licenta2.persistence.entities.Client
import com.example.licenta2.persistence.entities.Produs

class SharedViewModel:ViewModel() {
     val selectedClient = MutableLiveData<Client>()

    val listaProduse = mutableListOf<Produs>()
    val listaFinalaProduse = MutableLiveData<List<Produs>>()


    fun selectareClient(client:Client){
        selectedClient.value = client
    }

    fun adaugaProdus(produs:Produs)
    {
        listaProduse.add(produs)
    }
    fun produseSelectate()
    {
        listaFinalaProduse.value = listaProduse
    }

}