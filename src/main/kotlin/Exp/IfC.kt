package Exp

import Environment
import Val.BoolV
import Val.Value

class IfC(var a: ExprC, var b: ExprC, var c: ExprC) : ExprC() {

    override fun interp(env : Environment) : Value {
        val result = a.interp(env)
        if(result is BoolV) {
            if (result.bool) {
                return b.interp(env)
            } else {
                return c.interp(env)
            }
        } else {
            throw Exception("First argument to IfC is not a boolean")
        }
    }

}