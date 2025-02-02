package com.example.artofedendolcevita

// Model danych reprezentujący ozdobę – implementuje interfejs ProductItem
data class Decoration(
    override val name: String,
    override val imageRes: Int
) : ProductItem

object DecorationRepository {
    val decorations = listOf(
        Decoration("Porcelanowy słonik", R.drawable.decoration_porcelain_elephant),
        Decoration("Ozdoba wielkanocna", R.drawable.decoration_easter),
        Decoration("Porcelanowa figura", R.drawable.decoration_porcelain_figure),
        Decoration("Dekoracja świąteczna", R.drawable.decoration_christmas)
    )
}