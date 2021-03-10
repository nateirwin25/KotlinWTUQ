import Val.Value
import java.lang.Exception
import kotlin.math.E

class Environment(var env: ArrayList<Pair<Symbol, Value>> = ArrayList<Pair<Symbol, Value>>()) {

    fun add(s: Symbol, v: Value) {
        env.add(Pair(s, v))
    }

    fun get(s: Symbol) : Value {
        env.reversed().forEach {
            if(it.first.sym.equals(s.sym)) {
                return it.second
            }
        }
        throw Exception("Symbol not found in environment: $s")
    }

    fun extendEnv(s: Symbol, v: Value) : Environment {
        var temp = Environment(env)
        temp.add(s, v)
        return temp
    }

}