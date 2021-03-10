package Val

class PrimV(var op : (List<Value>) -> Value) : Value() {

    override fun serialize(): String {
        return "#<primop>"
    }

}