package Exp

import Environment
import Val.NumV

class NumC(var num: Int) : ExprC() {

    override fun interp(env: Environment) : NumV {
        return NumV(num);
    }

}