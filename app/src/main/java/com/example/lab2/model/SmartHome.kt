package com.example.lab2.model



import androidx.compose.runtime.mutableStateListOf

class SmartHome {
    // danh sách "observable" để Compose cập nhật UI khi có thay đổi
    val devices = mutableStateListOf<SmartDevice>()

    fun addDevice(d: SmartDevice) { devices.add(d) }
    fun removeDevice(d: SmartDevice) { devices.remove(d) }
    fun turnOffAll() { devices.forEach { it.turnOff() } }
}
