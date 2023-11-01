package com.arukusoft.note.ui.theme.screenLogics

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.arukusoft.note.ui.theme.navigation.Screen

fun openAddScreen(navHostController: NavHostController){
    navHostController.navigate(Screen.Edit.route)
}

@Composable
fun LoadingHandler(userName:String, loading:@Composable () -> Unit, content:@Composable () -> Unit){
    return if (userName.isNullOrBlank()){
        loading()
    }else{
        content()
    }
}