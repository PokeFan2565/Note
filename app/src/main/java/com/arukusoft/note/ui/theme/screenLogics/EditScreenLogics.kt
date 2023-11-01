package com.arukusoft.note.ui.theme.screenLogics

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.arukusoft.note.ui.theme.firebase.saveNote
import com.arukusoft.note.ui.theme.sreens.EditScreen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale




fun getCurrentDateInLocalFormat(): String {
    val currentDate = Date()
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return dateFormat.format(currentDate)
}