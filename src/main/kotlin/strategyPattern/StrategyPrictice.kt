package strategyPattern

class Customer(val name: String, val fee: Double, val discount: (Double) -> Double) {
    fun pricePerMonth() = discount(fee)
}


// 类的参数是函数，类的函数可以属于函数参数

class Student(val scor: (Double) -> Double) {
    fun allScor(baseScor: Double)  = scor(baseScor)
}



class Bag(val bagPrice: Double, val price: (Double) -> Double) {
    fun pirceWithFee() = price(bagPrice)
}


class Food(val basePrice : Double , val price: (Double) -> Double) {
    fun foodPrice() = price(basePrice)
}


fun main(args: Array<String>) {
    // usage
    val studentDiscount = { fee: Double -> fee/2 }
    val noDiscount = { fee: Double -> fee }
    val student = Customer("Ned", 10.0, studentDiscount)
    val regular = Customer("John", 10.0, noDiscount)

  //  println(student.pricePerMonth())
  //  println(regular.pricePerMonth())

    val aStudent = { baseScor: Double -> 10 + baseScor}
    val bStudent = { baseScor: Double -> baseScor / 2}

    val aScor = Student(aStudent).allScor(20.00)
    val bScor = Student(bStudent).allScor(20.00)

  //  println(aScor)
  //  println(bScor)

    val baoYou: (Double) -> Double = { basePrice: Double -> basePrice }
    val buBaoYou: (Double) -> Double = { basePrice: Double -> basePrice + 10 }

    val tP1 = Bag(100.00, baoYou).pirceWithFee()
    val tP2 = Bag(100.00, buBaoYou).pirceWithFee()

  //  println(tP1)
  //  println(tP2)

    val mianYou: (Double) -> Double = { basePrice: Double -> basePrice }
    val buMianYou: (Double) -> Double = { basePrice: Double -> basePrice + 6}
    val food1 = Food(10.00, mianYou)
    val food2 = Food(10.00, buMianYou)

    val price1 = food1.foodPrice()
    val price2 = food2.foodPrice()

    println(price1)
    println(price2)
}

