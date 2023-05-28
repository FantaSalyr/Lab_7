package com.topic2.android.notes.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
  primary = rwGreen,
  primaryVariant = rwGreenDark,
  secondary = rwGreen
)

private val DarkThemeColors = darkColors(
  primary = Color(0xFF00A055),
  primaryVariant = Color(0xFF00F884),
  secondary = Color.LightGray
)

/**
 * Отвечает за переключение цветовой палитры для темной и светлой темы.
 */
@Composable
fun NotesTheme(content: @Composable () -> Unit) {
  val isDarkThemeEnabled = isSystemInDarkTheme() || NotesThemeSettings.isDarkThemeEnabled
  val colors = if (isDarkThemeEnabled) DarkThemeColors else LightThemeColors

  MaterialTheme(colors = colors, content = content)
}

/**
 * Позволяет переключаться между светлой и темной темой в настройках приложения.
 */
object NotesThemeSettings {
  var isDarkThemeEnabled by mutableStateOf(false)
}
