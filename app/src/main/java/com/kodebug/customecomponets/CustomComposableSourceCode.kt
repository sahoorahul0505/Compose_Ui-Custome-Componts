package com.kodebug.customecomponets

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
import com.kodebug.customecomponets.utils.CodeBlock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomComposableSourceCode(modifier: Modifier = Modifier) {
    val clipboardManager = LocalClipboardManager.current
    val codeText = """
                          @Composable
                          fun ComposableCard(item: ComposableItem, onIconClick: (ComposableItem) -> Unit) {
                              var expanded by remember {
                               mutableStateOf(false) 
                               }
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
                                  elevation = CardDefaults
                                  .elevatedCardElevation( 
                                  defaultElevation = 8.dp 
                                  ),
                                  colors = CardDefaults.
                                  elevatedCardColors(
                                  containerColor = Color.White
                                  ),
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
                                                  fontWeight = FontWeight.SemiBold,
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
                                                  color = Color.Gray
                                              )
                                          )
                                      }
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