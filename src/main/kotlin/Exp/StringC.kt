package Exp

import Environment
import Val.StringV

class StringC(var stringVal: String) : ExprC() {

    override fun interp(env: Environment): StringV {
        return StringV(stringVal)
    }

}
