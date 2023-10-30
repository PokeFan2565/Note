package com.arukusoft.note.ui.theme.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arukusoft.note.ui.theme.firebase.getUserId
import com.arukusoft.note.ui.theme.sreens.LoginScreen
import com.arukusoft.note.ui.theme.sreens.NotesScreen
import com.arukusoft.note.ui.theme.sreens.UserRegisterScreen

@Composable
fun MainNavigation(context: Context){
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = Screen.Home.route ){
        composable(Screen.Auth.route){
            UserRegisterScreen(context = context, navHostController)
        }
        composable(Screen.Main.route){
            NotesScreen()
        }
        composable(Screen.Home.route){
            if (getUserId().isNullOrBlank()){
                LoginScreen(context = context, navHostController = navHostController)
            }else{
                NotesScreen()
            }
        }
        composable(Screen.LogIn.route){
            LoginScreen(context, navHostController)
        }

    }

}

sealed class Screen(val route:String) {
    object Auth:Screen("auth")
    object Home:Screen("home")
    object LogIn:Screen("login")
    object Main:Screen("main")
}