 package com.agro.kilimo.ui.theme.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            contentDescription = "about",
            modifier = Modifier.width(600.dp),
            painter = painterResource(id = R.drawable.watermelon)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Kilimo App is a mobile application designed to support farmers by providing agricultural information, tools, and services to improve productivity and sustainability. The main goal of Kilimo App is to empower farmers with timely, relevant, and actionable information to increase yields and income.",
            color = Color.Blue.copy(alpha = 0.8f),
            fontSize = 30.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            contentDescription = "about",
            modifier = Modifier.width(1000.dp),
            painter = painterResource(id = R.drawable.tomato)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            contentDescription = "about",
            modifier = Modifier.width(1000.dp),
            painter = painterResource(id = R.drawable.orange)
        )





    }
}

@Preview(showBackground = true)
@Composable
fun AboutPreview() {
    AboutScreen(navController = rememberNavController())
}
