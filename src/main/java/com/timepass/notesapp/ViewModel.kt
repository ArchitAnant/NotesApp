package com.timepass.notesapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timepass.notesapp.data.Notes
import com.timepass.notesapp.data.addNotes
import com.timepass.notesapp.data.deleteNote
import com.timepass.notesapp.data.generateTimeStamp
import com.timepass.notesapp.data.getNotes
import com.timepass.notesapp.data.updateNote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class ViewModel:ViewModel() {
    private val _collegeList = MutableStateFlow<MutableList<Notes>>(mutableListOf())
    private val _professionalList = MutableStateFlow<MutableList<Notes>>(mutableListOf())
    private val _randomList = MutableStateFlow<MutableList<Notes>>(mutableListOf())
    private val _personalList = MutableStateFlow<MutableList<Notes>>(mutableListOf())
    private val _shuffleList = MutableStateFlow<MutableList<Notes>>(mutableListOf())

    val collegeList = _collegeList.asStateFlow()
    val personalList = _personalList.asStateFlow()
    val professionalList = _professionalList.asStateFlow()
    val randomList = _randomList.asStateFlow()
    val shuffleList = _shuffleList.asStateFlow()

    init {
        viewModelScope.launch {
            val tempFormat  = getNotes()
            _collegeList.value = tempFormat.collegeList
            _professionalList.value = tempFormat.professionalList
            _randomList.value = tempFormat.randomList
            _personalList.value = tempFormat.personalList
            _shuffleList.value = tempFormat.shuffleList
        }
    }
    fun addNote(note:Notes){
        viewModelScope.launch {
            addNotes(note)
            val tempFormat  = getNotes()
            _collegeList.value = tempFormat.collegeList
            _professionalList.value = tempFormat.professionalList
            _randomList.value = tempFormat.randomList
            _personalList.value = tempFormat.personalList
        }
    }
    fun deleteNotes(docId:String){
        viewModelScope.launch {
            deleteNote(docId)
            val tempFormat  = getNotes()
            _collegeList.value = tempFormat.collegeList
            _professionalList.value = tempFormat.professionalList
            _randomList.value = tempFormat.randomList
            _personalList.value = tempFormat.personalList
        }
    }
    fun updateNotes(docId:String,title:String,detail:String){
        viewModelScope.launch {
            updateNote(
                docId = docId,
                docTitle = title,
                docDetail = detail,
                time = generateTimeStamp()
            )
            val tempFormat  = getNotes()
            _collegeList.value = tempFormat.collegeList
            _professionalList.value = tempFormat.professionalList
            _randomList.value = tempFormat.randomList
            _personalList.value = tempFormat.personalList
        }
    }
}
