package com.arukusoft.note.ui.theme.firebase

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.arukusoft.note.ui.theme.models.Cardmodel
import com.arukusoft.note.ui.theme.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
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
private lateinit var notesReference: DatabaseReference
val auth = FirebaseAuth.getInstance()

var userDetails = mutableMapOf<String, String>()
val noteList = mutableListOf<Cardmodel?>()



fun getUserId(): String? {
    return user
}

fun getNotes(callback: (MutableList<Cardmodel?>) -> Unit){
    notesReference = FirebaseDatabase.getInstance().reference
    val loginUser = auth.currentUser
    loginUser.let { userId ->
        val noteReference = notesReference.child("Notes").child(userId!!.uid)
        noteReference.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    noteList.clear()
                    for (note in snapshot.children){
                        val myNote = note.getValue(Cardmodel::class.java)
                        myNote.let {
                            noteList.add(it)
                        }

                    }
                    Log.d("noob", "onDataChange: $noteList")
                    callback(noteList)

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
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

fun saveNote(context: Context, userId:String, title: String, description: String, date: String): Boolean {
    database = Firebase.database.reference
    val myNote = Cardmodel(title, description, date)
    var isSuccess = false
    if (userId != null) {
        if (title.isBlank() || description.isBlank()) {
            Toast.makeText(context, "Please Fill The All Feild", Toast.LENGTH_SHORT).show()

        }else{
            database.child("Notes").child(userId).child(title).setValue(myNote)
            isSuccess = true

        }

    }else{
        Log.d("nullUser", "saveNote: Anonymuse User Found")
    }
    return isSuccess
}



