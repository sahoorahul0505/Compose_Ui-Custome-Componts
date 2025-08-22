package com.kodebug.customecomponets.utils

import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun CodeBlock(modifier: Modifier = Modifier, code: String) {
    /*
    val keywordColor = Color(0xFFff7b72)   // pink
    val stringColor = Color(0xFF3CA041)    // green
    val annotationColor = Color(0xFFD5BF00) // yellow
    val commentColor = Color(0xFF6C6969)   // gray
    val definitionColor = Color(0xFFCA82F5)
    val numberColor = Color(0xFF03A9F4)
    val normalColor = Color.White
 */

    val keywordColor = Color(0xFFff7b72)    // red/purple
    val stringColor = Color(0xFF4CAF50)     // green-blue
    val annotationColor = Color(0xFFD5BF00) // orange
    val commentColor = Color(0xFF6A737D)    // gray
    val definitionColor = Color(0xFFCA82F5) // purple (function/class names)
    val typeColor = Color(0xFFBE43D2)       // blue for types
    val numberColor = Color(0xFF7BB3FC)     // blue
    val normalColor = Color.White

    val numberWithUnitRegex = Regex("""^(-?\d+(\.\d+)?)([a-zA-Z]+)$""")
    val keywords = listOf(
        "fun", "val", "var", "if", "else", "return", "class", "object",
        "for", "while", "when", "interface", "override", "import", "package","data"
    )

    val annotated = buildAnnotatedString {
        val lines = code.split("\n")

        for ((i, line) in lines.withIndex()) {
            val tokens = line.split(" ")
            var skipNext = false

            for ((index, token) in tokens.withIndex()) {
                if (skipNext) {
                    skipNext = false; continue
                }

                when {
                    // Comment
                    token.trim().startsWith("//") -> {
                        withStyle(SpanStyle(color = commentColor, fontFamily = FontFamily.Monospace)) {
                            append(tokens.drop(index).joinToString(" "))
                        }
                        break
                    }

                    // String
                    token.startsWith("\"") && token.endsWith("\"") -> withStyle(
                        SpanStyle(color = stringColor, fontFamily = FontFamily.Monospace)
                    ) { append("$token ") }

                    // Annotation
                    token.startsWith("@") -> withStyle(
                        SpanStyle(color = annotationColor, fontFamily = FontFamily.Monospace)
                    ) { append("$token ") }

                    // Numbers with units (54.dp, 12f)
                    numberWithUnitRegex.matches(token) -> {
                        val match = numberWithUnitRegex.find(token)
                        val num = match?.groups?.get(1)?.value ?: token
                        val unit = match?.groups?.get(3)?.value ?: ""
                        withStyle(SpanStyle(color = numberColor, fontFamily = FontFamily.Monospace)) {
                            append(num)
                        }
                        withStyle(SpanStyle(color = normalColor, fontFamily = FontFamily.Monospace)) {
                            append("$unit ")
                        }
                    }

                    // Numbers
                    token.toDoubleOrNull() != null -> withStyle(
                        SpanStyle(color = numberColor, fontFamily = FontFamily.Monospace)
                    ) { append("$token ") }

                    // Keyword + definition (like fun Foo)
                    token in listOf("fun", "class", "object", "interface") -> {
                        withStyle(SpanStyle(color = keywordColor, fontFamily = FontFamily.Monospace)) {
                            append("$token ")
                        }
                        val next = tokens.getOrNull(index + 1)
                        if (next != null) {
                            val name = next.takeWhile { it.isLetterOrDigit() || it == '_' }
                            withStyle(SpanStyle(color = definitionColor, fontFamily = FontFamily.Monospace)) {
                                append("$name ")
                            }
                            if (next.length > name.length) {
                                append(next.drop(name.length) + " ")
                            }
                            skipNext = true
                        }
                    }

                    // Keywords
                    token in keywords -> withStyle(
                        SpanStyle(color = keywordColor, fontFamily = FontFamily.Monospace)
                    ) { append("$token ") }

                    // Types (Int, String, Boolean, etc.)
                    token in listOf("Int", "String", "Boolean", "Float", "Double", "Unit") -> withStyle(
                        SpanStyle(color = typeColor, fontFamily = FontFamily.Monospace)
                    ) { append("$token ") }

                    // Default
                    else -> withStyle(
                        SpanStyle(color = normalColor, fontFamily = FontFamily.Monospace)
                    ) { append("$token ") }
                }
            }
            if (i != lines.lastIndex) append("\n")
        }
    }

    SelectionContainer {
        Text(
            text = annotated,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace
            )
        )
    }


}