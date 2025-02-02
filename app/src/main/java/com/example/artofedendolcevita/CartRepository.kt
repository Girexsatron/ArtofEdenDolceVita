package com.example.artofedendolcevita

import androidx.compose.runtime.mutableStateListOf

object CartRepository {
    // Lista produktów dodanych do koszyka – dzięki mutableStateListOf UI będzie reagować na zmiany
    val cartItems = mutableStateListOf<ProductItem>()

    fun addItem(item: ProductItem) {
        cartItems.add(item)
    }

    fun removeItem(item: ProductItem) {
        cartItems.remove(item)
    }
}