package com.kodebug.customecomponets.flipableCardAnimation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.EaseInOutElastic
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kodebug.customecomponets.R

@Composable
fun FlippableCardAnimation(modifier: Modifier = Modifier) {
    var isCardFlipped by remember { mutableStateOf(false) }

    var animDuration = 1500
    val xAxisDistance = 10f

    // rotate card y axis
    val rotateCardY by animateFloatAsState(
        targetValue = if (isCardFlipped) 180f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            easing = EaseOutBack
        )
    )

    // text visible animation
    val textAnim by animateFloatAsState(
        targetValue = if (isCardFlipped) 1f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            easing = EaseInBounce
        )
    )

    // Text animation
    val rotateX by animateFloatAsState(
        targetValue = if (isCardFlipped) 360f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            easing = EaseOutBack
        )
    )

    // Card color animation
    val cardColor by animateColorAsState(
        targetValue = if (isCardFlipped) Color.Red.copy(alpha = .5f) else Color.Green.copy(alpha = .5f),
        animationSpec = tween(
            durationMillis = animDuration,
            easing = FastOutSlowInEasing
        )
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(240.dp, 360.dp)
                .graphicsLayer(
                    rotationY = rotateCardY,
                    cameraDistance = xAxisDistance,
                )
                .clip(shape = RoundedCornerShape(24.dp))
                .clickable { isCardFlipped = !isCardFlipped }
                .background(color = cardColor),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .size(150.dp, 250.dp)
                    .graphicsLayer(
                        rotationZ = rotateCardY + 180,
                        cameraDistance = xAxisDistance
                    )
                    .shadow(
                        elevation = 24.dp,
                        ambientColor = Color.White,
                        spotColor = Color.White,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .clip(RoundedCornerShape(24.dp))
                    .background(color = cardColor.copy(alpha = 1f)),
                contentAlignment = Alignment.Center
            ) {
                if (isCardFlipped) {

                    Text(
                        text = "🤡", fontSize = 100.sp, modifier = modifier.graphicsLayer(
                            alpha = textAnim
                        )
                    )
                }
            }
        }

        Spacer(modifier.size(80.dp))
        Text(
            text = if (isCardFlipped) "Now u can see me" else "You can't see me",
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold,
                color = cardColor
            ),
            modifier = modifier.graphicsLayer(
                rotationX = rotateX
            )
        )
    }
}

@Preview
@Composable
private fun Preview() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        FlippableCardAnimation()
    }
}