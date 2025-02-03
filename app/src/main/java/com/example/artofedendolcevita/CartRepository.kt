package com.example.artofedendolcevita

import androidx.compose.runtime.mutableStateListOf

object CartRepository {
    val cartItems = mutableStateListOf<IProductItem>()

    fun addItem(item: IProductItem) {
        cartItems.add(item)
    }

    fun removeItem(item: IProductItem) {
        cartItems.remove(item)
    }
}