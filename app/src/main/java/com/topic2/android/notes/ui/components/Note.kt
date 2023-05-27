package com.topic2.android.notes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.topic2.android.notes.R
import com.topic2.android.notes.routing.Screen
import com.topic2.android.notes.theme.NotesTheme
import com.topic2.android.notes.theme.rwGreen

@Composable
fun Note() {
    Row(modifier = Modifier.fillMaxWidth()) {
        NoteColor(
            color = rwGreen,
            size = 40.dp,
            padding = 4.dp,
            border = 1.dp
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(id = R.string.header),
                maxLines = 1
            )
            Text(
                text = stringResource(id = R.string.content),
                maxLines = 1
            )
        }
        Checkbox(
            checked = false,
            onCheckedChange = {},
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
private fun NotePreview() { Note()

}