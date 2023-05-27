package com.topic2.android.notes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import com.topic2.android.notes.ui.components.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.topic2.android.notes.R
import com.topic2.android.notes.domain.model.NoteModel
import com.topic2.android.notes.ui.components.Note
import com.topic2.android.notes.viewmodel.MainViewModel

@Composable
fun NotesScreen(
    viewModel: MainViewModel
){
    val notes: List<NoteModel> by viewModel
        .notesNotInTrash
        .observeAsState(listOf())
    Column {
        TopAppBar(
            title = stringResource(id = R.string.notes),
            icon = Icons.Filled.List,
            onIconClick = {}
        )
        NotesList(
            notes = notes,
            onNoteClick = { viewModel.onNoteClick(it) },
            onNoteCheckedChange = { viewModel.onNoteCheckedChange(it) }
        )
    }
}

@Composable
private fun NotesList(
    notes: List<NoteModel>,
    onNoteCheckedChange: (NoteModel) -> Unit,
    onNoteClick: (NoteModel) -> Unit,
) {
    LazyColumn {
        items(count = notes.size){noteIndex->
            val note = notes[noteIndex]
            Note(
                note = note,
                onNoteClick = onNoteClick,
                onNoteCheckedChange = onNoteCheckedChange,
            )

        }
    }
}

@Preview
@Composable
private fun NotesListPreview(){
    NotesList(
        notes = listOf(
            NoteModel(1, "Note 1", "Content 1", null),
            NoteModel(1, "Note 2", "Content 2", true),
            NoteModel(1, "Note 3", "Content 3", false)
        ),
        onNoteCheckedChange = {}, onNoteClick = {}
    )
}