package com.agro.kilimo.ui.theme.screens.splash



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.agro.kilimo.R
import com.agro.kilimo.navigation.ROUT_LOGIN
import kotlinx.coroutines.delay

@Composable
fun splash_sreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(ROUT_LOGIN)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Magenta)
    ) {
        Image(
            contentDescription = "splash",
            modifier = Modifier.size(150.dp),
            painter = painterResource(id = R.drawable.tomato)
        )
    }
}

@Preview
@Composable
private fun splash_prev() {
    splash_sreen(rememberNavController())
}