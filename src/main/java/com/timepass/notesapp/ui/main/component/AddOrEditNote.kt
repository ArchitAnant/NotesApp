package com.timepass.notesapp.ui.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.timepass.notesapp.data.Notes
import com.timepass.notesapp.utils.PtFont
import com.timepass.notesapp.utils.backgroundColor
import com.timepass.notesapp.utils.editBackgroundColor
import com.timepass.notesapp.utils.notesColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dialogBox(
    title:String,
    notes: Notes?,
    onDismiss:()->Unit,
    onSubClick:(Notes?, String, String)->Unit,
    ){
    var noteNameValue by remember {
        mutableStateOf("")
    }
    var noteDesc by remember {
        mutableStateOf("")
    }
    if (notes!=null){
        noteDesc = notes.detail
        noteNameValue = notes.name
    }
        AlertDialog(
            modifier = Modifier
                .wrapContentHeight()
                .padding(30.dp),
            properties = DialogProperties(usePlatformDefaultWidth = false),
            containerColor = editBackgroundColor,
            onDismissRequest = {
                onDismiss
            },
            confirmButton = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onSubClick(notes,noteNameValue,noteDesc) }
                    ) {
                        Text("Submit!")
                    }
                }
            },
            title = {
                Text(
                    text = title,
                    fontFamily = PtFont,
                    fontSize = 15.sp,
                    color = notesColor,
                    modifier = Modifier.padding(5.dp)
                )
            },
            text = {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    OutlinedTextField(value = noteNameValue, onValueChange = {
                        noteNameValue = it
                    }, label = {
                        Text(
                            text = "Title", fontFamily = PtFont,
                            fontSize = 13.sp,
                            color = notesColor,
                            modifier = Modifier.padding(5.dp)
                        )
                    },
                        colors = TextFieldDefaults.outlinedTextFieldColors(textColor = notesColor),
                        keyboardOptions = KeyboardOptions(KeyboardCapitalization.Sentences)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(value = noteDesc, onValueChange = {
                        noteDesc = it
                    }, label = {
                        Text(
                            text = "Description",
                            fontFamily = PtFont,
                            fontSize = 13.sp,
                            color = notesColor,
                            modifier = Modifier.padding(5.dp)
                        )

                    },
                        colors = TextFieldDefaults.outlinedTextFieldColors(textColor = notesColor),
                        keyboardOptions = KeyboardOptions(KeyboardCapitalization.Sentences)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .padding(start = 10.dp, end = 10.dp),
                        onClick = onDismiss
                    ) {
                        Text("Dismiss!")
                    }
                }
            },
        )

}