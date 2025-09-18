package com.example.lab2.model


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class DeviceStatus { OFF, ON }

open class SmartDevice(val name: String, val category: String) {
    // dùng mutableStateOf để Compose tự recompose khi value thay đổi
    var status by mutableStateOf(DeviceStatus.OFF)
        protected set

    open fun turnOn() { status = DeviceStatus.ON }
    open fun turnOff() { status = DeviceStatus.OFF }
}
