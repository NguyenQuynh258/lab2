package com.example.lab2.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class SmartTvDevice(name: String, category: String) : SmartDevice(name, category) {
    var volume by mutableStateOf(10)
        private set

    var channel by mutableStateOf(1)
        private set

    fun increaseVolume() {
        volume = (volume + 1).coerceIn(0, 100)
    }

    fun decreaseVolume() {
        volume = (volume - 1).coerceIn(0, 100)
    }

    fun nextChannel() {
        channel = (channel + 1).coerceIn(1, 999)
    }

    fun prevChannel() {
        channel = (channel - 1).coerceIn(1, 999)
    }

    override fun turnOn() {
        super.turnOn()
        if (volume == 0) volume = 10
    }
}
