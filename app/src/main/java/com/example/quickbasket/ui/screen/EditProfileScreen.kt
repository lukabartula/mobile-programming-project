package com.example.quickbasket.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quickbasket.R
import com.example.quickbasket.ui.theme.Orange
import com.example.quickbasket.viewModel.UserViewModel


@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    userViewModel: UserViewModel
    ) {
    val userUiState = userViewModel.userUiState
    var newName = remember { mutableStateOf(userUiState.usersDetails.name) }
    val newEmail = remember { mutableStateOf(userUiState.usersDetails.email) }
    val oldPassword = remember { mutableStateOf("") }
    val newPassword = remember { mutableStateOf("") }
    Box(
        modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Orange)
    ){
        Row(
            modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
        ){

            IconButton(
                onClick = {
                   navController.popBackStack()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.goback_arrow),
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(90.dp))
            Text(text = "Edit Profile",
                color = Color.Black,
                style = TextStyle(fontSize = 28.sp),
                modifier =Modifier.offset(x = -(20.dp), y = 8.dp)
            )


        }
        Column(
            modifier
                .padding(16.dp)
                .fillMaxWidth()
                .offset(y = 50.dp),
            content = {


            Spacer(modifier.height(8.dp))

            Text(text = "New name and surname")
            TextField(
                value = newName.value,
                onValueChange = { newName.value = it },
                modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "New Email Address")
            TextField(
                value = newEmail.value,
                onValueChange = { newEmail.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Old Password")
            TextField(
                value = oldPassword.value,
                onValueChange = { oldPassword.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(10.dp)),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "New Password")
            TextField(
                value = newPassword.value,
                onValueChange = { newPassword.value = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(10.dp)),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if(oldPassword.value == userUiState.usersDetails.password){
                        userViewModel.updateUserDetails(
                            newName.value,
                            newEmail.value,
                            newPassword.value
                        )
                        navController.popBackStack()
                    }else{
                        Toast.makeText(navController.context, "Old password is incorrect", Toast.LENGTH_SHORT).show()
                    }
                          },
                modifier
                    .requiredWidth(150.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text(
                    text = "Update Profile",
                    color = Color.Black,
                    style = TextStyle(fontSize = 15.sp)
                )
            }
        })
    }
}

@Preview
@Composable
private fun UpdateProfileScreenPreview() {
    //EditProfileScreen()
}