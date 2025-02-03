package com.example.artofedendolcevita

// Interfejs definiujący wspólne cechy produktu
interface IProductItem {
    val id: Int
    val name: String
    val imageRes: Int
    val price: Double
}

// Główna klasa produktu, której będziesz używać np. w HomeScreen
data class ProductItem(
    override val id: Int,
    override val name: String,
    override val imageRes: Int,
    // Dodajemy domyślną wartość 0.0, aby błąd "No value passed for parameter 'price'" nie występował
    override val price: Double = 0.0
) : IProductItem