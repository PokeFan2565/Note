package com.arukusoft.note.ui.theme.firebase

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.arukusoft.note.ui.theme.models.Cardmodel
import com.arukusoft.note.ui.theme.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private val user = FirebaseAuth.getInstance().currentUser?.uid
private lateinit var databaseReference: DatabaseReference
private lateinit var database: DatabaseReference
val auth = FirebaseAuth.getInstance()

var userDetails = mutableMapOf<String, String>()

fun getUserId(): String? {
    return user
}


fun getUserInfo(callback: (UserModel) -> Unit) {

    databaseReference = FirebaseDatabase.getInstance().reference

    val currentUser = auth.currentUser
    currentUser?.let { userid ->
        val userReference = databaseReference.child("Users").child(userid.uid)
        userReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {


                if (snapshot.exists()) {
                    val name: String? = snapshot.child("name").getValue(String::class.java)
                    val email: String? = snapshot.child("email").getValue(String::class.java)
                    val password: String? = snapshot.child("password").getValue(String::class.java)
                    if (name != null || email != null || password != null) {
                        val userModel =
                            UserModel(name.toString(), email.toString(), password.toString())
                        callback(userModel)
                        Log.d("Sale40", "$userModel")
                    }


                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }
    Log.d("Sale5", "$userDetails")

}

fun saveNote(context: Context, title: String, description: String, date: String): Boolean {
    database = Firebase.database.reference
    val myNote = Cardmodel(title, description, date)
    var isSuccess = false
    if (user != null) {
        if (title.isBlank() || description.isBlank()) {
            Toast.makeText(context, "Please Fill The All Feild", Toast.LENGTH_SHORT).show()

        }else{
            database.child("Notes").child(user).child(title).setValue(myNote)
            isSuccess = true

        }

    }
    return isSuccess
}



