package chainOfRespinsibility

class Director : Handler() {
    override fun dispose(user: String, fee: Double): String? {
        if (fee < 5000) {
            println("老大 给了 " + user + " " + fee + "元")
        } else if (super.nextHandler == null) {
            println("谁都处理不了 " + user + " 要 " + fee + "元的事情")
        } else {
            super.nextHandler!!.dispose(user, fee)
        }
        return null
    }
}