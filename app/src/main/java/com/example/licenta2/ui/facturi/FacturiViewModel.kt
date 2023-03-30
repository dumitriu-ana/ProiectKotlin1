package com.example.licenta2.ui.facturi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FacturiViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Facturi"
    }
    val text: LiveData<String> = _text
}