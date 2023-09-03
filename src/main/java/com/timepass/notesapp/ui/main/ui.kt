package com.timepass.notesapp.ui.main

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timepass.notesapp.ViewModel
import com.timepass.notesapp.data.generateTimeStamp
import com.timepass.notesapp.utils.kiteFont
import com.timepass.notesapp.utils.notesColor

@Composable
fun NoteUi(viewModel: ViewModel){
    val context = LocalContext.current
    val listOfNotes = viewModel.listOfNotes.collectAsState().value
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Notes",
            fontFamily = kiteFont,
            fontSize = 24.sp,
            color = notesColor,
            modifier = Modifier.padding(30.dp).clickable {
                Toast.makeText(context,"Developed and Designed by Archit Anant",Toast.LENGTH_LONG).show()
            }
        )
        if(listOfNotes.isEmpty()){
            CircularProgressIndicator(color = notesColor)
        }
        else {
            LazyColumn {
                items(listOfNotes) {
                    NotesItem(note = it, onDeleteClick = {noteId->
                        viewModel.deleteNotes(noteId)
                    },
                        onSubmitClick = {note,name,detail->
                            if(name.isNotEmpty()&&detail.isNotEmpty()) {
                                viewModel.updateNotes(
                                    docId = note!!.docId,
                                    title = name,
                                    detail = detail,
                                )
                            }
                            else{
                                Toast.makeText(context,"Bhai likh de Kuch!",Toast.LENGTH_LONG).show()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MainUiDisplay() {
//    NoteUi()
}