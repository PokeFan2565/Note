package com.arukusoft.note.ui.theme.sreens

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
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(){
    var title:String by remember { mutableStateOf("") }
    var description:String by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize(),
            //.background(Color.Cyan),
        contentAlignment = Alignment.TopCenter
    ){
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add/Edit",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                modifier = Modifier
                    .fillMaxWidth(),
                    //.border(2.dp, Color.LightGray),
                color = Color.Black

            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(value = title , onValueChange = {
                title = it
            },
                modifier = Modifier.fillMaxWidth()
                    .border(2.dp, Color.Black),
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Edit, contentDescription = "Edit Icon")

                },
                placeholder = {
                    Text(text = "Enter Title",
                        fontSize = 16.sp,
                        )
                },
                label = {
                    Text(text = "Title",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            TextField(value = description , onValueChange = {
                description = it
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .border(2.dp, Color.Black)
                    .heightIn(max = 600.dp),
                placeholder = {
                    Text(text = "Enter Description",
                        fontSize = 16.sp,
                    )
                },
                label = {
                    Text(text = "Description",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 5.dp, bottomEnd = 5.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Cyan,
                                Color.White
                            )
                        )
                    )
                    .height(50.dp)
                    .clickable { }
                    .border(2.dp, Color.Black)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Outlined.Done, contentDescription = "",
                        tint = Color.Blue,
                        modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Save",
                        color = Color.Blue,
                        fontSize = 24.sp)
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreEditScreen(){
    EditScreen()
}