package com.sjy.every

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.sjy.design.ui.ExampleScreen

/**
 * Created by JeckOnly on 2023/9/5
 * Describe:
 */
class MainActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleScreen()
        }
    }
}