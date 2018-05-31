package chainOfResponsibility

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val staff = Staff()
        val sectionChief = SectionChief()
        val director = Director()

        //set Handler
        staff.setTheNextHandler(sectionChief)
        sectionChief.nextHandler = director

        staff.dispose("小王", 400.00)
        staff.dispose("小混混", 800.00)
        staff.dispose("老李", 1200.00)

        staff.dispose("小明", 10000.00)
    }
}