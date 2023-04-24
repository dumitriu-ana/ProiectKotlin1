package com.example.licenta2.ui.autentificare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InregistrareViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Inregistrare"
    }
    val text: LiveData<String> = _text
}