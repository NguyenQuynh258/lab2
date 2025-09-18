package com.example.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab2.model.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // tạo SmartHome với vài thiết bị mẫu
                val home = remember {
                    SmartHome().apply {
                        addDevice(SmartTvDevice("Living Room TV", "Entertainment"))
                        addDevice(SmartLightDevice("Bedroom Light", "Lighting"))
                    }
                }
                MainScreen(home)
            }
        }
    }
}

@Composable
fun MainScreen(home: SmartHome) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Smart Home Demo", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))

        Button(onClick = { home.turnOffAll() }) {
            Text("Turn off all")
        }

        Spacer(Modifier.height(12.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(home.devices) { device ->
                DeviceCard(device)
            }
        }
    }
}

@Composable
fun DeviceCard(device: SmartDevice) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(device.name, style = MaterialTheme.typography.titleMedium)
                    Text(device.category, style = MaterialTheme.typography.bodySmall)
                }
                Text(if (device.status == DeviceStatus.ON) "ON" else "OFF")
            }

            Spacer(Modifier.height(8.dp))

            Row {
                Button(onClick = {
                    if (device.status == DeviceStatus.ON) device.turnOff() else device.turnOn()
                }) {
                    Text(if (device.status == DeviceStatus.ON) "Turn off" else "Turn on")
                }

                Spacer(Modifier.width(8.dp))

                when (device) {
                    is SmartTvDevice -> {
                        // điều khiển TV
                        Button(onClick = { device.decreaseVolume() }) { Text("-") }
                        Spacer(Modifier.width(6.dp))
                        Text("Vol ${device.volume}", modifier = Modifier.padding(vertical = 8.dp))
                        Spacer(Modifier.width(6.dp))
                        Button(onClick = { device.increaseVolume() }) { Text("+") }

                        Spacer(Modifier.width(12.dp))

                        Button(onClick = { device.prevChannel() }) { Text("Prev ch") }
                        Spacer(Modifier.width(6.dp))
                        Text("Ch ${device.channel}", modifier = Modifier.padding(vertical = 8.dp))
                        Spacer(Modifier.width(6.dp))
                        Button(onClick = { device.nextChannel() }) { Text("Next ch") }
                    }

                    is SmartLightDevice -> {
                        // điều khiển đèn
                        Slider(
                            value = device.brightness.toFloat(),
                            onValueChange = { device.updateBrightness(it.toInt()) }
                            ,
                            valueRange = 0f..100f,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("${device.brightness}%")
                    }
                }
            }
        }
    }
}
