package com.arukusoft.note.ui.theme.sreens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arukusoft.note.ui.theme.firebase.getUserInfo
import com.arukusoft.note.ui.theme.screenLogics.LoadingHandler

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutScreen(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {

    // To Get User Info
    var userName by remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        getUserInfo { userModel ->
            userName = userModel.name
        }
    }

    // End

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(title)
                },
                actions = {
                    ActionBar(userName)
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onClick,
                shape = CircleShape,
                containerColor = Color.Blue,
                contentColor = Color.White,
            ) {
                Icon(imageVector = icon, contentDescription = "")
            }
        }
    ) {
        LoadingHandler(userName = userName, loading = {LoadingScreen()}) {
            content()
        }
    }

}

@Composable
fun ActionBar(name: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(horizontal = 10.dp),
            color = Color.Blue,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        )
        Icon(
            imageVector = Icons.Default.AccountCircle, contentDescription = "",
            tint = Color.Blue,
            modifier = Modifier.size(32.dp)
        )
    }
}
