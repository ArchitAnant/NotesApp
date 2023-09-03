package com.timepass.notesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timepass.notesapp.data.Notes
import com.timepass.notesapp.data.addNotes
import com.timepass.notesapp.data.deleteNote
import com.timepass.notesapp.data.generateTimeStamp
import com.timepass.notesapp.data.getNotes
import com.timepass.notesapp.data.updateNote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModel:ViewModel() {
    private val _listOfNotes = MutableStateFlow<MutableList<Notes>>(mutableListOf())
    val listOfNotes = _listOfNotes.asStateFlow()
    init {
        viewModelScope.launch {
            _listOfNotes.value = getNotes()
        }
    }
    fun addNote(note:Notes){
        viewModelScope.launch {
            addNotes(note)
            _listOfNotes.value = getNotes()
        }
    }
    fun deleteNotes(docId:String){
        viewModelScope.launch {
            deleteNote(docId)
            _listOfNotes.value = getNotes()
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
            _listOfNotes.value= getNotes()
        }
    }
}