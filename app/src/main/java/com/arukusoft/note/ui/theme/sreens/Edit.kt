package com.arukusoft.note.ui.theme.sreens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.arukusoft.note.ui.theme.firebase.getUserInfo
import com.arukusoft.note.ui.theme.firebase.saveNote
import com.arukusoft.note.ui.theme.screenLogics.LoadingHandler
import com.arukusoft.note.ui.theme.screenLogics.getCurrentDateInLocalFormat
import com.arukusoft.note.ui.theme.screenLogics.saveMyNotes
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(context: Context, navHostController: NavHostController) {

    // All Veriable That I Used In This Screen
    var title: String by remember { mutableStateOf("") }
    var description: String by remember { mutableStateOf("") }

    fun getTitle(): String {
        return title
    }

    var userId by remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        userId = FirebaseAuth.getInstance().currentUser!!.uid
    }

    // End Veriable Area


    // Scaffold Area
    LayoutScreen(
        title = "Add Notes",
        icon = Icons.Default.Done,
        onClick = {
            saveMyNotes(
                context = context,
                userId = userId,
                navHostController = navHostController,
                title = title,
                description = description
            )
        }) {
        // Loading Screen Handeling Are
        LoadingHandler(userName = userId, loading = { LoadingScreen() }) {
            // Main Body Start
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(top = 64.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier.padding(top = 0.dp)
                ) {

                    // For Title
                    TextField(
                        value = title, onValueChange = {
                            title = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = "Edit Icon"
                            )

                        },
                        placeholder = {
                            Text(
                                text = "Enter Title",
                                fontSize = 16.sp,
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        )
                    )
                    // End Title Area

                    Spacer(modifier = Modifier.height(2.dp))

                    // For Description
                    TextField(
                        value = description, onValueChange = {
                            description = it
                        },
                        modifier = Modifier.fillMaxSize(),

                        placeholder = {
                            Text(
                                text = "Enter Descriptions",
                                fontSize = 16.sp,
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Default
                        )
                    )
                    // End Descriptions Area
                }
            }
            // End Main Body
        }
        // End Loading Screen Handeling
    }
    // End Scaffhold Area


}

// Logic


