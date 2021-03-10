package Exp

import Symbol
import Val.Value

class LamC(var params: List<Symbol>, var body: ExprC) : ExprC() {

    override fun interp(): Value {
        TODO("Not yet implemented")
    }
}