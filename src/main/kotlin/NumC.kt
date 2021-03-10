class NumC(var num: Int) : ExprC() {

    override fun interp() : NumV {
        return NumV(num);
    }

}