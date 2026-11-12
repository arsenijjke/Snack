package app.arsenijjke.snack.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import app.arsenijjke.snack.R

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily(Font(resId = R.font.dmsans_bold, weight = FontWeight.Bold)),
        fontWeight = FontWeight.Bold,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(resId = R.font.dmsans_medium, weight = FontWeight.Medium)),
        fontWeight = FontWeight.Medium,
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(resId = R.font.dmsans_semibold, weight = FontWeight.SemiBold)),
        fontWeight = FontWeight.SemiBold,
    )
)

val DmSans = FontFamily(
    Font(R.font.dmsans_regular,  FontWeight.Normal),
    Font(R.font.dmsans_medium,   FontWeight.Medium),
    Font(R.font.dmsans_semibold, FontWeight.SemiBold),
    Font(R.font.dmsans_bold,     FontWeight.Bold),
)

val AppTypography = Typography(
    // "Recipe of the day" / "Categories" header
    titleLarge = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
    ),
    // "View all" link
    labelLarge = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    // Meal title
    headlineMedium = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 32.sp,
    ),
    // Meal description
    bodyMedium = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    // Chips ("DINNER", "35 min")
    labelMedium = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        letterSpacing = 1.sp,
    ),
    // Category item label
    labelSmall = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),
    // Greeting ("Good Evening")
    bodySmall = TextStyle(
        fontFamily = DmSans,
        fontSize = 14.sp,
    ),
    // Name under greeting
    bodyLarge = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
    ),
)