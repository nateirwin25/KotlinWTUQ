import Exp.ExprC
import Val.Value

//open class Val.Value()
//class BoolV(var bool: Boolean) : Val.Value()
//class StringV(var str: String) : Val.Value()
//class Val.CloV(var parms: List<String>, var body: Val.Value) : Val.Value()
class PrimV(var oper: Char, var operands: List<ExprC>)

class Binding(var name: Char, var v: Value)
class Env(var bindings: List<Binding>)

//open class Exp.ExprC()
//class Exp.NumC(var n: Int) : Exp.ExprC()
//class StringC(var str: String) : Exp.ExprC()
//class ifC(var x: Exp.ExprC, var th: Exp.ExprC, var el: Exp.ExprC) : Exp.ExprC()
//class IdC(var x: Char) : Exp.ExprC()
//class AppC(var func: Exp.ExprC, var args: List<Exp.ExprC>) : Exp.ExprC()
//class Exp.LamC(var args: List<Char>, var body: Exp.ExprC) : Exp.ExprC()

fun interp(expr: ExprC, env: Env) {
    println(expr)
}

fun main() {
    println("Hello")
}