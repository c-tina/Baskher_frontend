package com.example.baskher_frontend

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.baskher_frontend.ui.login.ui.InitialScreen
import com.example.baskher_frontend.ui.login.ui.LoginScreen
import com.example.baskher_frontend.ui.login.ui.SignUpScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavigationWrapper(navHostController: NavHostController, auth: FirebaseAuth){

    NavHost(navController = navHostController, startDestination = "initial") {
        composable("initial"){
            InitialScreen(
                navigateToLogin = {navHostController.navigate("logIn")},
                navigateToSignUp = {navHostController.navigate("signUp")}
            )
        }

        composable ("logIn") {
            LoginScreen(auth)
        }

        composable ("signUp") {
            SignUpScreen(auth)
        }
    }
}