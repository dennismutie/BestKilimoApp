package com.agro.kilimo.ui.theme.screens.register


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.agro.kilimo.data.AuthViewModel
import com.agro.kilimo.navigation.ROUT_LOGIN



@Composable
fun RegisterScreen(navController: NavHostController) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue("")) }
    var confirmpass by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current

    Column(modifier=Modifier
        .fillMaxSize()
        .background(Color.Yellow),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(
//                Brush.verticalGradient(
//                    colors = listOf()
//                )
//            )
//            .padding(16.dp),
//        contentAlignment = Alignment.Center
//    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Title
            Text(
                text = "Create an Account",
                color = Color.Red, // Adjusted for better visibility
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Sign up to get started",
                color = Color.Red, // Adjusted for better contrast
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            // Email Input
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email Address", color = Color.Red) },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor =  Color.Blue,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.Black
                )
            )

            // Password Input
            TextField(
                value = pass,
                onValueChange = { pass = it },
                label = { Text(text = "Password", color = Color.Red) },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.Black
                )
            )

            // Confirm Password Input
            TextField(
                value = confirmpass,
                onValueChange = { confirmpass = it },
                label = { Text(text = "Confirm Password", color =Color.Blue) },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedTextColor = Color.Blue,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.Blue
                )
            )

            // Register Button
            Button(
                onClick = {
                    val myRegister = AuthViewModel(navController, context)
                    myRegister.signup(email.text.trim(), pass.text.trim(), confirmpass.text.trim())
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Register",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            // Login Button
            TextButton(
                onClick = { navController.navigate(ROUT_LOGIN) },
                modifier = Modifier.width(200.dp)
            ) {
                Text(
                    text = "Already have an account? Log in",
                    color = Color.Red,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun RegisterPreview() {
    RegisterScreen(rememberNavController())
}