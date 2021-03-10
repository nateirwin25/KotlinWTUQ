package Exp

import Environment
import Val.Value

open abstract class ExprC {

    abstract fun interp(env : Environment) : Value;

}