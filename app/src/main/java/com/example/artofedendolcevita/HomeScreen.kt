package com.example.artofedendolcevita

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.systemBarsPadding

@Composable
fun HomeScreen() {
    // Używamy metody getAllBouquets(), która zwraca listę bukietów.
    val bouquets = BouquetRepository.getAllBouquets()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        items(bouquets) { bouquet ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable {
                        // Konwertujemy Bouquet do ProductItem
                        val productItem = ProductItem(
                            id = bouquet.id,
                            name = bouquet.name,
                            imageRes = bouquet.imageRes  // Upewnij się, że ta właściwość istnieje w modelu Bouquet
                        )
                        CartRepository.addItem(productItem)
                    }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Image(
                        painter = painterResource(id = bouquet.imageRes),
                        contentDescription = bouquet.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = bouquet.name)
                }
            }
        }
    }
}