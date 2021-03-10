package Val

import Environment
import Exp.ExprC
import Symbol

class CloV(var params: List<Symbol>, var body: ExprC, var env: Environment) : Value() {

    override fun serialize(): String {
        return "#<procedure>"
    }

}