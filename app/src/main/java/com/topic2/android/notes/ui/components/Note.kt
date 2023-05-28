package com.topic2.android.notes.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.topic2.android.notes.domain.model.NoteModel
import com.topic2.android.notes.ui.fromHex

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Note(
    modifier: Modifier = Modifier,
    note: NoteModel,
    onNoteClick: (NoteModel) -> Unit = {},
    onNoteCheckedChange: (NoteModel) -> Unit = {},
    isSelected: Boolean = false
) {
    val background = if (isSelected)
        Color.LightGray
    else
        MaterialTheme.colors.surface

    val modifier = null

    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        backgroundColor = background
    ) {
        ListItem(
            text = {
                Text(
                    text = note.title,
                    maxLines = 1
                )
            },
            secondaryText = {
                Text(
                    text = note.content,
                    maxLines = 1
                )
            },
            icon = {
                NoteColor(
                    color = Color.fromHex(note.color.hex),
                    size = 40.dp,
                    border = 1.dp
                )
            },
            trailing = {
                if (note.isCheckedOff != null) {
                    Checkbox(
                        checked = note.isCheckedOff,
                        onCheckedChange = { isChecked ->
                            val newNote = note.copy(isCheckedOff = isChecked)
                            onNoteCheckedChange.invoke(newNote)
                        },
                        modifier = Modifier.padding(start =  8.dp)
                    )
                }
            },
            modifier = Modifier.clickable {
                onNoteClick.invoke(note)
            }
        )
    }
}

@Preview
@Composable
private fun NotePreview() {
    Note(
        note = NoteModel(
            1,
            "Заметка 1",
            "Содержание 1",
            null
        )
    )
}