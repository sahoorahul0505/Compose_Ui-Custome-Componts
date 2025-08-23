package com.kodebug.customecomponets.cardSlideAnimation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kodebug.customecomponets.R

@Composable
fun CardSlideAnimation(modifier: Modifier = Modifier) {

    val itemsList = listOf(
        Item("image_1", R.drawable.image_1),
        Item("image_2", R.drawable.image_2),
        Item("image_3", R.drawable.image_3),
        Item("image_4", R.drawable.image_4),
        Item("image_5", R.drawable.image_5),
        Item("image_6", R.drawable.image_6),
        Item("image_7", R.drawable.image_7),
        Item("image_8", R.drawable.image_8),
        Item("image_9", R.drawable.image_9),
        Item("image_10", R.drawable.image_10),
        Item("image_11", R.drawable.image_11),
        Item("image_12", R.drawable.image_12),
        Item("image_13", R.drawable.image_13),
        Item("image_14", R.drawable.image_14),
        Item("image_15", R.drawable.image_15)
    )

    // Create a pager state
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { itemsList.size }
    )

    // Current image
    var currentImage by remember { mutableStateOf(itemsList[pagerState.currentPage]) }

    Box(
        modifier
            .fillMaxSize()
            .background(Color.Black), contentAlignment = Alignment.Center
    ) {

        // UI feel less like a jump cut and more like a scene transition
        // background image animation
        Crossfade(
            targetState = currentImage.image,
            label = "",
            animationSpec = tween(500)
        ) { targetState ->
            Image(
                painter = painterResource(id = targetState),
                contentDescription = currentImage.name,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(
                        alpha = 0.5f,
                        compositingStrategy = CompositingStrategy.ModulateAlpha,
                    ),
                contentScale = ContentScale.Crop
            )
        }

        // Horizontal scroll behaviour
        HorizontalPager(
            modifier = modifier.fillMaxSize(),
            state = pagerState,
            contentPadding = PaddingValues(64.dp),
            key = {
                itemsList[it].image
            }
        ) { index ->
            currentImage = itemsList[pagerState.currentPage]

            val imageScale by animateFloatAsState(
                targetValue = if (index == pagerState.currentPage) 1.3f else 1f,
                animationSpec = tween(500),
                label = ""
            )

            val latterSpace by animateFloatAsState(
                targetValue = if (index == pagerState.currentPage) 8f else 0f,
                label = ""
            )

            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val saturation by animateFloatAsState(
                    targetValue = if (index == pagerState.currentPage) 1f else 0f,
                    animationSpec = tween(1500, easing = FastOutSlowInEasing),
                    label = ""
                )

                val grayScaleMatrixAnim = ColorMatrix().apply {
                    setToSaturation(saturation)
                }

                Image(
                    painter = painterResource(id = itemsList[index].image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(200.dp)
                        .aspectRatio(2 / 3f)
                        .scale(imageScale)
                        .shadow(elevation = 32.dp, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp)),
                    colorFilter = ColorFilter.colorMatrix(grayScaleMatrixAnim)
                )

                Spacer(modifier = Modifier.size(100.dp))
                Text(
                    text = itemsList[index].name,
                    fontSize = 25.sp,
                    color = Color.White,
                    letterSpacing = TextUnit(latterSpace, TextUnitType.Sp)
                )
            }
        }
    }
}


data class Item(
    val name: String,
    val image: Int
)