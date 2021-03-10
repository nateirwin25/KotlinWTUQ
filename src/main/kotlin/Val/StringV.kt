package Val

class StringV(var n: String) : Value() {

    override fun serialize(): String {
        return n
    }

}
