import Val.NumV
import Val.PrimV
import Val.Value
import Val.BoolV
import Val.StringV
import java.lang.Exception
import kotlin.math.E

class TopLevelEnvironment {

    companion object {

        fun verifyBinopArgs(args: List<Value>, op: (NumV, NumV) -> Value) : Value {
            if(args.size == 2) {
                val first = args.get(0)
                val second = args.get(1)
                if(first is NumV && second is NumV) {
                    return op(first, second)
                }
            }
            throw Exception("Wrong arguments to binary operator")
        }

        fun eq(args: List<Value>) : Value {
            if(args.size == 2) {
                val first = args.get(0)
                val second = args.get(1)
                if(first is NumV && second is NumV)
                    return BoolV(first.get() == second.get())
                else if(first is BoolV && second is BoolV)
                    return BoolV(first.bool == second.bool)
                else if(first is StringV && second is StringV)
                    return BoolV(first.str == second.str)
                else
                    return BoolV(false)
            }
            throw Exception("equal? had wrong number of arguments")
        }

        fun getEnvironment() : Environment {
            var topenv = Environment()


            topenv.add(Symbol("+"),
                PrimV { args: List<Value> -> verifyBinopArgs(args) { a: NumV, b: NumV -> NumV(a.get() + b.get())} })
            topenv.add(Symbol("-"),
                PrimV { args: List<Value> -> verifyBinopArgs(args) { a: NumV, b: NumV -> NumV(a.get() - b.get())} })
            // Need to verify divide by zero
            topenv.add(Symbol("/"),
                PrimV { args: List<Value> -> verifyBinopArgs(args) { a: NumV, b: NumV -> NumV(a.get() / b.get())} })
            topenv.add(Symbol("*"),
                PrimV { args: List<Value> -> verifyBinopArgs(args) { a: NumV, b: NumV -> NumV(a.get() * b.get())} })

            topenv.add(Symbol("true"), BoolV(true))
            topenv.add(Symbol("false"), BoolV(false))

            topenv.add(Symbol("error"), PrimV { args: List<Value> ->
                if(args.size == 1) {
                    throw Exception(args.get(0).serialize());
                } else
                    throw Exception("error had incorrect arguments")
            })

            topenv.add(Symbol("equal?"), PrimV { args: List<Value> -> eq(args)})

            topenv.add(Symbol("<="),
                PrimV { args: List<Value> -> verifyBinopArgs(args) { a: NumV, b: NumV -> BoolV(a.n <= b.n)} })



            return topenv
        }
    }

}