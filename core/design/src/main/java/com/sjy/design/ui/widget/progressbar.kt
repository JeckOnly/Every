package com.sjy.design.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import timber.log.Timber
import kotlin.math.roundToInt

/**
 * Created by JeckOnly on 2023/9/7
 * Describe:
 */
@Composable
fun BallProgressBar(
    initProgress: Float,
    onProgressChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {

    var barWidth by remember { mutableStateOf(0.1f) }
    var ballWidth by remember { mutableStateOf(0f) }
    var offsetX by remember(
        barWidth,
        ballWidth
    ) { mutableFloatStateOf(initProgress * (barWidth - ballWidth)) }


    Box(
        modifier = modifier.onGloballyPositioned {
            barWidth = it.size.width.toFloat()
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    shape = RoundedCornerShape(50),
                    color = Color(0xff3C3C3C)
                )
                .align(Alignment.Center),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth((offsetX + ballWidth) / barWidth)
                .fillMaxHeight()
                .background(
                    shape = RoundedCornerShape(50),
                    color = Color(0xffFA9C18)
                )
                .align(Alignment.CenterStart),
        )

        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .draggable(
//                    enabled = offsetX >= 0 && offsetX < (barWidth - ballWidth),
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        val result = offsetX + delta
                        if (result >= 0 && result <= (barWidth - ballWidth)) {
                            Timber.d("parent: $barWidth, ball: $ballWidth, result: $result")
                            offsetX = result
                            // 外部callback
                            onProgressChange(result / (barWidth - ballWidth))
                        }

                    },
                )
                .onSizeChanged {
                    ballWidth = it.width.toFloat()
                }
                .requiredSize(20.dp)
                .background(
                    shape = CircleShape,
                    color = Color.White
                )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF00BCD4)
@Composable
fun PreviewBallProgressBar() {
    BallProgressBar(
        initProgress = 0.5f,
        onProgressChange = {},
        modifier = Modifier.fillMaxWidth().height(16.dp)
    )
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeightIn(30.dp ,30.dp)
            .background(color = Color.Red)
    ) {
        Box(modifier = Modifier
            .size(60.dp)
            .background(color = Color.Green, shape = CircleShape))
    }
}

