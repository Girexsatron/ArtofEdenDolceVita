package com.example.artofedendolcevita

/**
 * Repozytorium przykładowych bukietów.
 * Zawiera listę przykładowych danych i metody do ich pobierania.
 *
 * Upewnij się, że pliki graficzne (np. bouquet_roses.png, bouquet_tulips.png)
 * znajdują się w folderze: app/src/main/res/drawable/
 */
object BouquetRepository {

    // Lista przykładowych bukietów
    private val bouquets = listOf(
        Bouquet(
            id = 1,
            name = "Bukiet Róż",
            imageRes = R.drawable.bouquet_red_roses,  // Upewnij się, że plik bouquet_roses.png istnieje w res/drawable
            basePrice = 50.0,
            availableFlowers = listOf(
                Flower(id = 1, name = "Czerwona Róża", price = 10.0),
                Flower(id = 2, name = "Biała Róża", price = 9.0)
            ),
            availableExtras = listOf(
                Extra(id = 1, name = "Wstążka", price = 2.0),
                Extra(id = 2, name = "Karta z życzeniami", price = 3.0)
            )
        ),
        Bouquet(
            id = 2,
            name = "Bukiet Tulipanów",
            imageRes = R.drawable.bouquet_pink_tulips, // Upewnij się, że plik bouquet_tulips.png istnieje w res/drawable
            basePrice = 45.0,
            availableFlowers = listOf(
                Flower(id = 3, name = "Żółty Tulipan", price = 8.0),
                Flower(id = 4, name = "Różowy Tulipan", price = 8.5)
            ),
            availableExtras = listOf(
                Extra(id = 3, name = "Opakowanie prezentowe", price = 4.0),
                Extra(id = 4, name = "Balon z helem", price = 6.0)
            )
        )
        // Dodaj kolejne bukiety według potrzeb
    )

    /**
     * Zwraca listę wszystkich bukietów.
     */
    fun getAllBouquets(): List<Bouquet> = bouquets

    /**
     * Zwraca bukiet o podanym [id].
     * Jeśli nie znajdzie, zwraca pierwszy z listy.
     */
    fun getBouquetById(id: Int): Bouquet {
        return bouquets.firstOrNull { it.id == id } ?: bouquets.first()
    }
}