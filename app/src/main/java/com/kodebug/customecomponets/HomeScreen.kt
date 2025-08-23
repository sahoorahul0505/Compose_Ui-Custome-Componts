package com.kodebug.customecomponets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kodebug.customecomponets.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController) {

    val compList = listOf(
        ComposableItem(
            name = "Shadow Button",
            desc = "This composable creates a ShadowButton with a custom shadow effect, since the default \n" +
                    "Compose shadow is not very impressive.\n" +
                    "\n" +
                    "The shadow is drawn manually using drawBehind with a Paint object, where blur, offset," +
                    "corner radius, and shadow color are applied to a rounded rectangle.\n" +
                    "\n" +
                    "A Material3 Button is placed inside the Box with its own elevation set to 0.dp, so only" +
                    "the custom shadow is visible. This approach gives more realistic and softer shadows" +
                    "compared to the built-in Compose shadow.\n",
            route = Routes.ShadowButton
        ),
        ComposableItem(
            name = "Expandable Card",
            desc = "This composable creates an expandable card with two animations:\n"+
                    "\n"+
                    "A blur animation on the description section using animateDpAsState, which briefly blurs in and out when the card is pressed.\n"+
                    "\n"+
                    "A size transition using animateContentSize, which smoothly expands or collapses the card when toggled.\n"+
                    "\n"+
                    "The animations are driven by state (expanded, isPressed) and controlled with tween timing and easing.",
            route = Routes.ExpandableCard
        ),
        ComposableItem(
            name = "Flippable Card",
            desc = "This composable demonstrates a flippable card animation with multiple effects:\n" +
                    "\n" +
                    "Rotation animations using animateFloatAsState to smoothly flip the card and control text visibility.\n" +
                    "\n" +
                    "A background color transition using animateColorAsState, which changes between colors when the card is flipped.\n" +
                    "\n" +
                    "3D perspective applied through graphicsLayer with cameraDistance, along with clip, shadow, and background for styling.\n" +
                    "\n" +
                    "A clickable modifier toggles the state, revealing animated content (emoji/text) with easing effects.\n",
            route = Routes.FlippableCard
        ),
        ComposableItem(
            name = "Card Slide Animation",
            desc = "This effect is achieved using only two composables:\n" +
                    "\n" +
                    "Crossfade → animates the background image when the pager changes, giving a smooth transition instead of a hard swap.\n" +
                    "\n" +
                    "HorizontalPager → handles the sliding cards, where each card animates with:\n" +
                    "\n" +
                    "Scale (zoom in when focused)\n" +
                    "\n" +
                    "Saturation (color for focused, grayscale for others)\n" +
                    "\n" +
                    "Letter spacing (text expands when focused)\n" +
                    "\n" +
                    "Together, it creates a polished card slider with smooth focus-based animations.",
            route = Routes.CardSlideAnimation
        )
    )





    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Custom Composable", style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize
                            )
                        )
                        IconButton(
                            onClick = {
                                navController.navigate(Routes.HomeCardsSourceCode)
                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.White.copy(alpha = .2f)
                            )
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.code_solid_full),
                                contentDescription = "source code",
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = modifier.size(28.dp)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Gray,
                    titleContentColor = Color.White
                )
            )
        }
    ) {
        Box(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
                .background(color = Color.White.copy(.9f))
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
            ) {
                LazyColumn {
                    item {
                        Spacer(modifier = modifier.size(24.dp))
                    }
                    items(compList) {

                        ComposableCard(item = it, onIconClick = {
                            navController.navigate(it.route)
                        })
                        Spacer(modifier = Modifier.size(24.dp))
                    }
                    item {
                        Spacer(modifier = modifier.size(240.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ComposableCard(item: ComposableItem, onIconClick: (ComposableItem) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var animPadding = animateDpAsState(
        targetValue = if (expanded) 16.dp else 8.dp,
        animationSpec = tween(
            durationMillis = 150,
            easing = FastOutSlowInEasing
        )
    )

    ElevatedCard(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = null
            ) { expanded = !expanded }
            .padding(
                horizontal = animPadding.value
            )
            .shadow(8.dp, shape = RoundedCornerShape(16.dp), clip = false),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp), // disable built-in shadow
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 250,
                        easing = FastOutSlowInEasing
                    )
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.name, style = TextStyle(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
                IconButton(onClick = { onIconClick(item) }) {
                    Icon(
                        Icons.AutoMirrored.Outlined.ArrowForward,
                        contentDescription = "Expand Icon",
                        tint = Color.Black
                    )
                }
            }

            if (expanded) {
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = item.desc,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    ),
                    lineHeight = 20.sp
                )
            }
        }
    }
}

data class ComposableItem(
    val name: String,
    val desc: String,
    val route: Routes
)