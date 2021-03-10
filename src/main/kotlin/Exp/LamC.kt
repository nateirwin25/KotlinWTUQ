package Exp

import Environment
import Symbol
import Val.CloV
import Val.Value

class LamC(var params: List<Symbol>, var body: ExprC) : ExprC() {

    override fun interp(env: Environment): Value {
        return CloV(params, body, env)
    }
}