package com.sjy.widget

import android.content.Context
import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.material3.ColorProviders
import androidx.glance.text.FontFamily
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.sjy.design.ui.AppTheme
import com.sjy.design.ui.DarkColors
import com.sjy.design.ui.LightColors

/**
 * Created by JeckOnly on 2023/9/5
 * Describe:
 */
class TimerWidget: GlanceAppWidget() {

    private val timer = Timer()


    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val timeCount = timer.nowTimeInSeconds.collectAsState()

            GlanceTheme(colors = ColorProviders(LightColors, DarkColors)) {
                // NOTE 2023/9/6 用cornerRadius设置圆角只在12+有效
                Box (modifier = GlanceModifier.fillMaxSize().background(ImageProvider(R.drawable.widget_bg)), contentAlignment = Alignment.Center){
                    Column {
                        ShowTime(timeCount.value)
                        Text(text = "Start", modifier = GlanceModifier.padding(top = 10.dp).clickable {
                            Log.d("Jeck", "点击")
                            timer.start()
                        })
                    }
                }
            }
        }
    }

    override suspend fun onDelete(context: Context, glanceId: GlanceId) {
        super.onDelete(context, glanceId)
        Log.d("Jeck", "onDelete")
    }

}

@Composable
fun ShowTime(number: Int) {
    val second = number % 60
    val minute = number / 60 % 60
    val hour = number / 60 / 60
    val format = "%02d:%02d:%02d".format(hour, minute, second)

    Text(text = format, style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.Serif))
}

