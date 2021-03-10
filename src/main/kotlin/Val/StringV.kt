package Val

class StringV(var str: String) : Value() {

    override fun serialize(): String {
        return str
    }

}
