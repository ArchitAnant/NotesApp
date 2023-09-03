package com.timepass.notesapp.data

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

suspend fun addNotes(note: Notes){
    val db =Firebase.firestore
    db.collection("Notes")
        .add(note)
        .addOnSuccessListener {
            Log.d("addNotes", "DocumentSnapshot added with ID: ${it.id}")
        }
        .addOnFailureListener{
            Log.d("addData","Failed")
        }
        .await()
}