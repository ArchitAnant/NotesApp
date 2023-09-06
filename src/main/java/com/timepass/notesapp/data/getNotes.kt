package com.timepass.notesapp.data

import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

suspend fun getNotes():Format{
    val tempNotesList = Format(mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(),
        mutableListOf()
    )
    val db = Firebase.firestore
        .collection("Notes")

    db.get().addOnSuccessListener {notes->
        val list = notes.documents
        for (note in list){
            var tag = note.get("tag").toString()
            val temp  = Notes(
                name = note.get("name").toString(),
                detail = note.get("detail").toString(),
                time = note.get("time").toString(),
                docId = note.id,
                tag = note.get("tag").toString()
            )
            when(tag){
                "college"-> tempNotesList.collegeList.add(temp)
                "professional"-> tempNotesList.professionalList.add(temp)
                "personal"-> tempNotesList.personalList.add(temp)
                "random"-> tempNotesList.randomList.add(temp)
            }
            tempNotesList.shuffleList.add(Notes(
                name = note.get("name").toString(),
                detail = note.get("detail").toString(),
                time = note.get("time").toString(),
                docId = note.id,
                tag = note.get("tag").toString()
            ))

        }
    }
        .addOnFailureListener {

        }
        .await()
    return tempNotesList
}