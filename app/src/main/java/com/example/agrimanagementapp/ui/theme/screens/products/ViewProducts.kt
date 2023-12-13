package com.example.agrimanagementapp.ui.theme.screens.products

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.agrimanagementapp.ui.theme.AgriManagementAppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.agrimanagementapp.R
import com.example.agrimanagementapp.data.ProductViewModel
import com.example.agrimanagementapp.models.Product
import com.example.agrimanagementapp.navigation.ACCOUNT_URL
import com.example.agrimanagementapp.navigation.ADD_PRODUCTS_URL
import com.example.agrimanagementapp.navigation.BUYNOW_URL
import com.example.agrimanagementapp.navigation.CONTACT_URL
import com.example.agrimanagementapp.navigation.HOME_URL
import com.example.agrimanagementapp.navigation.VIEW_PRODUCTS_URL


@Composable
fun ViewProductsScreen(navController: NavHostController){
    Column (modifier = Modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        var context = LocalContext.current
        var productRepository = ProductViewModel(navController, context)


        val emptyProductState = remember { mutableStateOf(Product("","","","","")) }
        var emptyProductsListState = remember { mutableStateListOf<Product>() }

        var products = productRepository.allProducts(emptyProductState, emptyProductsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageBrush =
                ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.hellocolor)))
            Text(
                text = "VIEW PRODUCTS",
                style = TextStyle(
                    brush = imageBrush,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp,
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(products){
                    ProductItem(
                        name = it.name,
                        quantity = it.quantity,
                        price = it.price,
                        id = it.id,
                        navController = navController,
                        productRepository = productRepository,
                        productImage = it.imageUrl
                    )
                }
            }
        }
    }
}


@Composable
fun ProductItem(name:String, quantity:String, price:String, id:String,
                navController:NavHostController, productRepository:ProductViewModel,productImage:String) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = name,
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold)
        Text(text = quantity,
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold)
        Text(text = price,
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold)
        Image(
            painter = rememberAsyncImagePainter(productImage),
            contentDescription = null,
            modifier = Modifier.size(250.dp))
        Button(onClick = {
            productRepository.deleteProduct(id)
        }, shape = CutCornerShape(10),
            border = BorderStroke(1.dp, Color.Magenta),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray)) {
            Text(text = "Delete")
        }
        Button(onClick = {
            //navController.navigate(ROUTE_UPDATE_PRODUCTS+"/$id")
        }, shape = CutCornerShape(10),
            border = BorderStroke(1.dp, Color.Magenta),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray)) {
            Text(text = "Update")
        }
        Button(onClick = {
            navController.navigate(CONTACT_URL)
        }, shape = CutCornerShape(10),
            border = BorderStroke(1.dp, Color.Magenta),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray)) {
            Text(text = "Contact Vendor")
        }
        Button(onClick = {
            navController.navigate(BUYNOW_URL)
        }, shape = CutCornerShape(10),
            border = BorderStroke(1.dp, Color.Magenta),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray)) {
            Text(text = "Buy Now")
        }
        Spacer(modifier = Modifier.height(40.dp))
        BottomAppBar(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth().padding(top = 40.dp)



        ) {
            IconButton(
                onClick = {
                    navController.navigate(HOME_URL)
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_home_24),
                    contentDescription = "home"
                )
//            Icon(imageVector = Icons.Default.home, contentDescription = )
                Text(
                    text = "Home",
                    modifier = Modifier.padding(top = 35.dp, bottom = 1.dp)
                )
            }
            IconButton(
                onClick = {
                    navController.navigate(VIEW_PRODUCTS_URL)
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_shopping_bag_24),
                    contentDescription = "view products"
                )
                Text(
                    text = "view ",
                    modifier = Modifier.padding(top = 35.dp, bottom = 1.dp)
                )
            }
            IconButton(
                onClick = {
                    navController.navigate(ADD_PRODUCTS_URL)
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_shopping_cart_24),
                    contentDescription = "add products"
                )
                Text(
                    text = "add ",
                    modifier = Modifier.padding(top = 40.dp, bottom = 1.dp)
                )
            }
            IconButton(
                onClick = {
                    navController.navigate(ACCOUNT_URL)
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_account_circle_24),
                    contentDescription = "Account"
                )
                Text(
                    text = "Account",
                    modifier = Modifier.padding(top = 35.dp, bottom = 1.dp)
                )
            }
        }


    }
}


@Composable
@Preview(showBackground = true)
fun ViewProductsScreenPreview(){
    AgriManagementAppTheme{
        ViewProductsScreen(navController = rememberNavController())
    }
}