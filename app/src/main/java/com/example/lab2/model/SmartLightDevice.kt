package com.example.lab2.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class SmartLightDevice(name: String, category: String) : SmartDevice(name, category) {
    var brightness by mutableStateOf(50)
        private set

    fun updateBrightness(value: Int) {
        brightness = value.coerceIn(0, 100)
    }

    override fun turnOn() {
        super.turnOn()
        if (brightness == 0) updateBrightness(50)
    }

    override fun turnOff() {
        super.turnOff()
        updateBrightness(0)
    }
}
