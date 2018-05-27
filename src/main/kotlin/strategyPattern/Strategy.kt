package strategyPattern

typealias strategy = (String) -> String

class Printer(val stringFormatterStrategy: strategy) {
    fun printString(string: String) = {
        println(stringFormatterStrategy.invoke(string))
    }
}

val lowerCaseFormatter: strategy = { it.toLowerCase() }

val upperCaseFormatter = { it: String -> it.toUpperCase() }

fun main(args: Array<String>) {

    val lowerCasePrinter = Printer(lowerCaseFormatter)

    lowerCasePrinter.printString("LOREM ipsum DOLOR sit amet")

    val upperCasePrinter = Printer(upperCaseFormatter)
    upperCasePrinter.printString("LOREM ipsum DOLOR sit amet")

    val prefixPrinter = Printer({ "Prefix: " +it })

    prefixPrinter.printString("LOREM ipsum DOLOR sit amet")
}







