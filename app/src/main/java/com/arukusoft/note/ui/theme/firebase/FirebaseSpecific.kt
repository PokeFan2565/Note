package com.arukusoft.note.ui.theme.firebase

import android.util.Log
import com.arukusoft.note.ui.theme.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private val user = FirebaseAuth.getInstance().currentUser?.uid
private lateinit var databaseReference: DatabaseReference
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
        userReference.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {


                if (snapshot.exists()){
                    val name: String? = snapshot.child("name").getValue(String::class.java)
                    val email: String? = snapshot.child("email").getValue(String::class.java)
                    val password: String? = snapshot.child("password").getValue(String::class.java)
                    if (name != null || email != null || password != null){
                        val userModel = UserModel(name.toString(), email.toString(), password.toString())
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



