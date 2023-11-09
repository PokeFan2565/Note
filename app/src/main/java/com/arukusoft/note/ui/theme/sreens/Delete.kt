package com.arukusoft.note.ui.theme.sreens

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.arukusoft.note.ui.theme.firebase.deleteNote
import com.arukusoft.note.ui.theme.navigation.Screen

@Composable
fun DeleteNoteScreen(context: Context, navHostController: NavHostController, id:String) {
    LaunchedEffect(key1 = Unit){
        val isSuccess = deleteNote(context = context, id = id)
        if (isSuccess){
            Toast.makeText(
                context,
                "Note Deleted Sucessfully",
                Toast.LENGTH_SHORT
            )
                .show()
            navHostController.navigate(Screen.Home.route)
        }else{
            Toast.makeText(
                context,
                "Somthing Went Wrong",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }
    LoadingScreen(massage = "Deleting")

}