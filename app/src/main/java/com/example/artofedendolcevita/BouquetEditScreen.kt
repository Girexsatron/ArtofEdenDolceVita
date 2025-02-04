package com.example.artofedendolcevita

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Ekran edycji bukietu:
 * - Kwiaty w liczbie sztuk (plus/minus).
 * - Dodatki (dodane/nie).
 * - Po kliknięciu "Dodaj do koszyka" pojawia się AlertDialog z pytaniem:
 *   "Czy kontynuować zakupy, czy przejść do koszyka?"
 *
 * onAddToCartConfirmed() -> logika nawigacji do koszyka
 * onContinueShopping() -> logika powrotu do listy bukietów (lub inny ekran)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BouquetEditScreen(
    bouquet: Bouquet,
    onAddToCartConfirmed: () -> Unit,
    onContinueShopping: () -> Unit,
    onCancel: () -> Unit
) {
    // 1. Liczba sztuk kwiatów
    var flowerQuantities by remember {
        mutableStateOf(bouquet.availableFlowers.associateWith { 5 })
    }

    // 2. Wybrane dodatki (lista binarna)
    var selectedExtras by remember { mutableStateOf<List<Extra>>(emptyList()) }

    // 3. Obliczenie łącznej ceny (dynamicznie)
    val totalPrice by remember(bouquet, flowerQuantities, selectedExtras) {
        mutableStateOf(
            bouquet.basePrice +
                    flowerQuantities.entries.sumOf { (flower, qty) ->
                        flower.price * qty
                    } +
                    selectedExtras.sumOf { it.price }
        )
    }

    // **Stan dialogu** – czy wyświetlamy AlertDialog z pytaniem o zakupy?
    var showConfirmDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Edytuj bukiet: ${bouquet.name}") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .padding(top = 16.dp) // DODANO padding(top = 16.dp)
        ) {
            // (A) Obrazek bukietu
            Card( // <-- Poprawnie użyty Card z content lambda
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) { // <-- Otwarcie content lambda dla Card
                Image(
                    painter = painterResource(id = bouquet.imageRes),
                    contentDescription = "Zdjęcie bukietu ${bouquet.name}",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            } // <-- Zamknięcie content lambda dla Card

            Spacer(modifier = Modifier.height(16.dp))

            // (B) Sekcja kwiatów
            Text(text = "Wybierz kwiaty", style = MaterialTheme.typography.titleMedium)
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            bouquet.availableFlowers.forEach { flower ->
                val currentQuantity = flowerQuantities[flower] ?: 0

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Nazwa i informacje
                    Column {
                        Text(text = flower.name, style = MaterialTheme.typography.bodyLarge)
                        Text(
                            text = "(Cena za sztukę: ${flower.price} PLN)",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                        Text(
                            text = "Aktualnie: $currentQuantity szt.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }

                    // Plus / minus
                    Row {
                        Button(
                            onClick = {
                                if (currentQuantity > 0) {
                                    val newMap = flowerQuantities.toMutableMap()
                                    newMap[flower] = currentQuantity - 1
                                    flowerQuantities = newMap
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                            enabled = currentQuantity > 0
                        ) {
                            Text(
                                text = "–",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                val newMap = flowerQuantities.toMutableMap()
                                newMap[flower] = currentQuantity + 1
                                flowerQuantities = newMap
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                        ) {
                            Text(
                                text = "+",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // (C) Sekcja dodatków
            Text(text = "Wybierz dodatki", style = MaterialTheme.typography.titleMedium)
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            bouquet.availableExtras.forEach { extra ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = extra.name, style = MaterialTheme.typography.bodyLarge)
                        Text(
                            text = "(Cena: ${extra.price} PLN)",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                    Button(
                        onClick = {
                            selectedExtras = if (selectedExtras.contains(extra)) {
                                selectedExtras - extra
                            } else {
                                selectedExtras + extra
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedExtras.contains(extra)) {
                                MaterialTheme.colorScheme.secondary
                            } else {
                                MaterialTheme.colorScheme.primary
                            }
                        )
                    ) {
                        Text(
                            text = if (selectedExtras.contains(extra)) "Usuń" else "Dodaj",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // (D) Wyświetlamy aktualną cenę
            Text(
                text = "Aktualna cena bukietu: $totalPrice PLN",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // (E) Przyciski
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    onClick = onCancel,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Anuluj", style = MaterialTheme.typography.labelLarge)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = {
                        // Gdy klikamy "Dodaj do koszyka", wyświetlamy dialog
                        showConfirmDialog = true
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Dodaj do koszyka", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }

    // --- AlertDialog ---
    if (showConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showConfirmDialog = false },
            title = { Text("Dodano do koszyka") },
            text = { Text("Czy chcesz kontynuować zakupy, czy przejść do koszyka?") },
            confirmButton = {
                // "Przejdź do koszyka" – tutaj dodajemy do koszyka i wywołujemy onAddToCartConfirmed
                Button(
                    onClick = {
                        showConfirmDialog = false

                        // Dodajemy "produkt" do koszyka z ceną totalPrice
                        // (Zakładamy, że CartRepository przyjmuje obiekty ProductItem,
                        //  a Ty sam decydujesz, czy nazwać to "Bukiet spersonalizowany" etc.)
                        val product = ProductItem(
                            id = bouquet.id,
                            name = bouquet.name,
                            imageRes = bouquet.imageRes,
                            price = totalPrice
                        )
                        CartRepository.addItem(product)

                        // Nawigacja do koszyka
                        onAddToCartConfirmed()
                    }
                ) {
                    Text("Przejdź do koszyka")
                }
            },
            dismissButton = {
                // "Kontynuuj zakupy" – wracamy na ekran bukietów
                OutlinedButton(
                    onClick = {
                        showConfirmDialog = false

                        // Dodajemy bukiet do koszyka:
                        val product = ProductItem(
                            id = bouquet.id,
                            name = bouquet.name,
                            imageRes = bouquet.imageRes,
                            price = totalPrice
                        )
                        CartRepository.addItem(product)

                        onContinueShopping()
                    }
                ) {
                    Text("Kontynuuj zakupy")
                }
            }
        )
    }
}