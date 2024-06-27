package com.example.quickbasket.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quickbasket.R
import com.example.quickbasket.ui.theme.Orange
import com.example.quickbasket.viewModel.UserViewModel


@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    userViewModel: UserViewModel
    ) {
    Box(
        modifier
            .fillMaxSize().
            background(color = Orange),
        contentAlignment = Alignment.Center

    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logo), // Replace with your app logo
                contentDescription = "App Logo",
                modifier
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(100.dp))
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Quick Basket", // Replace with your app name
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Explore fresh groceries at your fingertips!",
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(48.dp))
            Button(
                onClick = { if(!userViewModel.isLoggedIn())(navController.navigate("signup"))
                else navController.navigate("catalogue") },
                colors = ButtonDefaults.buttonColors(Color.Black),
            ) {
                Text(text = "Get Started!")
            }
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    //WelcomeScreen(navController = NavController)
}