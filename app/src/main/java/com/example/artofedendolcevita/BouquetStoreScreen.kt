package com.example.artofedendolcevita

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

/**
 * Ekran prezentujący listę bukietów (gdy selectedBouquet == null),
 * lub ekran edycji wybranego bukietu (BouquetEditScreen) – gdy selectedBouquet != null.
 *
 * onNavigateToCart()    -> Zdefiniuj w MainScreen/Nawigacji: co się dzieje po "Przejdź do koszyka"
 * onNavigateToBouquets() -> Zdefiniuj: co się dzieje po "Kontynuuj zakupy"
 */
@Composable
fun BouquetStoreScreen(
    onNavigateToCart: () -> Unit,
    onNavigateToBouquets: () -> Unit
) {
    // Zmienna przechowująca aktualnie wybrany bukiet do edycji
    var selectedBouquet by remember { mutableStateOf<Bouquet?>(null) }

    // Jeśli żaden bukiet nie jest wybrany – wyświetlamy listę bukietów w stałej siatce 2 kolumn
    if (selectedBouquet == null) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),              // Zamiast Adaptive -> stała liczba kolumn
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp) // DODANO padding(top = 16.dp)
        ) {
            items(BouquetRepository.getAllBouquets()) { bouquet ->
                // Każdy kafelek ma stały rozmiar
                Card(
                    modifier = Modifier
                        .size(width = 160.dp, height = 220.dp)  // Ustalony rozmiar kafelka
                        .clickable {
                            // Po kliknięciu przechodzimy do ekranu edycji
                            selectedBouquet = bouquet
                        }
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        // Obrazek bukietu
                        Image(
                            painter = painterResource(id = bouquet.imageRes),
                            contentDescription = bouquet.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),   // Stała wysokość obrazka
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        // Nazwa bukietu
                        Text(
                            text = bouquet.name,
                            maxLines = 2,                    // W razie długiej nazwy: maks 2 linie
                            overflow = TextOverflow.Ellipsis // Dodaje "..." jeśli tekst się nie mieści
                        )
                    }
                }
            }
        }
    } else {
        // Jeśli bukiet jest wybrany, pokazujemy ekran edycji
        BouquetEditScreen(
            bouquet = selectedBouquet!!, // Pamiętaj o przekazaniu wszystkich wymaganych parametrów!
            onAddToCartConfirmed = onNavigateToCart, // Użyj przekazanych lambd nawigacyjnych
            onContinueShopping = onNavigateToBouquets,
            onCancel = { selectedBouquet = null } // Anuluj edycję = wróć do listy bukietów
        )
    }
}