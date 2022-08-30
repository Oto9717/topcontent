package com.siradze.topcontent.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.siradze.topcontent.R

val fontFamily = FontFamily(
    Font(
        resId = R.font.roboto_condensed_regular,
        weight = FontWeight.W400,
        style = FontStyle.Normal
    ),
)
// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = fontFamily,
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)