package ejercicio1

fun main(args: Array<String>) {


    println("PRUEBA DE EJERCICIO 1")

    val path = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty(
        "file.separator") + "main" + System.getProperty("file.separator") + "resources" + System.getProperty("file.separator") + "credentials.conf"

    val authenticator = Authenticator(path)

    checkAndPrintAuthenticator(authenticator,"diego","1234567890")
    checkAndPrintAuthenticator(authenticator,"diego","123456789")
    checkAndPrintAuthenticator(authenticator, "h4ck3r", "1234567890")

}


fun checkAndPrintAuthenticator(authenticator:Authenticator,usuario:String,password:String){

    if (authenticator.authenticate(usuario,password)){
        println("Bienvenid@ $usuario")

    } else println("Acceso denegado")
}


