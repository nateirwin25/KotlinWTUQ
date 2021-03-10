import ExprC
//open class Value()
//class BoolV(var bool: Boolean) : Value()
//class StringV(var str: String) : Value()
//class CloV(var parms: List<String>, var body: Value) : Value()
class PrimV(var oper: Char, var operands: List<ExprC>)

class Binding(var name: Char, var v: Value)
class Env(var bindings: List<Binding>)

//open class ExprC()
//class NumC(var n: Int) : ExprC()
//class StringC(var str: String) : ExprC()
//class ifC(var x: ExprC, var th: ExprC, var el: ExprC) : ExprC()
//class IdC(var x: Char) : ExprC()
//class AppC(var func: ExprC, var args: List<ExprC>) : ExprC()
//class LamC(var args: List<Char>, var body: ExprC) : ExprC()

fun interp(expr: ExprC, env: Env) {
    println(expr)
}

fun main() {
    println("Hello")
}