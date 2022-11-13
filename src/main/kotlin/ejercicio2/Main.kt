package ejercicio2

fun main(){
    val path = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty(
        "file.separator") + "main" + System.getProperty("file.separator") + "resources" + System.getProperty("file.separator") + "pokemon.xml"
    val pokemonFinder = PokemonFinder(path)

    val search = pokemonFinder.searchPokemon("BulbasauR")

    println(search.first)
    println(search.second)


    with(pokemonFinder){

        listOf("BulbasauR","GALVANTULA","Charmeleon","BLAZE").forEach { pokemon ->

            searchPokemon(pokemon).let {
                println(it.first)
                println(it.second)
            }
        }
    }

}