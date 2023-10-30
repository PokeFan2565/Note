package com.arukusoft.note.ui.theme.sreens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.arukusoft.note.R
import com.arukusoft.note.ui.theme.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// Firebase Specific Veriable
private lateinit var auth: FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(context: Context, navHostController: NavHostController){

    // User Specific Veriable
    var email:String by remember { mutableStateOf("") }
    var password:String by remember { mutableStateOf("") }




    // For Controlling Keyboard
    val controller = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column (
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(painter = painterResource(id = R.drawable.ladyregister),
            contentDescription = "Register Png",
            modifier = Modifier.size(100.dp, 100.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Log In",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold)
            Spacer(
                modifier = Modifier.height(20.dp))

            // getting Email From User
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Email,
                        contentDescription = "Icons")
                },
                label = {
                    Text(text = "Email")
                },
                placeholder = {
                    Text(text = "Enter Your Email")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                ),

                modifier = Modifier.fillMaxWidth()

                )

            Spacer(modifier = Modifier.height(10.dp))

            // Getting Password Input From User
            OutlinedTextField(value = password, onValueChange = {
                password = it
            },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Lock, contentDescription = "Password Icon")
                },
                label = {
                    Text(text = "Password")
                },
                placeholder = {
                    Text(text = "Enter Your Password")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth(),
                keyboardActions = KeyboardActions(
                    onDone = {
                        controller?.hide()
                    }
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Creating A Button For Login
            Box(
                modifier = Modifier.border(2.dp, color = Color.Blue)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            loginUser(context = context,navHostController, email, password)
                        }
                        .padding(horizontal = 45.dp, vertical = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }

            // privecy Policy Aletr
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "By Clicking Continue You Are Agree To Ower Privecy Policy.",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall)
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "New User? ",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(text = "Create Account",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Blue,
                    modifier = Modifier.clickable { navHostController.navigate(Screen.Auth.route) }
                )
            }
        }

    }
}

fun loginUser(context: Context,navHostController: NavHostController, email:String, password:String) {
    // Inisializing Firebase Specific Veriable
    auth = Firebase.auth

    // Parsing Inputs
    val userEmail = email.trim()
    val userPassword = password.trim()

    if (userEmail.isBlank() || userPassword.isBlank()){
        Toast.makeText(context, "Please Fill All The Feild", Toast.LENGTH_SHORT).show()

    }else{
        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener{
            if (it.isSuccessful){
                Toast.makeText(context, "Login SuccessFull", Toast.LENGTH_SHORT).show()
                navHostController.navigate(Screen.Main.route)
            }else{
                Toast.makeText(context, "${it.exception?.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowLoginScreen(){
    //LoginScreen()
}