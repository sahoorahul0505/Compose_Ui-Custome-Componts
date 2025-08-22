package com.kodebug.customecomponets.flipableCardAnimation

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
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kodebug.customecomponets.R
import com.kodebug.customecomponets.utils.CodeBlock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlippableCardSourceCode(modifier: Modifier = Modifier) {
    val clipboardManager = LocalClipboardManager.current
    val codeText = """
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
                                          .size(300.dp, 500.dp)
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
                                                  rotationZ = rotateCardY,
                                                  cameraDistance = xAxisDistance
                                              )
                                              .shadow(elevation = 24.dp, ambientColor = Color.White, spotColor = Color.White, shape = RoundedCornerShape(24.dp))
                                              .clip(RoundedCornerShape(24.dp))
                                              .background(color = cardColor.copy(alpha = 1f))
                                      )
                                  }
                              }
                          }
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