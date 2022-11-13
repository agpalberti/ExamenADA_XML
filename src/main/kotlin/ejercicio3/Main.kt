package ejercicio3

import Pokemon

fun main() {

    val statsMap = mutableMapOf<String, Int>()
    with(statsMap) {
        set("HP", 100)
        set("ATK", 200)
    }

    val pokemon1 = Pokemon("BULBASAUR", "GRASS", listOf("OVERGROW", "CHLOROPHYLL"), statsMap)

    PokemonXMLCreator.create(pokemon1)
}