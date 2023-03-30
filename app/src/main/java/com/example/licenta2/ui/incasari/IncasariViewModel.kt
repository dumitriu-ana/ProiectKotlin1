package com.example.licenta2.ui.incasari

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IncasariViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Incasari"
    }
    val text: LiveData<String> = _text
}