package com.timepass.notesapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.timepass.notesapp.data.Notes
import com.timepass.notesapp.data.generateTimeStamp
import com.timepass.notesapp.data.getNotes
import com.timepass.notesapp.ui.main.FabButton
import com.timepass.notesapp.ui.main.NoteUi
import com.timepass.notesapp.ui.main.dialogBox
import com.timepass.notesapp.ui.theme.NotesTheme
import com.timepass.notesapp.utils.backgroundColor

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm = ViewModel()
            NotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = backgroundColor
                ) {
                    var openDialog by remember {
                        mutableStateOf(false)
                    }
                    Scaffold(
                        floatingActionButton = {
                            FabButton {
                                openDialog = !openDialog
                            }
                        },
                        containerColor = backgroundColor
                    ) {
                        NoteUi(vm)
                        if (openDialog){
                            dialogBox(title = "Add Notes!", notes = null, onDismiss = {openDialog= !openDialog},
                                onSubClick = {notes,noteNameValue,noteDesc->
                                if (notes!=null){
                                    notes.name = noteNameValue
                                    notes.detail = noteDesc
                                }
                                else{
                                    if (noteNameValue.isNotEmpty())
                                    vm.addNote(
                                        Notes(
                                        name = noteNameValue,
                                        detail = noteDesc,
                                        time = generateTimeStamp(),
                                            docId = ""
                                    )
                                    )
                                }
                                    openDialog  = !openDialog
                            })
                        }
                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotesTheme {
    }
}