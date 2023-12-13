package com.example.agrimanagementapp.ui.theme.screens.about

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.agrimanagementapp.R
import com.example.agrimanagementapp.navigation.LOGIN_URL
import com.example.agrimanagementapp.navigation.SIGNUP_URL
import com.example.agrimanagementapp.ui.theme.AgriManagementAppTheme


@Composable
fun SecondScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.background4),
            contentDescription = "background image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center


        ) {
            val imageBrush =
                ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.hellocolor)))
            Text(
                text = "WELCOME!! LETS GET STARTED",
                style = TextStyle(
                    brush = imageBrush,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 50.sp
                )
            )
            Button(
                onClick = {
                    navController.navigate(SIGNUP_URL)

                }, shape = CutCornerShape(10),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Magenta)
            ) {
                Text(text = "SIGN UP")
            }
            Button(
                onClick = {
                    navController.navigate(LOGIN_URL)

                }, shape = CutCornerShape(10),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Magenta)

            ) {
                Text(text = "LOGIN")

            }

        }
    }

}

@Composable
@Preview(showBackground = true)
fun SecondScreenPreview(){
    AgriManagementAppTheme{
        SecondScreen(navController = rememberNavController())
    }
}