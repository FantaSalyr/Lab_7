package com.topic2.android.notes.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.topic2.android.notes.R
import com.topic2.android.notes.domain.model.ColorModel
import com.topic2.android.notes.ui.components.NoteColor
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.painterResource
import com.topic2.android.notes.domain.model.NEW_NOTE_ID
import com.topic2.android.notes.domain.model.NoteModel
import com.topic2.android.notes.routing.NotesRouter
import com.topic2.android.notes.routing.Screen
import com.topic2.android.notes.ui.fromHex
import com.topic2.android.notes.viewmodel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@ExperimentalMaterialApi
fun SaveNoteScreen(viewModel: MainViewModel){
    val noteEntry: NoteModel by viewModel.noteEntry.observeAsState(NoteModel())

    Scaffold(topBar = {
        val isEditingMode: Boolean = noteEntry.id != NEW_NOTE_ID
        SaveNoteTopAppBar(
            isEditingMode = isEditingMode,
            onBackClick = {
                NotesRouter.navigateTo(Screen.Notes)
            },
            onSaveNoteClick = {},
            onOpenColorPickerClick = {},
            onDeleteNoteClick = {}
        )
    },
        content = {}
    )
}

@Composable
private fun SaveNoteTopAppBar(
    isEditingMode: Boolean,
    onBackClick: () -> Unit,
    onSaveNoteClick: () -> Unit,
    onOpenColorPickerClick: () -> Unit,
    onDeleteNoteClick: () -> Unit
){
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.save_note) ,
                color = MaterialTheme.colors.onPrimary
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Save Note Button",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
        actions = {
            IconButton(onClick = onSaveNoteClick) {
                Icon(
                    imageVector = Icons.Default.Check,
                    tint = MaterialTheme.colors.onPrimary,
                    contentDescription = "Save Note"
                )
            }
            IconButton(onClick = onOpenColorPickerClick) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_baseline_color_lens_24
                    ),
                    contentDescription = "Open Color Picker Button",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
            if (isEditingMode) {
                IconButton(
                    onClick = onDeleteNoteClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Note Button",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    )
}

@Composable
private fun PickedColor(color: ColorModel){
    Row (
        Modifier
            .padding(8.dp)
            .padding(top = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.picked_color), modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        NoteColor(
            color = Color.fromHex(color.hex),
            size = 40.dp,
            border = 1.dp,
            modifier = Modifier.padding(4.dp)
        )
    }
}


@Composable
fun ColorItem(
    color: ColorModel,
    onColorSelect: (ColorModel) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    onColorSelect(color)
                }
            )
    ) {
        NoteColor(
            modifier = Modifier
                .padding(10.dp),
            color = Color.fromHex(color.hex) ,
            size = 80.dp,
            border = 2.dp
        )
        Text(
            text = color.name,
            fontSize = 22.sp,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun ColorPicker(
    color: List<ColorModel>,
    onColorSelect: (ColorModel) -> Unit
){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Color picker",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ){
            items(
                color.size
            ){
                    itemIndex ->
                val color = color[itemIndex]
                ColorItem(
                    color = color,
                    onColorSelect = onColorSelect
                )
            }
        }
    }
}

@Preview
@Composable
fun ColorPickerPreview(){
    ColorPicker(
        color = listOf(
            ColorModel.DEFAULT,
            ColorModel.DEFAULT,
            ColorModel.DEFAULT
        )
    ){

    }
}

@Preview
@Composable
fun ColorItemPreview(){
    ColorItem(ColorModel.DEFAULT){
    }
}

@Preview(showBackground = true)
@Composable
fun SaveNoteTopAppBarPreview(){
    SaveNoteTopAppBar(
        isEditingMode = true,
        onBackClick = {  },
        onSaveNoteClick = { },
        onOpenColorPickerClick = {  },
        onDeleteNoteClick = {  }
    )
}

@Preview(showBackground = true)
@Composable
fun PickedColorPreview(){
    PickedColor(ColorModel.DEFAULT)
}