package com.sjy.widget

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.material3.ColorProviders
import androidx.glance.text.Text
import com.sjy.design.ui.AppTheme
import com.sjy.design.ui.DarkColors
import com.sjy.design.ui.LightColors

/**
 * Created by JeckOnly on 2023/9/5
 * Describe:
 */
class TimerWidget: GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme(colors = ColorProviders(LightColors, DarkColors)) {
                // TODO 2023/9/6 用cornerRadius设置圆角只在12+有效
                Box (modifier = GlanceModifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background).cornerRadius(15.dp)){
                    Text(text = "This is a Widget", modifier = GlanceModifier.padding(20.dp))
                }
            }
        }
    }
}