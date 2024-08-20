package com.example.bottomnavbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.bottomnavbar.ui.theme.BottomNavBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavBarTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "screen_one",
            Modifier.padding(innerPadding)
        ) {
            composable("screen_one") { ScreenOne(navController) }
            composable("screen_two") { ScreenTwo(navController) }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Screen One") },
            label = { Text("Screen One") },
            selected = false,
            onClick = {
                navController.navigate("screen_one") {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Screen Two") },
            label = { Text("Screen Two") },
            selected = false,
            onClick = {
                navController.navigate("screen_two") {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
    }
}

@Composable
fun ScreenOne(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is Screen One")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("screen_two")
        }) {
            Text("Go to Screen Two")
        }
    }
}

@Composable
fun ScreenTwo(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is Screen Two")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("screen_one")
        }) {
            Text("Go to Screen One")
        }
    }
}
