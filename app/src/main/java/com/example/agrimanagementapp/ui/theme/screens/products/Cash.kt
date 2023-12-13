package com.example.agrimanagementapp.ui.theme.screens.products

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.agrimanagementapp.R
import com.example.agrimanagementapp.navigation.ACCOUNT_URL
import com.example.agrimanagementapp.navigation.ADD_PRODUCTS_URL
import com.example.agrimanagementapp.navigation.BUYNOW_URL
import com.example.agrimanagementapp.navigation.HOME_URL
import com.example.agrimanagementapp.navigation.VIEW_PRODUCTS_URL
import com.example.agrimanagementapp.ui.theme.AgriManagementAppTheme

@Composable
fun CashScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()).background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        val imageBrush =
            ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.hellocolor)))
        Text(
            text = "Contact vendor and come to an agreement on how you can pay through cash.",
            style = TextStyle(
                brush = imageBrush,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 40.sp,
            )
        )
        Spacer(modifier = Modifier.height(100.dp))
        Button(onClick = {
            navController.navigate(BUYNOW_URL)

        }, shape = CutCornerShape(10),
            border = BorderStroke(1.dp, Color.Black),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Green)) {
            Text(text = "GO BACK")
        }
        Spacer(modifier = Modifier.height(240.dp))
        BottomAppBar(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth().padding(top = 40.dp),

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
fun CashScreenPreview(){
    AgriManagementAppTheme{
        CashScreen(navController = rememberNavController())
    }
}