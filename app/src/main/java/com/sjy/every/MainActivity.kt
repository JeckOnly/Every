package com.sjy.every

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import com.sjy.catfact.CatFactScreen
import com.sjy.design.ui.AppTheme
import com.sjy.design.ui.ExampleScreen
import com.sjy.udp.TestFlowContext
import com.sjy.udp.UDPScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by JeckOnly on 2023/9/5
 * Describe:
 */
@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface {
                    Column {
                        Button(onClick = {
                            TestFlowContext.testFlowContext()
                        }) {
                            Text(text = "测试东西")
                        }
                    }
                }
            }
        }
    }
}