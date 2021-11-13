package com.example.tiptime.ui.calculate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculateViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Calculate"
    }
    val text: LiveData<String> = _text
}