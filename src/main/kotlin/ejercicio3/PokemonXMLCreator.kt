package ejercicio3

import Pokemon
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class PokemonXMLCreator() {


    companion object {

        val path = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty(
            "file.separator"
        ) + "main" + System.getProperty("file.separator") + "resources" + System.getProperty("file.separator")

        private val documentCreator = DocumentBuilderFactory.newInstance().newDocumentBuilder().domImplementation

        fun create(pokemon: Pokemon) {
            val document = documentCreator.createDocument(null, pokemon.nombre, null)
            val root = document.createElement(pokemon.nombre)

            val nombre = document.createElement("name")
            val valueNombre = document.createTextNode(pokemon.nombre)
            nombre.appendChild(valueNombre)

            val tipo = document.createElement("type")
            val valueTipo = document.createTextNode(pokemon.tipo)
            tipo.appendChild(valueTipo)

            val habilidades = document.createElement("abilities")
            pokemon.habilidades.forEach {
                val habilidad = document.createElement("ability")
                val nombreHabilidad = document.createTextNode(it)
                habilidad.appendChild(nombreHabilidad)
                habilidades.appendChild(habilidad)
            }

            val stats = document.createElement("stats")
            pokemon.stats.forEach {
                val nombreStat = document.createElement(it.key)
                val valorStat = document.createTextNode(it.value.toString())
                nombreStat.appendChild(valorStat)
                stats.appendChild(nombreStat)
            }


            with(root) {
                appendChild(nombre)
                appendChild(tipo)
                appendChild(habilidades)
                appendChild(stats)
            }

            document.documentElement.appendChild(root)

            val source = DOMSource(document)
            val streamResult = StreamResult(File(path + "${pokemon.nombre}.xml"))
            val transformer = TransformerFactory.newInstance().newTransformer()
            with(transformer) {
                setOutputProperty(OutputKeys.INDENT, "yes")
                transform(source, streamResult)
            }
        }

    }


}