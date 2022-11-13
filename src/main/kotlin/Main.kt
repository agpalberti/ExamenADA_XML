import ejercicio1.Authenticator
import ejercicio1.checkAndPrintAuthenticator
import ejercicio2.PokemonFinder
import ejercicio3.PokemonXMLCreator


fun main(){


    println("PRUEBA DE EJERCICIO 1")

    val confPath = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty(
        "file.separator") + "main" + System.getProperty("file.separator") + "resources" + System.getProperty("file.separator") + "credentials.conf"

    val authenticator = Authenticator(confPath)

    checkAndPrintAuthenticator(authenticator,"diego","1234567890")
    checkAndPrintAuthenticator(authenticator,"diego","123456789")
    checkAndPrintAuthenticator(authenticator, "h4ck3r", "1234567890")

    println("PRUEBA DE EJERCICIO 2")




    println("PRUEBA DE EJERCICIO 3")

    val statsMap = mutableMapOf<String,Int>()
    with(statsMap){
        set("HP",100)
        set("ATK",200)
    }

    val pokemon1 = Pokemon("Pikachu", "ELECTRIC", listOf("RELÁMPAGO","PLACAJE"), statsMap)
    val pokemon2 = Pokemon("Butterfree", "FIRE", listOf("SOMNIFERO","VUELO"), statsMap)
    val pokemon3 = Pokemon("Evee", "Normal", listOf("PISTOLA BURBUJA","PLACAJE"), statsMap)


    listOf(pokemon1,pokemon2,pokemon3).forEach { PokemonXMLCreator.create(it) }

    val pokemonXmlPath = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty(
        "file.separator") + "main" + System.getProperty("file.separator") + "resources" + System.getProperty("file.separator") + "pokemon.xml"
    val pokemonFinder = PokemonFinder(pokemonXmlPath)

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

