package com.timepass.notesapp.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timepass.notesapp.ViewModel
import com.timepass.notesapp.data.Notes
import com.timepass.notesapp.data.generateTimeStamp
import com.timepass.notesapp.ui.main.component.FabButton
import com.timepass.notesapp.ui.main.component.NotesItem
import com.timepass.notesapp.ui.main.component.dialogBox
import com.timepass.notesapp.utils.PtFont
import com.timepass.notesapp.utils.backgroundColor
import com.timepass.notesapp.utils.collegeColor
import com.timepass.notesapp.utils.collegeColorLight
import com.timepass.notesapp.utils.randomColor
import com.timepass.notesapp.utils.randomColorLight

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomScreen(viewModel: ViewModel) {
    var openDialog by remember {
        mutableStateOf(
            false)
    }
    val listOfNotes = viewModel.randomList.collectAsState().value
    Scaffold(
        floatingActionButton = {
            FabButton (
                fabButtonColor = randomColor
            ){
                openDialog = !openDialog
            }
        },
        containerColor = backgroundColor
    ) {
        InRandomScreen(listOfNotes, onDeleteClick = {
            viewModel.deleteNotes(it)
        },
            onSubmitClick = {id,name,detail->
                viewModel.updateNotes(id,name, detail)
            },
            addNote = {
                viewModel.addNote(it)
            })
        if (openDialog){
            dialogBox(title = "Add Notes!", notes = null, onDismiss = {openDialog= !openDialog},
                onSubClick = {notes,noteNameValue,noteDesc->
                    if (notes!=null){
                        notes.name = noteNameValue
                        notes.detail = noteDesc
                    }
                    else{
                        if (noteNameValue.isNotEmpty())
                            viewModel.addNote(
                            Notes(
                                name = noteNameValue,
                                detail = noteDesc,
                                time = generateTimeStamp(),
                                docId = "",
                                tag = "random"
                            )
                        )
                    }
                    openDialog  = !openDialog
                })
        }
    }
}

@Composable
fun InRandomScreen(listOfNotes: List<Notes>,
                   onDeleteClick:(id:String)->Unit,
                   onSubmitClick:(noteId:String,name:String,detail: String)->Unit,
                   addNote:(Notes)->Unit) {
    Column(
        modifier = Modifier
            .background(backgroundColor)
            .padding(start = 34.dp, end = 34.dp, top = 64.dp, bottom = 50.dp)
            .fillMaxSize()
        ,
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Randoms",
            fontFamily = PtFont,
            fontSize = 40.sp,
            color = randomColorLight,
        )
        Spacer(modifier = Modifier.height(77.dp))
        if (listOfNotes.isEmpty()){
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {
                Text(
                    text = "Nothing ;)",
                    fontFamily = PtFont,
                    fontSize = 20.sp,
                    color = Color.White,
                )
                Text(
                    text = "•••",
                    fontFamily = PtFont,
                    fontSize = 25.sp,
                    color = Color.White,
                )
                Text(
                    text = "••",
                    fontFamily = PtFont,
                    fontSize = 20.sp,
                    color = Color.White,
                )
            }
        }
        else {
            LazyColumn {
                items(listOfNotes) {
                    NotesItem(note = it,
                        noteColor = randomColor,
                        onDeleteClick = {
                                        onDeleteClick(it)
                        },
                        noteButtonColor = randomColorLight,
                        onSubmitClick = { note, name, detail ->
                            if(note==null){
                                addNote(Notes(
                                    name = name,
                                    detail=detail,
                                    time = generateTimeStamp(),
                                    docId = "",
                                    tag = "random"
                                ))
                            }
                            else {
                                onSubmitClick(note.docId, name, detail)
                            }
                        },
                        buttonColor = randomColorLight)
                    Spacer(modifier = Modifier.height(17.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun RandomScreenPrev(){
    RandomScreen(ViewModel())
}