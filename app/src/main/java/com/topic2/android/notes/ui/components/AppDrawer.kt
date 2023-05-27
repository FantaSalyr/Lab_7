package com.topic2.android.notes.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.topic2.android.notes.R
import com.topic2.android.notes.routing.NotesRouter
import com.topic2.android.notes.routing.Screen
import com.topic2.android.notes.theme.NotesTheme
import com.topic2.android.notes.theme.NotesThemeSettings

@Composable
private fun AppDrawerHeader(){
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(
            imageVector = Icons.Filled.Menu,
            contentDescription = "Drawer Header Icon",
            colorFilter = ColorFilter
                .tint(MaterialTheme.colors.onSurface),
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = stringResource(id = R.string.notes),
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
        )
    }
}

@Composable
private fun ScreenNavigationButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colors
    val imageAlpha = if (isSelected){
        1f
    } else{
        0.6f
    }
    val textColor = if (isSelected){
        colors.primary
    } else{
        colors.onSurface.copy(alpha = 0.6f)
    }
    val backgroundColor = if (isSelected){
        colors.primary.copy(alpha = 0.12f)
    } else{
        colors.surface
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Image(
                imageVector = icon ,
                contentDescription = "Screen Navigation Button",
                colorFilter = ColorFilter.tint(textColor),
                alpha = imageAlpha
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.body2,
                color = textColor,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun LightDarkThemeItem(){
    Row(
        Modifier
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.on_dark_theme),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                .align(alignment = Alignment.CenterVertically)
        )
        Switch(
            checked = NotesThemeSettings.isDarkThemeEnabled,
            onCheckedChange ={ NotesThemeSettings.isDarkThemeEnabled = it},
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .align(alignment = Alignment.CenterVertically)
        )
    }
}

@Composable
fun AppDrawer(
    currentScreen: Screen,
    closeDrawerAction: () ->Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppDrawerHeader()

        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))

        ScreenNavigationButton(
            icon = Icons.Filled.Home,
            label = stringResource(id = R.string.notes),
            isSelected = currentScreen == Screen.Notes,
            onClick = {
                NotesRouter.navigateTo(Screen.Notes)
                closeDrawerAction()
            }
        )
        ScreenNavigationButton(
            icon = Icons.Filled.Delete,
            label = stringResource(id = R.string.basket),
            isSelected = currentScreen == Screen.Trash,
            onClick = {
                NotesRouter.navigateTo(Screen.Trash)
                closeDrawerAction
            }
        )
        LightDarkThemeItem()
    }
}

@Composable
fun AppDrawerHeaderPreview(){
    NotesTheme {
        AppDrawerHeader()
    }
}

@Composable
fun ScreenNavigationButtonPreview(){
    NotesTheme {
        ScreenNavigationButton(
            icon = Icons.Filled.Home,
            label = stringResource(id = R.string.notes),
            isSelected = true,
            onClick = {}
        )
    }
}

@Composable
private fun LightDarkThemeItemPreview(){
    NotesTheme {
        LightDarkThemeItem()
    }
}

@Preview
@Composable
fun AppDrawerPreview(){
    NotesTheme {
        AppDrawer(Screen.Notes){

        }
    }
}