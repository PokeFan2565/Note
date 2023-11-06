package com.arukusoft.note.ui.theme.navigation

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arukusoft.note.ui.theme.firebase.getUserId
import com.arukusoft.note.ui.theme.screenLogics.openAddScreen
import com.arukusoft.note.ui.theme.sreens.EditScreen
import com.arukusoft.note.ui.theme.sreens.LayoutScreen
import com.arukusoft.note.ui.theme.sreens.LoginScreen
import com.arukusoft.note.ui.theme.sreens.NotesScreen
import com.arukusoft.note.ui.theme.sreens.UpdateNoteScreen
import com.arukusoft.note.ui.theme.sreens.UserRegisterScreen

@Composable
fun MainNavigation(context: Context) {
    val navHostController = rememberNavController()
    val userId = getUserId()
    NavHost(navController = navHostController, startDestination = Screen.Home.route) {
        composable(Screen.Auth.route) {
            UserRegisterScreen(context = context, navHostController)
        }
        composable(Screen.Main.route) {
            LayoutScreen(
                title = "Notes",
                icon = Icons.Default.Add,
                onClick = { openAddScreen(navHostController) }) {
                NotesScreen(navHostController) {
                    if (it != null) {
                        navHostController.navigate("${Screen.Update.route}/${it.title}/${it.description}/${it.id}")
                    }
                }
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
                    NotesScreen(navHostController) {
                        if (it != null) {
                            navHostController.navigate("${Screen.Update.route}/${it.title}/${it.description}/${it.id}")
                        }
                    }
                }
            }
        }
        composable(Screen.LogIn.route) {
            LoginScreen(context, navHostController)
        }
        composable(Screen.Edit.route) {
            EditScreen(context, navHostController)
        }
        composable("${Screen.Update.route}/{cardTitle}/{cardDescription}/{cardId}",
            arguments = listOf(
                navArgument(name = "cardTitle") {
                    type = NavType.StringType
                },
                navArgument(name = "cardDescription") {
                    type = NavType.StringType
                },
                navArgument(name = "cardId") {
                    type = NavType.StringType
                }
            )) {
            val cardTitle = it.arguments!!.getString("cardTitle")
            val cardDescription = it.arguments!!.getString("cardDescription")
            val cardId = it.arguments!!.getString("cardId")
            UpdateNoteScreen(
                navHostController = navHostController,
                userId = userId!!,
                context = context,
                cardTitle = cardTitle!!,
                cardDescription = cardDescription!!,
                cardId = cardId!!
            )
        }

    }

}

sealed class Screen(val route: String) {
    object Auth : Screen("auth")
    object Home : Screen("home")
    object LogIn : Screen("login")
    object Main : Screen("main")
    object Edit : Screen("edit")
    object Update : Screen("update")
}