package com.example.artofedendolcevita

// Model danych reprezentujący bukiet – teraz implementuje interfejs ProductItem
data class Bouquet(
    override val name: String,
    override val imageRes: Int,
    val price: Double  // cena – może być używana później na ekranie szczegółów
) : ProductItem

object BouquetRepository {
    val bouquets = listOf(
        Bouquet("Bouquet Blue Hydrangeas", R.drawable.bouquet_blue_hydrangeas, 29.99),
        Bouquet("Bouquet Mixed Wildflowers", R.drawable.bouquet_mixed_wildflowers, 19.99),
        Bouquet("Bouquet Pink Peonies", R.drawable.bouquet_pink_peonies, 34.99),
        Bouquet("Bouquet Pink Tulips", R.drawable.bouquet_pink_tulips, 24.99),
        Bouquet("Bouquet Purple Orchids", R.drawable.bouquet_purple_orchids, 39.99),
        Bouquet("Bouquet Red Roses", R.drawable.bouquet_red_roses, 27.99),
        Bouquet("Bouquet Red & White Carnations", R.drawable.bouquet_red_white_carnations, 22.99),
        Bouquet("Bouquet White Lilies", R.drawable.bouquet_white_lilies, 31.99),
        Bouquet("Bouquet Yellow Sunflowers", R.drawable.bouquet_yellow_sunflowers, 18.99)
    )
}