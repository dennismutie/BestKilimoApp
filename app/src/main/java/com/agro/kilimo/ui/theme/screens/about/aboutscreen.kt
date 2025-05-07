package com.agro.kilimo.ui.theme.screens.about


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.agro.kilimo.R

@Composable
fun AboutScreen(navController: NavHostController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "....Know more about Kilimo App",
            color = Color.Red.copy(alpha = 0.8f),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Image(
            contentDescription = "about",
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.watermelon)
        )
        Text(
            text = "Kilimo App is a mobile application designed to support farmers by providing agricultural information, tools, and services to improve productivity and sustainability. The main goal of Kilimo App is to empower farmers with timely, relevant, and actionable information to increase yields and income.",
            color = Color.Blue.copy(alpha = 0.8f),
            fontSize = 18.sp,
        )


        @Preview
        @Composable
        fun AboutPreview(rememberNavController: NavHostController) {
            AboutPreview(rememberNavController())
        }
    }
}