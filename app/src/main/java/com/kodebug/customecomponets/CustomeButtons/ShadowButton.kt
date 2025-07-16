package com.kodebug.customecomponets.CustomeButtons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShadowButton(
    onClick: () -> Unit,
    text: Int, // stringResource
    icon: Int? = null, // Svg file
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(32.dp),
    containerColor: Color,
    contentPadding: PaddingValues = PaddingValues(12.dp),
    textColor: Color
) {
    Box(
        modifier = modifier
            .drawBehind {
                val shadowColor = Color.LightGray.copy(alpha = 0.4f)
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
            modifier = Modifier
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                icon?.let {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = text.toString(),
                        tint = Color.Unspecified,
                        modifier = Modifier.size(42.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = text),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = textColor
                    )
                }
            }
        }
    }
}