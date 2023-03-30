package com.example.licenta2.ui.acasa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AcasaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Acasa"
    }
    val text: LiveData<String> = _text
}