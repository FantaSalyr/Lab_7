package com.topic2.android.notes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.topic2.android.notes.R
import com.topic2.android.notes.routing.Screen
import com.topic2.android.notes.theme.NotesTheme
import com.topic2.android.notes.theme.rwGreen

@Composable
fun Note() {
    val backgroundShape: Shape = RoundedCornerShape(4.dp)
    Row(
        modifier = Modifier
            .padding(8.dp)
            .shadow(1.dp, backgroundShape)
            .fillMaxWidth()
            .heightIn(min = 64.dp)
            .background(Color.White, backgroundShape)

    ) {
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