package Exp

import Environment
import Val.Value

abstract class ExprC {

    abstract fun interp(env : Environment) : Value

}
