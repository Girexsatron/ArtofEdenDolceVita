package com.example.artofedendolcevita

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen() {
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
        Column( // Dodajemy Column jako kontener dla treści zakładek, żeby móc dodać padding
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Zachowujemy padding z Scaffold
                .padding(top = 16.dp)   // DODAJEMY padding(top = 16.dp) TUTAJ, na poziomie MainScreen
        ) {
            when (selectedTab) {
                // 1. Ekran bukietów z możliwością edycji
                0 -> BouquetStoreScreen(
                    onNavigateToCart = {
                        // Zmieniamy zakładkę na "Koszyk"
                        selectedTab = 2
                    },
                    onNavigateToBouquets = {
                        // Powracamy / pozostajemy na zakładce bukietów
                        selectedTab = 0
                    }
                )

                // 2. Ekran ozdób
                1 -> DecorationScreen(modifier = Modifier.fillMaxSize()) // Używamy fillMaxSize() bo Column już ma padding
                // 3. Ekran koszyka
                2 -> CartScreen()
            }
        }
    }
}