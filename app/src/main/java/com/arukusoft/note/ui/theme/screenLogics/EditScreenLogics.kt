package com.arukusoft.note.ui.theme.screenLogics

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.arukusoft.note.ui.theme.firebase.saveNote
import com.arukusoft.note.ui.theme.navigation.Screen
import com.arukusoft.note.ui.theme.sreens.EditScreen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun saveMyNotes(context: Context, navHostController: NavHostController, title:String, description:String){
    val currentDate = getCurrentDateInLocalFormat()
    val isSuccess = saveNote(context = context, title = title, description = description, date = currentDate)
    if (isSuccess){
        Toast.makeText(context, "Note Added SuccessFully", Toast.LENGTH_SHORT).show()
        navHostController.navigate(Screen.Main.route)
    }
}

fun getCurrentDateInLocalFormat(): String {
    val currentDate = Date()
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return dateFormat.format(currentDate)
}