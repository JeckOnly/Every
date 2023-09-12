package com.sjy.design.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sjy.design.ui.widget.BallProgressBar
import timber.log.Timber

/**
 * Created by JeckOnly on 2023/9/7
 * Describe:
 */

@Composable
fun ExampleScreen() {
    Box(modifier = Modifier.padding(16.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
        BallProgressBar(initProgress = 0.3f, onProgressChange = {
            Timber.d("progress: $it")
        }, modifier = Modifier
            .fillMaxWidth(0.7f)
            .height(16.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewExampleScreen() {
    ExampleScreen()
}