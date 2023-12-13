package com.example.agrimanagementapp.ui.theme.screens.products

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.agrimanagementapp.ui.theme.AgriManagementAppTheme
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agrimanagementapp.R
import com.example.agrimanagementapp.data.ProductViewModel
import com.example.agrimanagementapp.navigation.ACCOUNT_URL
import com.example.agrimanagementapp.navigation.ADD_PRODUCTS_URL
import com.example.agrimanagementapp.navigation.HOME_URL
import com.example.agrimanagementapp.navigation.VIEW_PRODUCTS_URL


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductsScreen(navController: NavHostController){
    Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.background6),
            contentDescription = "background image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )}
    Column (
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        val imageBrush =
            ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.hellocolor)))
        Text(
            text = "ADD PRODUCTS",
            style = TextStyle(
                brush = imageBrush,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
            )
        )

        var productName by remember { mutableStateOf("") }
        var productQuantity by remember { mutableStateOf("") }
        var productPrice by remember { mutableStateOf("") }
        val context = LocalContext.current

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = productName,
            onValueChange = { productName = it },
            label = { Text(
                text = "Product name *",
                color = Color.Black
            ) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = productQuantity,
            onValueChange = { productQuantity = it },
            label = { Text(text = "Product quantity *",
                color = Color.Black) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = productPrice,
            onValueChange = { productPrice = it },
            label = { Text(text = "Product price *",
                color = Color.Black) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))


        //---------------------IMAGE PICKER START-----------------------------------//
        var modifier = Modifier

        ImagePicker(
            modifier,
            context,
            navController,
            productName.trim(),
            productQuantity.trim(),
            productPrice.trim()
        )
    }
}
@Composable
fun ImagePicker(modifier: Modifier = Modifier, context: Context,navController: NavHostController, name:String, quantity:String, price:String) {
    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    Column(modifier = modifier) {
        if (hasImage && imageUri != null) {
            val bitmap = MediaStore.Images.Media.
            getBitmap(context.contentResolver,imageUri)
            Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Selected image")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp), horizontalAlignment = Alignment.CenterHorizontally,) {
            Button(
                onClick = {
                    imagePicker.launch("image/*")
                }, shape = CutCornerShape(10),
                border = BorderStroke(1.dp, Color.Green),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
            ) {
                Text(
                    text = "Select Image"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                //-----------WRITE THE UPLOAD LOGIC HERE---------------//
                var productRepository = ProductViewModel(navController,context)
                productRepository.uploadProduct(name, quantity, price,imageUri!!)


            }, shape = CutCornerShape(10),
                border = BorderStroke(1.dp, Color.Green),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)) {
                Text(text = "Upload")
            }
        }
        Spacer(modifier = Modifier.height(140.dp))
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
fun AddProductsScreenPreview(){
    AgriManagementAppTheme{
        AddProductsScreen(navController = rememberNavController())
    }
}