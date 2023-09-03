package com.timepass.notesapp.data

import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

suspend fun getNotes():MutableList<Notes>{
    val tempNotesList = mutableListOf<Notes>()
    val db = Firebase.firestore
        .collection("Notes")

    db.get().addOnSuccessListener {notes->
        val list = notes.documents
        for (note in list){
            val temp  = Notes(
                name = note.get("name").toString(),
                detail = note.get("detail").toString(),
                time = note.get("time").toString(),
                docId = note.id,
            )
            tempNotesList.add(temp)
        }
    }
        .addOnFailureListener {

        }
        .await()
    return tempNotesList
}