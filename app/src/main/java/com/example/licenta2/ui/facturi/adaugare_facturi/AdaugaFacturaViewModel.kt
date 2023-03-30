package com.example.licenta2.ui.facturi.adaugare_facturi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AdaugaFacturaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Adauga Factura"
    }
    val text: LiveData<String> = _text
}