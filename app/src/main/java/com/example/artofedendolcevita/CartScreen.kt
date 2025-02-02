package com.example.artofedendolcevita

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CartScreen() {
    // Pobieramy listę produktów z koszyka (lista aktualizowana przy pomocy mutableStateListOf)
    val cartItems = CartRepository.cartItems

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()  // Dodaje odstęp od pasków systemowych (status bar, nawigacja)
            .padding(16.dp)
    ) {
        items(cartItems) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween  // Prawidłowy import z foundation.layout
                ) {
                    Text(text = item.name)
                    Button(
                        onClick = { CartRepository.removeItem(item) }
                    ) {
                        Text("Usuń")
                    }
                }
            }
        }
    }
}