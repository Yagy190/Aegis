package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val AegisDarkColorScheme =
    darkColorScheme(
        primary = AegisPrimary,
        onPrimary = AegisOnPrimary,
        secondary = AegisSecondary,
        background = AegisBackground,
        surface = AegisSurface,
        surfaceVariant = AegisSurfaceVariant,
        onBackground = AegisTextPrimary,
        onSurface = AegisTextPrimary,
        onSurfaceVariant = AegisTextSecondary,
        error = AegisCritical
    )

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = AegisDarkColorScheme // Aegis is designed as an immersive dark emergency command center UI

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
