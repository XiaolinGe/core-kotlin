package Koans

fun sendMessageToClient(
        client: Client?, message: String?, mailer: Mailer
){
    val theEmail =   client?.personalInfo?.email
    if (theEmail != null && message != null)
        mailer.sendMessage(theEmail, message)
}

class Client (val personalInfo: PersonalInfo?)
class PersonalInfo (val email: String?)
interface Mailer {
    fun sendMessage(email: String, message: String)
}