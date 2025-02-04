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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.unit.sp // <---- !!! CZY NA PEWNO MASZ TEN IMPORT??? !!!

@Composable
fun DecorationScreen(modifier: Modifier = Modifier) {
    val decorations = DecorationRepository.decorations

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
            .fillMaxSize()
        //.systemBarsPadding()  <- USUNIĘTO systemBarsPadding() z DecorationScreen
        //.padding(top = 16.dp)   <- USUNIĘTO padding(top = 16.dp) z DecorationScreen, padding jest teraz w MainScreen
    ) {
        items(decorations) { decoration ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable {
                        // Dodaj dekorację do koszyka
                        CartRepository.addItem(decoration)
                    }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Image(
                        painter = painterResource(id = decoration.imageRes),
                        contentDescription = decoration.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = decoration.name)

                    // DODAJEMY WYŚWIETLANIE CENY TUTAJ:
                    Text(
                        text = "Cena: ${decoration.price} zł", // Wyświetlamy cenę z obiektu decoration
                        style = TextStyle( // Ustawiamy styl tekstu
                            fontSize = 12.sp,          // Mniejsza czcionka (14sp)
                            color = Color.Gray         // Szary kolor tekstu
                        )
                    )
                }
            }
        }
    }
}