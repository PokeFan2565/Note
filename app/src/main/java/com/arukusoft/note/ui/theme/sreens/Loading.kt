package com.arukusoft.note.ui.theme.sreens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arukusoft.note.R

@Composable
fun LoadingScreen(massage: String = "Connecting...") {
    val lodingImg = painterResource(id = R.drawable.loading)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RotatingImage(painter = lodingImg)
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = massage,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Blue
            )
        }
    }
}

@Composable
fun RotatingImage(painter: Painter) {
    var rotationState by remember { mutableFloatStateOf(0f) }

    val rotationTransition = rememberInfiniteTransition(label = "")
    val rotationAnim by rotationTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    LaunchedEffect(rotationAnim) {
        rotationState = rotationAnim
    }

    Image(
        painter = painter,
        contentDescription = null, // Provide a meaningful description
        modifier = Modifier.graphicsLayer(rotationZ = rotationState)
    )
}

@Preview(showBackground = true)
@Composable
fun ShowScreen() {
    LoadingScreen()
}