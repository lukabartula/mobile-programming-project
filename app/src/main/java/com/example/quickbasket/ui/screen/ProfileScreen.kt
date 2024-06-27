package com.example.quickbasket.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quickbasket.R
import com.example.quickbasket.viewModel.FavouritesViewModel
import com.example.quickbasket.viewModel.ProductViewModel
import com.example.quickbasket.viewModel.UserViewModel
import com.example.quickbasket.viewModel.toUsers
import kotlinx.coroutines.launch


@SuppressLint("SuspiciousIndentation")
@Composable
fun ProfileScreen(
        modifier: Modifier = Modifier,
        navController: NavController,
        userViewModel: UserViewModel,
        favouritesViewModel: FavouritesViewModel) {

    val userUiState = userViewModel.userUiState
    val favouritesUiState = favouritesViewModel.favourites.collectAsState()
    val favourites = favouritesUiState.value
    Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.goback_arrow),
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
                Text(
                    text = "Your Profile",
                    color = Color.Black,
                    style = TextStyle(fontSize = 24.sp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 32.dp),
                    textAlign = TextAlign.Center
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 60.dp), // Adjust this padding as needed
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_image),
                    contentDescription = "image 6",
                    modifier = Modifier
                        .height(200.dp)
                        .width(200.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(shape = RoundedCornerShape(100.dp))
                )
                Text(
                    text = userUiState.usersDetails.name,
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 15.sp
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                Text(
                    text = userUiState.usersDetails.email,
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 15.sp
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                Divider(
                    thickness = 1.dp, color = Color.Black, modifier = Modifier
                        .width(300.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Favourites",
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 15.sp
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                LazyRow {
                    items(favourites) {

                            item ->
                        Spacer(modifier = Modifier.width(10.dp))
                        ProductItem(navController = navController, product = item)
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedButton(
                        onClick = { navController.navigate("editProfile") },
                        shape = RoundedCornerShape(25.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff97300)),
                        contentPadding = PaddingValues(horizontal = 41.dp, vertical = 4.dp),
                        border = BorderStroke(1.dp, Color.Black),
                        modifier = Modifier
                            .requiredWidth(width = 153.dp)
                            .requiredHeight(height = 35.dp)
                    )

                    {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                10.dp,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .requiredWidth(width = 153.dp)
                                .requiredHeight(height = 35.dp)
                        ) {
                            Text(
                                text = "Edit profile",
                                color = Color.White,
                                style = TextStyle(
                                    fontSize = 15.sp
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    OutlinedButton(
                        onClick = {
                            userViewModel.viewModelScope.launch{
                                userViewModel.deleteUser()
                            }
                            navController.navigate("welcome") },
                        shape = RoundedCornerShape(25.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff97300)),
                        contentPadding = PaddingValues(horizontal = 41.dp, vertical = 4.dp),
                        border = BorderStroke(1.dp, Color.Black),
                        modifier = Modifier
                            .requiredWidth(width = 153.dp)
                            .requiredHeight(height = 35.dp)
                    )

                    {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                10.dp,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .requiredWidth(width = 153.dp)
                                .requiredHeight(height = 35.dp)
                        ) {
                            Text(
                                text = "Delete profile",
                                color = Color.White,
                                style = TextStyle(
                                    fontSize = 15.sp
                                )
                            )
                        }
                    }
                }

                OutlinedButton(
                    onClick = {
                        userViewModel.logout()
                        favouritesViewModel.clearFavourites()
                        navController.navigate("welcome")
                    },
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff97300)),
                    contentPadding = PaddingValues(horizontal = 41.dp, vertical = 4.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .requiredWidth(width = 153.dp)
                        .requiredHeight(height = 35.dp)
                ) {
                    Text(
                        text = "Sign out",
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 15.sp
                        )
                    )
                }

            }
        }
    }

