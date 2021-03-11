class Symbol(var sym: String) {
    fun get() : String {
        return sym
    }

    override fun equals(other: Any?): Boolean {
        return sym.equals(other)
    }

    override fun toString(): String {
        return sym.toString()
    }

}

