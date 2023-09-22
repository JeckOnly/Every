package com.sjy.udp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by JeckOnly on 2023/9/22
 * Describe:
 */

@Composable
fun UDPScreen() {

    val coroutineScope = rememberCoroutineScope()
    var receivedData by remember {
        mutableStateOf("---")
    }
    LaunchedEffect(key1 = Unit, block = {
        UDPUtil.receiveBroadcastData().flowOn(Dispatchers.IO).collect {
            receivedData = it
        }
    })

    Surface {
        Column {
            Button(onClick = {
                coroutineScope.launch {
                    UDPUtil.sendBroadcastData("Hello World")
                }
            }) {
                Text(text = "Send Broadcast")
            }

            Text(text = "进到界面开始收集")


            Row {
                Text(text = receivedData)
                Spacer(modifier = Modifier.width(16.dp))
                Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier
                    .width(24.dp)
                    .clickable {
                        receivedData = "---"
                    })
            }

        }
    }
}