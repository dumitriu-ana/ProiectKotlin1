package com.example.licenta2.ui.clienti

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ClientiViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Clienti"
    }
    val text: LiveData<String> = _text
}