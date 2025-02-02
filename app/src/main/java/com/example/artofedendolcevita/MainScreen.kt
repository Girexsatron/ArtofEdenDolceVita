package com.example.artofedendolcevita

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun MainScreen() {
    // 0 - Bukiety, 1 - Ozdoby, 2 - Koszyk
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Filled.LocalFlorist, contentDescription = "Bukiety") },
                    label = { Text("Bukiety") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Filled.Star, contentDescription = "Ozdoby") },
                    label = { Text("Ozdoby") }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Koszyk") },
                    label = { Text("Koszyk") }
                )
            }
        }
    ) { innerPadding ->
        when (selectedTab) {
            0 -> HomeScreen() // ekran bukietów
            1 -> DecorationScreen(modifier = Modifier.padding(innerPadding)) // ekran ozdób
            2 -> CartScreen() // ekran koszyka
        }
    }
}