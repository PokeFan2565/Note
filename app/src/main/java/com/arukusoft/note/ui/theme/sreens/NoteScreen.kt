package com.arukusoft.note.ui.theme.sreens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.arukusoft.note.ui.theme.firebase.deleteNote
import com.arukusoft.note.ui.theme.firebase.getNotes
import com.arukusoft.note.ui.theme.firebase.getUserInfo
import com.arukusoft.note.ui.theme.models.Cardmodel
import com.arukusoft.note.ui.theme.navigation.Screen
import com.arukusoft.note.ui.theme.screenLogics.LoadingHandler
import kotlinx.coroutines.delay


data class Note(
    val title: String? = null,
    val description: String? = null,
    val date: String? = null
)

var noteList = mutableListOf<Cardmodel?>()
@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(context: Context,onDeleteClick: (String) -> Unit, onCardClick: (cardModel:Cardmodel?) -> Unit) {

    // To Get User Info
    var userName by remember { mutableStateOf("") }


    // End

    LaunchedEffect(key1 = Unit) {
        getNotes {
            noteList.clear()
            it.forEach { cardmodel ->
                noteList.add(cardmodel)

            }
            getUserInfo { userModel ->
                userName = userModel.name
            }
        }
    }

    LoadingHandler(userName = userName, loading = { EmptyNoteScreen() }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .border(1.dp, Color.Blue)
                .padding(top = 20.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column {


                Spacer(modifier = Modifier.height(10.dp))
                // Start Title Area
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)

                ) {
                    Text(
                        text = "Notes",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                // End Title Are
                Spacer(modifier = Modifier.height(10.dp))
                // Start Card Area
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        //.background(Color.LightGray)
                        .padding(horizontal = 20.dp, vertical = 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(noteList) { it ->
                        Card(
                            onClick = {onCardClick(it)},
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 10.dp)
                            ) {
                                // For Card title
                                Text(
                                    text = it?.title.toString(),
                                    maxLines = 1,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 18.sp,
                                    modifier = Modifier.fillMaxWidth(),
                                    color = Color.Blue
                                )
                                Spacer(modifier = Modifier.height(10.dp))

                                // For Card Description
                                Box(
                                    modifier = Modifier.padding(
                                        horizontal = 10.dp
                                    )
                                ) {
                                    Text(
                                        text = it?.description.toString(),
                                        maxLines = 2,
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Medium,
                                        fontStyle = FontStyle.Italic,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                // For Date And Delete Button
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Text(
                                        text = "Date : ${it?.date.toString()}",
                                        maxLines = 2,
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Medium,
                                        fontStyle = FontStyle.Italic,
                                        textAlign = TextAlign.End,
                                        modifier = Modifier.padding(end = 10.dp)
                                    )
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = null,
                                        tint = Red,
                                        modifier = Modifier.clickable {
                                            onDeleteClick(it!!.id.toString())
                                        }
                                        )
                                }

                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }

        // End Main Body
    }
}
@Composable
fun NotesLoading(noteList: MutableList<Cardmodel?>, loading:@Composable () -> Unit, content:@Composable () -> Unit){
    return if (noteList.isEmpty()){
        loading()
    }else{
        content()
    }
}

@Composable
fun TopTitleBar(userName:String) {
    // Start Top Bar
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            //.background(Color.LightGray)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Menu,
            contentDescription = "Menu",
            modifier = Modifier.size(40.dp),
            tint = Color.Blue
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = userName,
                modifier = Modifier.padding(horizontal = 5.dp),
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp
            )
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Circle Icon",
                tint = Color.Blue,
                modifier = Modifier.size(40.dp)
            )
        }

    }
    // End Top Bar
}

