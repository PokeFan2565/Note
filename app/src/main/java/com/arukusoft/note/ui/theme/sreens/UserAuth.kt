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
import androidx.compose.material.icons.outlined.AccountBox
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.arukusoft.note.R
import com.arukusoft.note.ui.theme.models.UserModel
import com.arukusoft.note.ui.theme.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// Firebase Specific Veriabble
private lateinit var auth: FirebaseAuth
private lateinit var dataBase: DatabaseReference


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun UserRegisterScreen(context: Context, navHostController: NavHostController) {
    // Inisializing Remember Veriable
    var name: String by remember { mutableStateOf("") }
    var email: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }
    var conformPassword: String by remember { mutableStateOf("") }


    // Alert Specific Veriable
    var alertColor: Color by remember {
        mutableStateOf(Color.Red)
    }
    var alert: String by remember {
        mutableStateOf("")
    }

    // Inisializing Controller Veriable
    val controller = LocalSoftwareKeyboardController.current

    // Main Body Area
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {
        Column(
            modifier = Modifier
                .padding(vertical = 5.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ladyregister),
                contentDescription = "Register Png",
                modifier = Modifier.size(100.dp, 100.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Register Here",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
                //.border(2.dp, Color.Black)
            ) {

                // Getting Name Input From User
                OutlinedTextField(
                    value = name, onValueChange = {
                        name = it
                    },
                    placeholder = {
                        Text(text = "Enter Your Name")
                    },
                    label = {
                        Text(text = "Name")
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.AccountBox, contentDescription = "Icon")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Getting Email Input From User
                OutlinedTextField(
                    value = email, onValueChange = {
                        email = it
                    },
                    placeholder = {
                        Text(text = "Enter Your Email")
                    },
                    label = {
                        Text(text = "Email")
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Email, contentDescription = "Icon")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Getting Password Input From User
                OutlinedTextField(
                    value = password, onValueChange = {
                        password = it
                        // For Checking Password Matching In Runtime
                        if (password == conformPassword) {
                            alert = "Password Match 100%"
                            alertColor = Color.Green
                        } else {
                            alert = "Password Miss Match"
                            alertColor = Color.Red
                        }
                    },
                    placeholder = {
                        Text(text = "Enter Your Password")
                    },
                    label = {
                        Text(text = "Password")
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Password
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Lock, contentDescription = "Icon")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Getting Confirm Password Input From User
                OutlinedTextField(value = conformPassword, onValueChange = {
                    conformPassword = it
                    // For Checking Password Matching In Runtime
                    if (password == conformPassword) {
                        alert = "Password Match 100%"
                        alertColor = Color.Green
                    } else {
                        alert = "Password Miss Match"
                        alertColor = Color.Red
                    }
                },
                    placeholder = {
                        Text(text = "Confirm Your Password")
                    },
                    label = {
                        Text(text = "Confirm Password")
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            controller?.hide()
                        }
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Lock, contentDescription = "Icon")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = alert,
                    color = alertColor
                )
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (password == conformPassword) {
                                registerUser(context, navHostController, name, email, password)
                            } else {
                                Toast
                                    .makeText(context, "Password Not Matching", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                        .border(2.dp, Color.Black)
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Continue",
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                // privecy Policy Aletr
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "By Clicking Continue You Are Agree To Ower Privecy Policy.",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Already Have A Account? ",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(text = "Log-In Here",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Blue,
                        modifier = Modifier.clickable { navHostController.navigate(Screen.LogIn.route) }
                    )
                }

            }
        }
    }
}


fun registerUser(
    context: Context,
    navHostController: NavHostController,
    name: String,
    email: String,
    password: String
) {
    val userName = name.trim()
    val userEmail = email.trim()
    val userPassword = password.trim()

    // Inisialize FireBase Specific Veriable
    auth = Firebase.auth
    dataBase = Firebase.database.reference

    if (userName.isBlank() || userEmail.isBlank() || userPassword.isBlank()) {
        Toast.makeText(context, "Please Fill All The Fields", Toast.LENGTH_SHORT).show()
    } else {
        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener {
            if (it.isSuccessful) {

                // Toast Massage For SuccessFull Registration
                Toast.makeText(context, "Account Created SuccessFully ", Toast.LENGTH_SHORT).show()

                // For Creating A User Model
                val user = UserModel(userName, userEmail, userPassword)

                // For Getting User Id
                val userId = FirebaseAuth.getInstance().currentUser!!.uid

                // For Storing User InFo In Realtime Database
                dataBase.child("Users").child(userId).setValue(user)
                navHostController.navigate(Screen.LogIn.route)

            } else {
                Toast.makeText(context, "${it.exception?.localizedMessage}", Toast.LENGTH_LONG)
                    .show()

            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun ShowRegisterScreen() {
    //UserRegisterScreen()
}