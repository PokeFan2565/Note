package com.arukusoft.note.ui.theme.sreens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arukusoft.note.ui.theme.firebase.getUserInfo


data class Note(
    val title: String? = null,
    val description: String? = null,
    val date: String? = null
)

val notList: List<Note>
    get() = listOf(
        Note(
            "\"Exploring the Enchanting Beauty of Kyoto\"",
            "Discover the timeless allure of Kyoto, a city that effortlessly blends traditional Japanese culture with modern sophistication. Nestled in the heart of Japan, Kyoto is a captivating destination that promises a journey through time. From its ancient temples and shrines to its bustling streets and vibrant markets, this city has something for every traveler.\n" +
                    "\n" +
                    "Unearth the secrets of Kyoto's historic districts, where well-preserved machiya houses transport you back to the Edo period. Wander through the stunning bamboo groves of Arashiyama and visit the iconic Fushimi Inari Shrine, with its thousands of vermilion torii gates leading up the sacred Mount Inari. Don't miss the serene beauty of the Golden Pavilion, Kinkaku-ji, and the rock garden of Ryoan-ji, where Zen tranquility reigns.\n" +
                    "\n" +
                    "Kyoto's culinary scene is equally tantalizing. Savor the delicate flavors of kaiseki cuisine, indulge in freshly made matcha tea and sweets in traditional teahouses, or feast on street food at Nishiki Market. The city offers a fascinating blend of ancient traditions and contemporary innovations, making it an enriching experience for all.\n" +
                    "\n" +
                    "Explore the enchanting beauty of Kyoto, where every corner is a piece of art, and every moment is a step into history. Embrace the culture, soak in the natural beauty, and let Kyoto cast its enchanting spell on you.",
            "25/07/2000"
        )
    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen() {



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
                items(notList) {
                    Card(
                        onClick = { },
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 10.dp)
                        ) {
                            // For Card title
                            Text(
                                text = it.title.toString(),
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
                                    text = it.description.toString(),
                                    maxLines = 2,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Medium,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            // For Date
                            Text(
                                text = "Date : ${it.date.toString()}",
                                maxLines = 2,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Medium,
                                fontStyle = FontStyle.Italic,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
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

@Preview(showBackground = true)
@Composable
fun Show() {
    NotesScreen()
}