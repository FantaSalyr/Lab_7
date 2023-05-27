package com.topic2.android.notes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.topic2.android.notes.R
import com.topic2.android.notes.theme.rwGreen

@Composable
fun Note() {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(rwGreen)
    )
    Text(
        text = stringResource(id = R.string.header),
        maxLines = 1
    )
    Text(
        text = stringResource(id = R.string.content),
        maxLines = 1
    )
}

@Preview
@Composable
private fun NotePreview() { Note()

}