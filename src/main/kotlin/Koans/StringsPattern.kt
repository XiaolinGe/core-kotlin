package Koans

val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

fun String.getPattern(): String  {
    val day = this.split(".")[0]
    val year = this.split(".")[2]
    val monthStr = this.split(".")[1]
    val month = when (monthStr) {
        "01" -> "JAN"
        "02" -> "FEB"
        "03" -> "MAR"
        "04" -> "APR"
        "05" -> "MAY"
        "06" -> "JUN"
        "07" -> "JUL"
        "08" -> "AUG"
        "09" -> "SEP"
        "10" -> "OCT"
        "11" -> "NOV"
        "12" -> "DEC"
        else -> "invalid month"
    }

    return "$day $month $year"

}

fun getPattern2(date: String): String {
    val dayStr = date.split(".")[0]
    val yearStr = date.split(".")[2]
    val monthStr = date.split(".")[1]
    monthStr.toLong()



    val monthStr1 = month.substring(1, month.length-1)
            .split("|")[1]


    return "$dayStr $monthStr $yearStr"
}



fun main(args: Array<String>) {
    val str = "13.06.1992"
    println(str.split("."))
    println(str.split(".")[0])
    println(str.split(".")[1])
    println(str.split(".")[2])

    println(("13.06.1992".getPattern()))
    println("========")
    println((getPattern2("13.06.1992")))

    val monthStr = month.substring(1, month.length-1)
            .split("|")[1]

}