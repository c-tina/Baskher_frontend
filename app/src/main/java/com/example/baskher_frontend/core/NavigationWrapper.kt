package com.example.baskher_frontend.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baskher_frontend.data.api.BaskherRepository
import com.example.baskher_frontend.data.api.RetrofitInstance
import com.example.baskher_frontend.ui.login.InitialScreen
import com.example.baskher_frontend.ui.login.LoginScreen
import com.example.baskher_frontend.ui.login.SignUpScreen
import com.example.baskher_frontend.ui.screens.JugadoraDetailScreen
import com.example.baskher_frontend.ui.screens.MainScreen
import com.example.baskher_frontend.ui.viewmodels.JugadoraViewModel
import com.example.baskher_frontend.ui.viewmodels.JugadoraViewModelFactory
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavigationWrapper(navHostController: NavHostController, auth: FirebaseAuth){

    val navController = rememberNavController()

    val context = LocalContext.current

    val baskherRepository = BaskherRepository(
        baskherApiService = RetrofitInstance.baskherApiService,
    )

    val viewModel: JugadoraViewModel = viewModel(factory = JugadoraViewModelFactory(baskherRepository))

    val isUserLoggedIn = auth.currentUser != null

    val startDestination = if (isUserLoggedIn) "mainScreen" else "initial"

    NavHost(navController = navHostController, startDestination = startDestination) {
        composable("initial"){
            InitialScreen(
                navigateToLogin = {navHostController.navigate("logIn")},
                navigateToSignUp = {navHostController.navigate("signUp")}
            )
        }

        composable ("logIn") {
            LoginScreen(auth,
                onLoginSuccess = {
                    navHostController.navigate("mainScreen") {
                        popUpTo("initial") { inclusive = true }
                    }
                }
            )
        }

        composable ("signUp") {
            SignUpScreen(auth,
                onSignUpSuccess = {
                    navHostController.navigate("mainScreen") {
                        popUpTo("initial") { inclusive = true }
                    }
                }
            )
        }

        composable("mainScreen") {
            MainScreen(viewModel = viewModel,
                navigateToDetail = { id ->
                    navHostController.navigate("jugadoras/$id")
                }
            )
        }

        composable("jugadoras/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            id?.let {
                JugadoraDetailScreen(
                    jugadoraId = it,
                    viewModel = viewModel
                )
            }
        }
    }
}