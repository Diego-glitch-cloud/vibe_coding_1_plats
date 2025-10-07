package com.diegocal.tunechat.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = TuneChatPurpleLight,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = TuneChatDarkGray,
    surface = TuneChatDarkGray,
    onPrimary = TuneChatWhite,
    onSecondary = TuneChatWhite,
    onBackground = TuneChatWhite,
    onSurface = TuneChatWhite
)

private val LightColorScheme = lightColorScheme(
    primary = TuneChatPurple,
    secondary = TuneChatPurpleLight,
    tertiary = Pink40,
    background = TuneChatWhite,
    surface = TuneChatGray,
    onPrimary = TuneChatWhite,
    onSecondary = TuneChatWhite,
    onBackground = TuneChatDarkGray,
    onSurface = TuneChatDarkGray
)

@Composable
fun TuneChatTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Deshabilitado para mantener colores morado/blanco
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}