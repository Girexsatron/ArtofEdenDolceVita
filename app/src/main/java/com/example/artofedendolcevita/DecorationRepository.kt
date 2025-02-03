package com.example.artofedendolcevita

data class Decoration(
    override val id: Int,
    override val name: String,
    override val imageRes: Int,
    override val price: Double
) : IProductItem

object DecorationRepository {
    val decorations = listOf(
        Decoration(id = 101, name = "Porcelanowy słonik", imageRes = R.drawable.decoration_porcelain_elephant, price = 20.0),
        Decoration(id = 102, name = "Ozdoba wielkanocna", imageRes = R.drawable.decoration_easter, price = 15.0),
        Decoration(id = 103, name = "Porcelanowa figura", imageRes = R.drawable.decoration_porcelain_figure, price = 25.0),
        Decoration(id = 104, name = "Dekoracja świąteczna", imageRes = R.drawable.decoration_christmas, price = 30.0)
    )
}