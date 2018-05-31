package chainOfResponsibility

abstract class Handler {
    var nextHandler: Handler? = null

    fun getTheNextHandler(): Handler? {
        return nextHandler
    }

    fun setTheNextHandler(nextHandler: Handler) {
        this.nextHandler = nextHandler
    }

    abstract fun dispose(user: String, fee: Double): String?

}