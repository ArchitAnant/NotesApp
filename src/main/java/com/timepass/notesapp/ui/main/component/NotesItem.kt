package com.timepass.notesapp.ui.main.component

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timepass.notesapp.data.Notes
import com.timepass.notesapp.ui.main.tempNote
import com.timepass.notesapp.utils.PtFont
import com.timepass.notesapp.utils.backgroundColor
import com.timepass.notesapp.utils.collegeColor
import com.timepass.notesapp.utils.collegeColorLight
import com.timepass.notesapp.utils.editButtonColor
import com.timepass.notesapp.utils.notesColor
import com.timepass.notesapp.utils.notesFontColor

@Composable
fun NotesItem(
    note: Notes,
    noteColor:Color,
    noteButtonColor:Color,
    onDeleteClick:(String)->Unit,
    onSubmitClick:(Notes?,String,String)->Unit,
    buttonColor:Color
) {
    val context = LocalContext.current
    val noteTitle = note.name
    val dateTimeString = note.time
    val noteDec = note.detail
    var expanded by remember {
        mutableStateOf(false)
    }
    var expandIcon by remember {
        mutableStateOf(Icons.Default.KeyboardArrowDown)
    }
    var openDialog by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(
            topEnd = 16.dp,
            bottomEnd = 16.dp,
            bottomStart = 16.dp
        ),
        colors = CardDefaults.cardColors(containerColor = noteColor),
        ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(start = 15.dp, top = 10.dp, bottom = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    TextCompose(str = noteTitle, fontSize = 16.sp, paddingValues = 0.dp)
                    TextCompose(str = dateTimeString, fontSize = 9.sp, paddingValues = 0.dp)
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    expanded=!expanded
                }) {
                    Icon(
                        expandIcon,
                        contentDescription = null,
                        tint = backgroundColor
                    )
                }
            }
            if (expanded){
                expandIcon = Icons.Default.KeyboardArrowUp
                Spacer(modifier = Modifier.height(5.dp))
                TextCompose(str = noteDec, fontSize = 13.sp, paddingValues = 6.dp)
                Spacer(modifier = Modifier.height(25.dp))
                Row {
                    Button(
                        onClick = {
                            openDialog = true
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                    ) {
                        TextCompose(str = "edit", fontSize = 9.sp, paddingValues = 5.dp)
                    }
                    Spacer(modifier = Modifier.width(7.dp))
                    Button(
                        onClick = {
                            onDeleteClick(note.docId)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                    ) {
                        TextCompose(str = "delete", fontSize = 9.sp, paddingValues = 5.dp)
                    }
                }
            }
            else{
                expandIcon = Icons.Default.KeyboardArrowDown
            }
            if(openDialog){
                dialogBox(title = "Edit!", notes = note, onDismiss = {openDialog = false}, onSubClick = {notes, name, detail ->
                    if(name.isNotEmpty()&&detail.isNotEmpty()) {
                        onSubmitClick(notes, name, detail)
                        openDialog = !openDialog
                    }
                    else{
                        Toast.makeText(context,"Bhai Likh de Kuch!",Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }
}
@Composable
fun TextCompose(str:String,fontSize : TextUnit ,paddingValues: Dp) {
    Text(
        text = str,
        fontFamily = PtFont,
        fontSize = fontSize,
        color = backgroundColor,
        modifier = Modifier.padding(paddingValues)
    )
}

@Preview
@Composable
fun NoteDisplay() {
//    NotesItem(note = tempNote, noteColor = collegeColor, onDeleteClick ={} , noteButtonColor = collegeColorLight,onSubmitClick = {
//        notes, s, s2 ->
//    })
}