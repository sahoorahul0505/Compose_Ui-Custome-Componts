package com.kodebug.customecomponets.expandableCard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kodebug.customecomponets.R
import com.kodebug.customecomponets.navigation.Routes
import com.kodebug.customecomponets.utils.CodeBlock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandCardSourceCode(modifier: Modifier = Modifier) {

    val clipboardManager = LocalClipboardManager.current
    val codeText = """
                            @Composable
                            fun ExpandableCard(item: Item) {

                                var expanded by remember { mutableStateOf(false) }
                                var isPressed by remember { mutableStateOf(false) }

                                val blurDpAnim by animateDpAsState(
                                    targetValue = if (isPressed) 10.dp else 0.dp,
                                    animationSpec = tween(durationMillis = 300),
                                    label = "blurAnim"
                                )

                                LaunchedEffect(isPressed) {
                                    if (isPressed) {
                                        delay(150)
                                        isPressed = false
                                    }
                                }

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable(
                                            interactionSource = null,
                                            indication = null
                                        ) {
                                            expanded = !expanded
                                            isPressed = true
                                        },
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.White
                                    ),
                                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                                    shape = RoundedCornerShape(12.dp),
                                ) {

                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                            .animateContentSize(
                                                animationSpec = tween(
                                                    durationMillis = 150,
                                                    easing = FastOutSlowInEasing
                                                )
                                            ),
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        Text(
                                            text = item.title.toString(),
                                            style = TextStyle(
                                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                                fontWeight = FontWeight.SemiBold,
                                                color = Color.Black
                                            )
                                        )

                                        if (expanded) {
                                            Column(
                                                modifier = Modifier
                                                    .blur(blurDpAnim)
                                                    .fillMaxWidth()
                                            ) {
                                                Spacer(modifier = Modifier.size(16.dp))
                                                Text(
                                                    text = item.desc.toString(),
                                                    style = TextStyle(
                                                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                                        color = Color.Gray
                                                    )
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                            data class Item(
                                val title: String? = "",
                                val desc: String? = ""
                            )
                        """.trimIndent()

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
                            text = "Source code", style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize
                            )
                        )
                        IconButton(
                            onClick = {
                                clipboardManager.setText(annotatedString = AnnotatedString(codeText))
                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.White.copy(alpha = .2f)
                            )
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.copy_solid_full),
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
                .background(color = Color(0xFF0D1117))
        ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)

            ) {
                item {
                    Spacer(modifier = modifier.size(24.dp))
                }
                item {
                    CodeBlock(
                        code = codeText
                    )
                }
                item {
                    Spacer(modifier = modifier.size(24.dp))
                }
            }
        }
    }
}