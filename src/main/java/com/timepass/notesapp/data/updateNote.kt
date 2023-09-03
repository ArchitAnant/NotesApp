package com.timepass.notesapp.data

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


suspend fun updateNote(docId:String,docTitle:String,docDetail:String,time:String){
    val db = Firebase.firestore
        .collection("Notes")
    db.document(docId)
        .update(
            mapOf(
                "name" to docTitle,
                "detail" to docDetail,
                "time" to time
            )
        )
        .await()
}