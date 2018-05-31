package chainOfResponsibility

class SectionChief: Handler() {
    override fun dispose(user: String, fee: Double): String? {
        if(fee < 1000) {
            println("小主管 给了 "+user+" "+fee+"元");
        }else if (super.getTheNextHandler() == null) {
            println("谁都处理不了 "+user+" 要 "+fee+"元的事情");
        } else {
            super.getTheNextHandler()!!.dispose(user, fee)
        }
        return null
    }
}