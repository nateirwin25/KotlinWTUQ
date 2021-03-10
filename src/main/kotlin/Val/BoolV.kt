package Val

class BoolV(var bool: Boolean) : Value() {

    override fun serialize(): String {
        return bool.toString()
    }

}