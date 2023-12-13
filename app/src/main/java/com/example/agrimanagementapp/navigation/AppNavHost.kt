package com.example.agrimanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agrimanagementapp.ui.theme.screens.home.HomeScreen
import com.example.agrimanagementapp.ui.theme.screens.about.AboutScreen
import com.example.agrimanagementapp.ui.theme.screens.about.SecondScreen
import com.example.agrimanagementapp.ui.theme.screens.account.AccountScreen
import com.example.agrimanagementapp.ui.theme.screens.products.AddProductsScreen
import com.example.agrimanagementapp.ui.theme.screens.products.ViewProductsScreen
import com.example.agrimanagementapp.ui.theme.screens.signup.SignUpScreen
import com.example.agrimanagementapp.ui.theme.screens.login.LoginScreen
import com.example.agrimanagementapp.ui.theme.screens.products.BuyNowScreen
import com.example.agrimanagementapp.ui.theme.screens.products.CashScreen
import com.example.agrimanagementapp.ui.theme.screens.products.ContactScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination:String= ABOUT_URL
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(LOGIN_URL) {
            LoginScreen(navController = navController)
        }
        composable(SIGNUP_URL) {
            SignUpScreen(navController = navController)
        }
        composable(HOME_URL) {
            HomeScreen(navController = navController)
        }
        composable(ADD_PRODUCTS_URL) {
            AddProductsScreen(navController = navController)

        }
        composable(VIEW_PRODUCTS_URL) {
            ViewProductsScreen(navController = navController)

        }
        composable(ABOUT_URL) {
            AboutScreen(navController = navController)

        }
        composable(SECOND_URL) {
            SecondScreen(navController = navController)

        }
        composable(ACCOUNT_URL) {
            AccountScreen(navController = navController)

        }
        composable(BUYNOW_URL) {
            BuyNowScreen(navController = navController)

        }
        composable(CONTACT_URL) {
            ContactScreen(navController = navController)

        }
        composable(CASH_URL) {
            CashScreen(navController = navController)

        }

    }
}