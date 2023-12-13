package com.example.agrimanagementapp.ui.theme.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.agrimanagementapp.R
import com.example.agrimanagementapp.data.AuthViewModel
import com.example.agrimanagementapp.navigation.HOME_URL
import com.example.agrimanagementapp.navigation.SIGNUP_URL
import com.example.agrimanagementapp.ui.theme.AgriManagementAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            val composition by rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(R.raw.cartoon_ab)
            )
            var isPlaying by remember {
                mutableStateOf(true)
            }
            val progress by animateLottieCompositionAsState(
                composition = composition,
                isPlaying = isPlaying
            )
            LaunchedEffect(key1 = progress) {
                if (progress == 0f) {
                    isPlaying = true
                }
                if (progress == 1f) {
                    isPlaying = false
                }
            }

            LottieAnimation(
                composition = composition,
                modifier = Modifier
                    .size(500.dp)
                    .clickable {
                        isPlaying = true
                    },
                //iterations = LottieConstants.IterateForever
                progress = {
                    progress
                }
            )
            var email by remember { mutableStateOf("") }

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        text = "Enter Email",
                        color = Color.Magenta,
                        fontFamily = FontFamily.Monospace
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(10.dp))
            var password by remember { mutableStateOf("") }
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        text = "Enter your password",
                        color = Color.Magenta,
                        fontFamily = FontFamily.Monospace
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                )

            )
            Spacer(modifier = Modifier.height(10.dp))
            val context = LocalContext.current
            val authViewModel = AuthViewModel(navController, context)
            Button(onClick = {
                authViewModel.login(email, password)
            },
                border = BorderStroke(1.dp, Color.Magenta),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray)) {
                Text(text = "LOGIN")
            }

            Button(
                onClick = {
                    navController.navigate(SIGNUP_URL)

                }, shape = CutCornerShape(10),
                border = BorderStroke(1.dp, Color.Magenta),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray)
            ) {
                Text(text = "SIGN UP INSTEAD")
            }
            Button(
                onClick = {
                    navController.navigate(HOME_URL)

                }, shape = CutCornerShape(10),
                border = BorderStroke(1.dp, Color.Magenta),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray)
            ) {
                Text(text = "home")
            }


        }

}


@Composable
@Preview(showBackground = true)
fun LoginScreenPreview(){
    AgriManagementAppTheme{
        LoginScreen(navController = rememberNavController())
    }
}