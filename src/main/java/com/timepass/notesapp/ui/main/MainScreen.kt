package com.timepass.notesapp.ui.main

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timepass.notesapp.R
import com.timepass.notesapp.ViewModel
import com.timepass.notesapp.data.Notes
import com.timepass.notesapp.data.generateTimeStamp
import com.timepass.notesapp.ui.main.component.NotesItem
import com.timepass.notesapp.utils.PtFont
import com.timepass.notesapp.utils.backgroundColor
import com.timepass.notesapp.utils.collegeColor
import com.timepass.notesapp.utils.collegeColorLight
import com.timepass.notesapp.utils.personalColor
import com.timepass.notesapp.utils.personalColorLight
import com.timepass.notesapp.utils.profColorLight
import com.timepass.notesapp.utils.professionalColor
import com.timepass.notesapp.utils.randomColor
import com.timepass.notesapp.utils.randomColorLight
import com.timepass.notesapp.utils.searchBarColor
import kotlin.random.Random

@Composable
fun NoteUi(
    onCollegeClick:()->Unit,
    onProfessionalClick:()->Unit,
    onPersonalClick:()->Unit,
    onRandomClick:()->Unit,
    viewModel: ViewModel
){
    val shuffleList = viewModel.shuffleList.collectAsState().value
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .background(backgroundColor)
            .padding(start = 34.dp, end = 34.dp, top = 64.dp, bottom = 50.dp)
            .fillMaxSize()
        ,
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "My",
            fontFamily = PtFont,
            fontSize = 40.sp,
            color = searchBarColor,
            modifier = Modifier.clickable {
                Toast.makeText(context,"Developed and Designed by Archit Anant",Toast.LENGTH_LONG).show()
            }
        )
        Text(
            text = "Notes",
            fontFamily = PtFont,
            fontSize = 40.sp,
            color = searchBarColor,
            modifier = Modifier.clickable {
                Toast.makeText(context,"Developed and Designed by Archit Anant",Toast.LENGTH_LONG).show()
            }
        )
        Spacer(modifier = Modifier.height(65.dp))
        Column(Modifier.verticalScroll(rememberScrollState())) {
            Row {
                leftSector(
                    sec = "College",
                    bgColor = collegeColor,
                    obj = R.drawable.college,
                    onClick = onCollegeClick
                )
                Spacer(modifier = Modifier.weight(1f))
                rightSector(
                    sec = "Professional",
                    bgColor = professionalColor,
                    obj = R.drawable.prof,
                    onClick = onProfessionalClick
                )
            }
            Spacer(modifier = Modifier.height(7.dp))
            Row {
                leftSector(
                    sec = "Personals",
                    bgColor = personalColor,
                    obj = R.drawable.personal,
                    onClick = onPersonalClick
                )
                Spacer(modifier = Modifier.weight(1f))
                rightSector(
                    sec = "Randoms!",
                    bgColor = randomColor,
                    obj = R.drawable.random,
                    onClick = onRandomClick
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Shuffle!",
                fontFamily = PtFont,
                fontSize = 30.sp,
                color = searchBarColor
            )
            Spacer(modifier = Modifier.height(20.dp))
            if (shuffleList.isEmpty()) {
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

            } else {
                val myRandomValues = mutableListOf<Int>()
                if (shuffleList.size<=4) {
                    for (i in 1..shuffleList.size){
                        myRandomValues.add(i)
                    }
                }
                else {
                    while (myRandomValues.size < 4) {
                        val curr = Random.nextInt(0, shuffleList.size - 1)
                        if (!myRandomValues.contains(curr)) {
                            myRandomValues.add(curr)
                        }
                    }
                }
                for (i in myRandomValues) {
                    val curr = shuffleList[i]
                    when(curr.tag){
                        "college"->{
                            NotesItem(
                                note =curr,
                                noteColor = collegeColor,
                                noteButtonColor = collegeColorLight,
                                onDeleteClick = {
                                    viewModel.deleteNotes(it)
                                },
                                onSubmitClick = { notes, name, detail ->
                                    if(notes==null){
                                        viewModel.addNote(Notes(
                                            name = name,
                                            detail = detail,
                                            time = generateTimeStamp(),
                                            docId = "",
                                            tag = "college"
                                        ))
                                    }
                                    else{
                                        viewModel.updateNotes(notes.docId,name,detail)
                                    }
                                },
                                buttonColor = collegeColorLight)
                        }
                        "professional"->{
                            NotesItem(
                                note =curr,
                                noteColor = professionalColor,
                                noteButtonColor = profColorLight,
                                onDeleteClick = {
                                    viewModel.deleteNotes(it)
                                },
                                onSubmitClick = { notes, name, detail ->
                                    if(notes==null){
                                        viewModel.addNote(Notes(
                                            name = name,
                                            detail = detail,
                                            time = generateTimeStamp(),
                                            docId = "",
                                            tag = "professional"
                                        ))
                                    }
                                    else{
                                        viewModel.updateNotes(notes.docId,name,detail)
                                    }
                                },
                                buttonColor = profColorLight)
                        }
                        "personal"->{
                            NotesItem(
                                note =curr,
                                noteColor = personalColor,
                                noteButtonColor = personalColorLight,
                                onDeleteClick = {
                                    viewModel.deleteNotes(it)
                                },
                                onSubmitClick = { notes, name, detail ->
                                    if(notes==null){
                                        viewModel.addNote(Notes(
                                            name = name,
                                            detail = detail,
                                            time = generateTimeStamp(),
                                            docId = "",
                                            tag = "personal"
                                        ))
                                    }
                                    else{
                                        viewModel.updateNotes(notes.docId,name,detail)
                                    }
                                },
                                buttonColor = personalColorLight)
                        }
                        "random"->{
                            NotesItem(
                                note =curr,
                                noteColor = randomColor,
                                noteButtonColor = randomColorLight,
                                onDeleteClick = {
                                    viewModel.deleteNotes(it)
                                },
                                onSubmitClick = { notes, name, detail ->
                                    if(notes==null){
                                        viewModel.addNote(Notes(
                                            name = name,
                                            detail = detail,
                                            time = generateTimeStamp(),
                                            docId = "",
                                            tag = "random"
                                        ))
                                    }
                                    else{
                                        viewModel.updateNotes(notes.docId,name,detail)
                                    }
                                },
                                buttonColor = randomColorLight)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun leftSector(sec:String,bgColor:Color,@DrawableRes obj:Int,onClick: () -> Unit){
    Card(shape = RoundedCornerShape(
        topEnd = 16.dp,
        bottomEnd = 16.dp,
        bottomStart = 16.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        modifier = Modifier.height(66.dp),
        onClick = onClick
    ) {
        Row (verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 20.dp)
        ){
            Image(painter = painterResource(id = obj), contentDescription = null, modifier = Modifier.size(28.dp))
            Text(
                text = sec,
                fontFamily = PtFont,
                fontSize = 21.sp,
                color = backgroundColor,
                modifier = Modifier.padding(start = 5.dp,end=20.dp)
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rightSector(sec:String,bgColor:Color,@DrawableRes obj:Int,onClick: () -> Unit){
    Card(shape = RoundedCornerShape(
        topStart = 16.dp,
        bottomEnd = 16.dp,
        bottomStart = 16.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        modifier = Modifier.height(66.dp),
        onClick = onClick
    ) {
        Row (verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 20.dp)
        ){
            Image(painter = painterResource(id = obj), contentDescription = null, modifier = Modifier.size(28.dp))
            Text(
                text = sec,
                fontFamily = PtFont,
                fontSize = 21.sp,
                color = backgroundColor,
                modifier = Modifier.padding(start = 5.dp,end=17.5.dp)
            )
        }
    }
}

@Preview
@Composable
fun MainUiDisplay() {
//    NoteUi({},{},{},{})
}