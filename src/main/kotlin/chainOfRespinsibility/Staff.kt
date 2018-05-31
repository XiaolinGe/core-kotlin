package chainOfRespinsibility

class Staff: Handler() {
    override fun dispose(user: String, fee: Double): String? {
        if(fee < 500) {
            println("职员 给了 "+user+" "+fee+"元")
        } else if (super.nextHandler == null ) {
           println("谁都处理不了 "+user+" 要 "+fee+"元的事情")
        } else {
            super.getTheNextHandler()!!.dispose(user, fee)
        }

        return null
    }
}