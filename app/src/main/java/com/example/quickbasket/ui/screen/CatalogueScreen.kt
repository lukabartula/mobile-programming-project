package com.example.quickbasket.ui.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quickbasket.R
import com.example.quickbasket.data.models.Products
import com.example.quickbasket.ui.theme.Orange
import com.example.quickbasket.viewModel.ProductViewModel
import com.example.quickbasket.viewModel.UserViewModel


@Composable
fun CatalogueScreen(
    navController: NavController,
    modifier : Modifier = Modifier,
    userViewModel: UserViewModel,
    productViewModel: ProductViewModel) {
    //val products = viewModel.products.observeAsState(initial = emptyList())
    val productsStateUi = productViewModel.products.collectAsState()
    val products = productsStateUi.value
    Log.d("CatalogueScreen", "products: $products")



    Box(
        modifier
            .fillMaxSize()
            .background(Color.White)
    ){

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 16.dp)) {
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
                text = "Catalogue",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 24.sp
                ),
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .weight(1f),
                textAlign = TextAlign.Center
            )
            IconButton(
                onClick = { navController.navigate("profile") }
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = Color.Black
                )
            }

        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier.padding(
                top = 80.dp)
        ) {
            items(products.size) {index ->
                ProductItem(navController = navController, product = products[index], modifier = Modifier.padding(8.dp))
            }
        }
    }
}


@Composable
fun ProductItem(navController: NavController,
    modifier: Modifier = Modifier,
    product: Products = Products(id = 0, name = "", description = "", price = 0, imageResId = R.drawable.bananas)
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
            contentColor = Color.White
        ),
        modifier = modifier
            .clip(shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
            .requiredWidth(width = 150.dp)
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(
                    bottomStart = 15.dp,
                    bottomEnd = 15.dp,
                    topStart = 15.dp,
                    topEnd = 15.dp
                )
            )
    ) {
        Column(
            modifier = Modifier
                .requiredHeight(height = 200.dp)
        ) {
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = "product image",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .requiredWidth(width = 150.dp)
                    .requiredHeight(height = 100.dp)
                    .border(
                        BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                    )
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .requiredWidth(width = 150.dp)
                    .requiredHeight(height = 97.dp)
                    .background(color = Color.Black)
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
            ) {
                Text(
                    text = product.name,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 15.sp))
                Text(
                    text = "$${product.price}",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 10.sp))
                Button(
                    onClick = { navController.navigate("singleProduct/${product.id}") },
                    colors = ButtonDefaults.buttonColors(Orange)
                ){
                    Text(
                        text = "View",
                        modifier
                            .requiredWidth(width = 70.dp)
                            .requiredHeight(height = 30.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

