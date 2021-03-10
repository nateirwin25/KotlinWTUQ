package Val

class PrimV(op : (List<Value>) -> Value) : Value() {

    override fun serialize(): String {
        return "#<primop>"
    }

}