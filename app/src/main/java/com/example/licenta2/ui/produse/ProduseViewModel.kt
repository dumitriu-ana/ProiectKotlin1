package com.example.licenta2.ui.produse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProduseViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Produse"
    }
    val text: LiveData<String> = _text
}