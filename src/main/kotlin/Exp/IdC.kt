package Exp
import Environment
import Symbol
import Val.Value

class IdC(var id: Symbol) : ExprC() {

    override fun interp(env: Environment): Value {
        return env.get(id)
    }

}

