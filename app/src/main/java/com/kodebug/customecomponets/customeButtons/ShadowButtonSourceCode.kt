package com.kodebug.customecomponets.customeButtons

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
fun ShadowButtonSourceCode(modifier: Modifier = Modifier) {

    val clipboardManager = LocalClipboardManager.current
    val codeText = """
                            @Composable
                            fun ShadowButton(
                                onClick: () -> Unit,
                                modifier: Modifier = Modifier,
                                shape: Shape = RoundedCornerShape(32.dp),
                                containerColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
                                contentPadding: PaddingValues = PaddingValues(12.dp),
                                shadowColor: Color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = .4f),
                                content: @Composable RowScope.() -> Unit
                            ) {
                                Box(
                                    modifier = modifier
                                        .drawBehind {
                                            val shadowColor = shadowColor
                                            val cornerRadius = 32.dp.toPx()
                                            val blurRadius = 80f
                                            val offsetX = 18.dp.toPx()
                                            val offsetY = 18.dp.toPx()

                                            val paint = Paint().asFrameworkPaint().apply {
                                                isAntiAlias = true
                                                color = android.graphics.Color.TRANSPARENT
                                                setShadowLayer(
                                                    blurRadius,
                                                    offsetX,
                                                    offsetY,
                                                    shadowColor.toArgb()
                                                )
                                            }
                                            drawIntoCanvas {
                                                it.nativeCanvas.drawRoundRect(
                                                    0f,
                                                    0f,
                                                    size.width,
                                                    size.height,
                                                    cornerRadius,
                                                    cornerRadius,
                                                    paint
                                                )
                                            }
                                        }
                                ) {
                                    Button(
                                        onClick = onClick,
                                        shape = shape,
                                        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
                                        contentPadding = contentPadding,
                                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center,
                                            modifier = Modifier,
                                            content = content
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
                    Spacer(modifier = modifier.size(54.dp))
                }
            }
        }
    }
}

