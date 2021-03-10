package Exp

import Environment
import Val.BoolV

class IfC(var a: ExprC, var b: ExprC, var c: ExprC) : ExprC() {

    override fun interp(env : Environment) : BoolV {
        if(a.interp() is BoolV) {
            if (a.interp().bool) {
                return b.interp()
            } else {
                return c.interp()
            }
        } else {
            throw Exception("First argument to IfC is not a boolean")
        }
    }

}