package com.example.licenta2.ui.incasari.adaugare_incasare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IncasareViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Incasari"
    }
    val text: LiveData<String> = _text
}