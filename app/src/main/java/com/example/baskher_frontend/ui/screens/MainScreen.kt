package com.example.baskher_frontend.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.baskher_frontend.R
import com.example.baskher_frontend.ui.models.NavItem
import com.example.baskher_frontend.ui.theme.PurpleDark
import com.example.baskher_frontend.ui.theme.PurpleMedium
import com.example.baskher_frontend.ui.viewmodels.JugadoraViewModel
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier,
               viewModel: JugadoraViewModel,
               navigateToDetail: (Int) -> Unit,
               navController: NavHostController,
               auth: FirebaseAuth
) {

    val navItemList = listOf(
        NavItem("Estadísticas", R.drawable.baseline_bar_chart_24),
        NavItem("Explora", R.drawable.baseline_person_search_24),
        NavItem("Compara", R.drawable.baseline_compare_arrows_24),
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "BaskHer",
                        color = Color.White,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                },
                actions = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Cerrar sesión",
                            tint = Color.White,
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .clickable {
                                    auth.signOut() // Firebase logout
                                    navController.navigate("initial") {
                                        popUpTo("mainScreen") { inclusive = true }
                                    }
                                }
                        )

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PurpleDark,
                    titleContentColor = Color.White
                ),

            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = PurpleDark

            ) {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected =  selectedIndex == index ,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = navItem.icon),
                                contentDescription = "Icon"
                            )
                        },
                        label = {
                            Text(text = navItem.label)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            unselectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            unselectedTextColor = Color.White,
                            indicatorColor = PurpleMedium
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding),
            selectedIndex = selectedIndex,
            viewModel = viewModel,
            navigateToDetail = navigateToDetail)
    }
}


@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex : Int,
                  viewModel: JugadoraViewModel,
                  navigateToDetail: (Int) -> Unit) {
    when(selectedIndex){
        0-> StatisticsScreen(
            navigateToDetail = navigateToDetail,
            viewModel = viewModel,
        )
        1-> ExploreScreen(
            navigateToDetail = navigateToDetail,
            viewModel = viewModel
        )
        2-> CompareScreen(
            viewModel = viewModel
        )
    }
}