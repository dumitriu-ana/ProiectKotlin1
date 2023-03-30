package com.example.licenta2.ui.clienti.adaugare_client

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AdaugaClientViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Acasa"
    }
    val text: LiveData<String> = _text
}