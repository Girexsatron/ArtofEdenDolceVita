package com.example.artofedendolcevita

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Alignment

@Composable
fun CartScreen() {
    // Pobieramy elementy koszyka (np. List<ProductItem>)
    val cartItems = CartRepository.cartItems
    // Sumujemy ceny wszystkich elementów w koszyku
    val totalPrice = cartItems.sumOf { it.price }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        // Wyświetlamy kolejne produkty w koszyku
        items(cartItems) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // 1. Obrazek produktu (np. bukietu)
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = item.name,
                        modifier = Modifier
                            .size(60.dp)  // Ustal wielkość miniatury
                            .padding(end = 16.dp)
                    )

                    // 2. Sekcja tekstowa (nazwa i ewentualnie inne szczegóły)
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        // Jeśli chcesz dodać np. cenę produktu
                        Text(
                            text = "Cena: ${item.price} zł",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    // 3. Przycisk usuwania
                    Button(
                        onClick = { CartRepository.removeItem(item) },
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Text("Usuń")
                    }
                }
            }
        }
        // Na samym końcu listy – sekcja podsumowania koszyka
        item {
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(modifier = Modifier.padding(bottom = 8.dp))
            Text(text = "Łączna cena: $totalPrice zł")
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    // Obsługa zamówienia – np. przejście do ekranu płatności
                }
            ) {
                Text("Zamów")
            }
        }
    }
}