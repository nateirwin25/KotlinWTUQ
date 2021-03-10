package Exp

import Val.Value

open abstract class ExprC {

    abstract fun interp() : Value;

}