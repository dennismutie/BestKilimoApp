package com.agro.kilimo.ui.theme.screens.about


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.agro.kilimo.R

@Composable
fun AboutScreen(navController: NavController){
    Text(
        text = "....Know more about Kilimo App",
        color = Color.White.copy(alpha = 0.8f),
        fontSize = 18.sp,
        textAlign = TextAlign.Center
    )
    Image(
        contentDescription = "about",
        modifier = Modifier.size(150.dp),
        painter = painterResource(id = R.drawable.watermelon)
    )
    Text(
        text = "Kilimo App is a mobile application designed to support farmers by providing agricultural information, tools, and services to improve productivity and sustainability. The main goal of Kilimo App is to empower farmers with timely, relevant, and actionable information to increase yields and income.",
        color = Color.White.copy(alpha = 0.8f),
        fontSize = 18.sp,)


}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview(){
    AboutScreen(rememberNavController())}