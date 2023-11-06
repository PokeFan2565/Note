package com.arukusoft.note.ui.theme.screenLogics

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.arukusoft.note.ui.theme.firebase.saveNote
import com.arukusoft.note.ui.theme.firebase.updateNote
import com.arukusoft.note.ui.theme.navigation.Screen

fun updateMyNotes(
    context: Context,
    userId: String,
    navHostController: NavHostController,
    cardId:String,
    title: String,
    description: String
) {
    val currentDate = getCurrentDateInLocalFormat()
    val isSuccess = updateNote(
        context = context,
        userId = userId,
        id = cardId,
        title = title,
        description = description,
        date = currentDate
    )
    if (isSuccess) {
        Toast.makeText(context, "Note Updated SuccessFully", Toast.LENGTH_SHORT).show()
        navHostController.navigate(Screen.Main.route)
    }
}