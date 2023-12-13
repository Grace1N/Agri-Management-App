package com.example.agrimanagementapp.ui.theme.screens.products

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
fun ContactScreen(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        val imageBrush =
            ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.hellocolor)))
        Text(
            text = "CONTACT",
            style = TextStyle(
                brush = imageBrush,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
            )
        )
        var context = LocalContext.current
        Button(onClick = {
            val uri = Uri.parse("smsto:0707826578")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "The SMS text")
            context.startActivity(intent)


        }, shape = CutCornerShape(30),
            border = BorderStroke(1.dp, Color.Magenta),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)) {
            Text(text = "SMS")

        }
       Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {

            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "gracenjoroge4r@gmail.com", null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Sale of your Goods")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear M..........")
            context.startActivity(Intent.createChooser(emailIntent, "Send email..."))

        }, shape = CutCornerShape(30),
            border = BorderStroke(1.dp, Color.Magenta),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)) {
            Text(text = "Email")

        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0707826579"))
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf<String>(Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                context.startActivity(intent)
            }

        }, shape = CutCornerShape(30),
            border = BorderStroke(1.dp, Color.Magenta),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)) {
            Text(text = "Call")

        }
        Spacer(modifier = Modifier.height(70.dp))
        Button(onClick = {
            navController.navigate(BUYNOW_URL)

        }, shape = CutCornerShape(10),
            border = BorderStroke(1.dp, Color.Magenta),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)) {
            Text(text = "PAY NOW")
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {
            navController.navigate(VIEW_PRODUCTS_URL)

        }, shape = CutCornerShape(10),
            border = BorderStroke(1.dp, Color.Magenta),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)) {
            Text(text = "GO BACK")
        }
        Spacer(modifier = Modifier.height(170.dp))
        BottomAppBar(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .padding(top = 40.dp),

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
fun ContactScreenPreview(){
    AgriManagementAppTheme{
        ContactScreen(navController = rememberNavController())
    }
}