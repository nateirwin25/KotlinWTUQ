class NumV(var n: Int) : Value() {

    override fun serialize(): String {
        return n.toString();
    }

}