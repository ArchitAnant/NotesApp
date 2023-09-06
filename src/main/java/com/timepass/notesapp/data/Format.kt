package com.timepass.notesapp.data

data class Format(
    val collegeList:MutableList<Notes>,
    val personalList:MutableList<Notes>,
    val professionalList:MutableList<Notes>,
    val randomList:MutableList<Notes>,
    val shuffleList:MutableList<Notes>
)
