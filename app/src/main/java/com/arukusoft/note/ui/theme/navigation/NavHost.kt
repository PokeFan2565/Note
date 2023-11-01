package com.arukusoft.note.ui.theme.navigation

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arukusoft.note.ui.theme.firebase.getUserId
import com.arukusoft.note.ui.theme.screenLogics.openAddScreen
import com.arukusoft.note.ui.theme.sreens.EditScreen
import com.arukusoft.note.ui.theme.sreens.LayoutScreen
import com.arukusoft.note.ui.theme.sreens.LoginScreen
import com.arukusoft.note.ui.theme.sreens.NotesScreen
import com.arukusoft.note.ui.theme.sreens.UserRegisterScreen

@Composable
fun MainNavigation(context: Context) {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = Screen.Edit.route) {
        composable(Screen.Auth.route) {
            UserRegisterScreen(context = context, navHostController)
        }
        composable(Screen.Main.route) {
            LayoutScreen(
                title = "Notes",
                icon = Icons.Default.Add,
                onClick = { openAddScreen(navHostController) }) {
                NotesScreen()
            }
        }
        composable(Screen.Home.route) {
            if (getUserId().isNullOrBlank()) {
                LoginScreen(context = context, navHostController = navHostController)
            } else {
                LayoutScreen(
                    title = "Notes",
                    icon = Icons.Default.Add,
                    onClick = { openAddScreen(navHostController) }) {
                    NotesScreen()
                }
            }
        }
        composable(Screen.LogIn.route) {
            LoginScreen(context, navHostController)
        }
        composable(Screen.Edit.route) {
            LayoutScreen(
                title = "Add Note",
                icon = Icons.Default.Done,
                onClick = { }) {
                EditScreen()
            }
        }

    }

}

sealed class Screen(val route: String) {
    object Auth : Screen("auth")
    object Home : Screen("home")
    object LogIn : Screen("login")
    object Main : Screen("main")
    object Edit : Screen("edit")
}