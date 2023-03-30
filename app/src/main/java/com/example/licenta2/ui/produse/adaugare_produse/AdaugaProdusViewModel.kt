package com.example.licenta2.ui.produse.adaugare_produse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AdaugaProdusViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Adauga Produs"
    }
    val text: LiveData<String> = _text
}