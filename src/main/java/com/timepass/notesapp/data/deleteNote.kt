package com.timepass.notesapp.data

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


suspend fun deleteNote(docId:String){
    val db = Firebase.firestore
        .collection("Notes")
    db.document(docId)
        .delete()
        .await()
}