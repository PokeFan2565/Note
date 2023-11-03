package com.arukusoft.note.ui.theme.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNodeLifecycleCallback
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.arukusoft.note.ui.theme.firebase.getUserInfo

data class Common(
    val navHostController: NavHostController
)


