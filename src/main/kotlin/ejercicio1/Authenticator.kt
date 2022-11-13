package ejercicio1

import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.Properties

//Ejercicio 1

class Authenticator(path: String) {

    val properties = Properties()


    val file = try {
        File(path)

    } catch (_: Exception) {
        throw FileNotFoundException("No se encontró el archivo .conf en la ubicación determinada.")
    }


    //Solución
    fun authenticate(usuario: String, password: String): Boolean =
        properties["user"] == usuario.trim() && properties["password"] == password

    init {
        require(path.isNotBlank())

        loadProperties()

    }


    private fun loadProperties() {
        properties.load(FileInputStream(file))
    }

}