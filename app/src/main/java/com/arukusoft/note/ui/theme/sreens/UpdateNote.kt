package com.arukusoft.note.ui.theme.sreens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.arukusoft.note.ui.theme.models.Cardmodel
import com.arukusoft.note.ui.theme.screenLogics.getCurrentDateInLocalFormat
import com.arukusoft.note.ui.theme.screenLogics.saveMyNotes
import com.arukusoft.note.ui.theme.screenLogics.updateMyNotes
import kotlin.reflect.KProperty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateNoteScreen(navHostController: NavHostController, userId:String, context: Context, cardTitle:String, cardDescription: String, cardId:String) {
    // Veriable That I Used This Screen
    val screenTitle: String = "My Note"
    val screenIcon = Icons.Default.Done
    // End Veriable Are

    // UpdateScreen Content veriables

    val id:String = cardId
    var title: String by remember {
        mutableStateOf(cardTitle)
    }
    var description: String by remember {
        mutableStateOf(cardDescription)
    }
    // End UpdateScreen Content Veriable Are
    LayoutScreen(title = screenTitle, icon = screenIcon, onClick = { updateMyNotes(
        context = context,
        userId = userId,
        navHostController = navHostController,
        cardId = id,
        title = title,
        description = description,
    ) }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 65.dp)
                .background(Color.White),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // For Title Text Input
                TextField(value = title, onValueChange = {
                    title = it
                },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Blue, // Text color
                        containerColor = Color.White, // Background color
                        cursorColor = Color.Black // Cursor color
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    label = {
                        Text(text = "Title",
                            fontWeight = FontWeight.Medium,
                            color = Color.Blue)
                    }
                )
                // End Title Text Input

                // For Description Text Input
                TextField(value = description, onValueChange = {
                    description = it
                },
                    modifier = Modifier.fillMaxSize(),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black, // Text color
                        containerColor = Color.White, // Background color
                        cursorColor = Color.Black // Cursor color
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Default
                    )
                )
                // End Description Text Input
            }
        }
    }
}




