package com.example.artofedendolcevita

data class Flower(
    val id: Int,
    val name: String,
    val price: Double
)

data class Extra(
    val id: Int,
    val name: String,
    val price: Double
)

data class Bouquet(
    val id: Int,
    val name: String,
    val imageRes: Int,      // np. R.drawable.bouquet_example
    val basePrice: Double,  // cena bazowa bukietu
    val availableFlowers: List<Flower>,
    val availableExtras: List<Extra>
)