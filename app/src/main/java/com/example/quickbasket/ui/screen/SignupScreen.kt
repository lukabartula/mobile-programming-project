package com.example.quickbasket.ui.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quickbasket.R
import com.example.quickbasket.viewModel.UserViewModel
import kotlinx.coroutines.launch



@Composable
fun SignupScreen(modifier: Modifier = Modifier,
                 navController: NavController,
                 userViewModel: UserViewModel
                 ) {
    val userUiState = userViewModel.userUiState
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var errorMessage by remember {
        mutableStateOf<String?>(null)
    }
    var showToast by remember {
        mutableStateOf(false)
    }

    if(showToast) {
        Toast.makeText(context, "User signed up successfully", Toast.LENGTH_SHORT).show()
        showToast = false
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .requiredWidth(390.dp)
                .requiredHeight(844.dp)
                .background(Color.White)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 48.dp)){
                IconButton(
                    onClick = { navController.popBackStack() }

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.goback_arrow),
                        contentDescription = "Back"
                    )
                }

                Text(
                    text = "Sign up",
                    color = Color.Black,
                    style = TextStyle(fontSize = 35.sp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 36.dp),
                    textAlign = TextAlign.Center

                )
            }
            Text(
                text = "Full name",
                color = Color.Black,
                style = TextStyle(fontSize = 15.sp),
                modifier = Modifier
                    .offset(x = 40.dp, y = 110.dp)
            )
            TextField(
                value = name,
                onValueChange = { name = it
                                userViewModel.updateUiState(userUiState.usersDetails.copy(name = it))
                                },
                placeholder = { Text("Enter your name") },
                modifier = Modifier
                    .offset(x = 31.dp, y = 115.dp)
                    .requiredWidth(320.dp)
                    .requiredHeight(52.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color.White)
                    .border(BorderStroke(1.5.dp, Color(0xfff97300)), RoundedCornerShape(25.dp)),
                textStyle = TextStyle(color = Color.Black)
            )
            Text(
                text = "Email",
                color = Color.Black,
                style = TextStyle(fontSize = 15.sp),
                modifier = Modifier
                    .offset(x = 40.dp, y = 120.dp)
            )
            TextField(
                value = email,
                onValueChange = { email = it
                                userViewModel.updateUiState(userUiState.usersDetails.copy(email = it))
                                },
                placeholder = { Text("Enter your email address") },
                modifier = Modifier

                    .offset(x = 31.dp, y = 122.dp)
                    .requiredWidth(320.dp)
                    .requiredHeight(52.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color.White)
                    .border(BorderStroke(1.5.dp, Color(0xfff97300)), RoundedCornerShape(25.dp)),
                textStyle = TextStyle(color = Color.Black)
            )
            Text(
                text = "Password",
                color = Color.Black,
                style = TextStyle(fontSize = 15.sp),
                modifier = Modifier
                    .offset(x = 40.dp, y = 130.dp)
            )
            TextField(
                value = password,
                onValueChange = { password = it
                                userViewModel.updateUiState(userUiState.usersDetails.copy(password = it))
                                },
                placeholder = { Text("Enter your password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .offset(x = 31.dp, y = 132.dp)
                    .requiredWidth(320.dp)
                    .requiredHeight(52.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color.White)
                    .border(BorderStroke(1.5.dp, Color(0xfff97300)), RoundedCornerShape(25.dp)),
                textStyle = TextStyle(color = Color.Black)
            )
            Text(
                text = "Repeat password",
                color = Color.Black,
                style = TextStyle(fontSize = 15.sp),
                modifier = Modifier
                    .offset(x = 40.dp, y = 140.dp)
            )
            TextField(
                value = repeatPassword,
                onValueChange = { repeatPassword = it },
                placeholder = { Text("Enter your password again") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .offset(x = 31.dp, y = 142.dp)
                    .requiredWidth(320.dp)
                    .requiredHeight(52.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color.White)
                    .border(BorderStroke(1.5.dp, Color(0xfff97300)), RoundedCornerShape(25.dp)),
                textStyle = TextStyle(color = Color.Black)
            )

            Button(
                onClick = {
                    if(password == repeatPassword){
                        coroutineScope.launch {
                            userViewModel.registerUser { success, message ->
                                if (success) {
                                    errorMessage = null
                                    showToast = true
                                } else {
                                    errorMessage = message
                                }
                            }
                        }
                        navController.navigate("signin")
                    } else {
                        errorMessage = "Passwords do not match"
                    }

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff97300)),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
                    .offset(x = 0.dp, y = 175.dp)
            ) {
                Text(
                    text = "Sign Up",
                    color = Color.White,
                    style = TextStyle(fontSize = 20.sp)
                )
            }

            Text(
                text = "Have an account?",
                color = Color.Black,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
                    .offset(x = (-40).dp, y = 70.dp)
            )

            Button(
                onClick = { navController.navigate("signin")},
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier

                    .padding(top = 8.dp)
                    .offset(x = 190.dp, y = 29.dp)
            ) {
                Text(
                    text = "Log In",
                    color = Color(0xfff97300),
                    style = TextStyle(fontSize = 14.sp)
                )
            }
        }
        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .offset(y = 50.dp)
            )
        }
    }
}
@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun SignupScreenPreview() {
    //SignupScreen()
}
