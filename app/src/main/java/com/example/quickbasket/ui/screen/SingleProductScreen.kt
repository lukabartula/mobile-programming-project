package com.example.quickbasket.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.quickbasket.R
import com.example.quickbasket.ui.theme.Orange
import com.example.quickbasket.viewModel.FavouritesViewModel
import com.example.quickbasket.viewModel.ProductViewModel
import com.example.quickbasket.viewModel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun SingleProductScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    userViewModel: UserViewModel,
    productViewModel: ProductViewModel,
    favouritesViewModel: FavouritesViewModel,
    productId: String?
) {

    //val product = repository.getOneStream(productId).collectAsState(initial = null).value
    val products = productViewModel.products.collectAsState()
    val product = products.value.find { it.id == productId?.toInt() }
    val userId = userViewModel.userUiState.usersDetails.id


    if (product != null) {

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 40.dp)){
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
                    text = product.name,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f).padding(end = 36.dp)
                )
            }
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = product.name,
                modifier = Modifier
                    .size(260.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(2.dp, Color(0xFFF97300), RoundedCornerShape(10.dp))
            )
            Text(
                text = product.description,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 25.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Price: $${product.price}", fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 15.dp)
                )
            }
            Button(
                onClick = {
                    favouritesViewModel.viewModelScope.launch {
                        favouritesViewModel.addToFavourites(
                            userId = userId,
                            productId = product.id
                        )

                    }
                    navController.popBackStack()
                    Toast.makeText(
                        navController.context,
                        "Added to Favourites",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                colors = ButtonDefaults.buttonColors(Orange),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .height(50.dp)
            ) {
                Text(text = "Add to Favourites", fontSize = 16.sp, color = Color.White)
            }
        }
    }
}

