package ejercicio2

import Pokemon
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.FileNotFoundException
import javax.xml.parsers.DocumentBuilderFactory

class PokemonFinder(path: String) {

    private val xml = try {

        xmlBuilder(path)

    } catch (_: Exception) {
        throw FileNotFoundException("No se encontró el archivo XML en la ubicación determinada.")
    }


    init {
        require(path.isNotBlank()) { "El path no puede estar vacío" }

        xml.documentElement.normalize()
    }

    private fun xmlBuilder(path: String) = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(path)

    fun searchPokemon(name: String): Pair<Int, Pokemon?> {

        val nodeList = xml.documentElement.childNodes

        val node = specieSearcher(nodeList, name)

        if (node != null) {
            var nombre = ""
            var tipo = ""
            var habilidades = listOf<String>()
            val stats = mutableMapOf<String, Int>()
            for (i in 0 until node.childNodes.length) {
                val caracteristica = node.childNodes.item(i)
                when (caracteristica.nodeName) {
                    "species" -> nombre = caracteristica.textContent
                    "types" -> {
                        tipo = nodeListIterator(caracteristica.childNodes).first()
                    }

                    "abilities" -> {
                        habilidades = nodeListIterator(caracteristica.childNodes)
                    }

                    "baseStats" -> {
                        for (counter in 0 until caracteristica.childNodes.length)
                            if (caracteristica.childNodes.item(counter).nodeName != "#text") {
                                stats[caracteristica.childNodes.item(counter).nodeName] =
                                    caracteristica.childNodes.item(counter).textContent.toInt()
                            }
                    }
                }
            }

            return Pair(200, Pokemon(nombre, tipo, habilidades, stats))
        } else return Pair(404, null)

    }

    private fun specieSearcher(nodeList: NodeList, pokemonName: String): Node? {
        for (counter in 0 until nodeList.length) {

            if (nodeList.item(counter).hasChildNodes()) {
                val childNodeList = nodeList.item(counter).childNodes
                for (node in 0 until childNodeList.length) {
                    if (childNodeList.item(node).nodeName == "species") {
                        if (childNodeList.item(node).textContent.trim().uppercase() == pokemonName.uppercase().trim()) {
                            return nodeList.item(counter)
                        }
                    }
                }
            }

        }
        return null
    }

    private fun nodeListIterator(nodeList: NodeList): List<String> {
        val lista = mutableListOf<String>()
        for (counter in 0 until nodeList.length)
            if (nodeList.item(counter).nodeName != "#text") {
                lista.add(nodeList.item(counter).textContent)
            }
        return lista
    }

}