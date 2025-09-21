package com.example.spclone.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = SpotifyGreen,
    secondary = SpotifyDarkGray,
    tertiary = SpotifyWhite,
    background = SpotifyBlack,
    surface = SpotifyBlack,
    onPrimary = SpotifyWhite,
    onSecondary = SpotifyWhite,
    onTertiary = SpotifyBlack,
    onBackground = SpotifyWhite,
    onSurface = SpotifyWhite
)

private val LightColorScheme = lightColorScheme(
    primary = SpotifyGreen,
    secondary = SpotifyDarkGray,
    tertiary = SpotifyWhite,
    background = SpotifyWhite,
    surface = SpotifyWhite,
    onPrimary = SpotifyBlack,
    onSecondary = SpotifyWhite,
    onTertiary = SpotifyBlack,
    onBackground = SpotifyBlack,
    onSurface = SpotifyBlack
)

@Composable
fun SPCloneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
