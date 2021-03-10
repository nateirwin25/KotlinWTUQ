import Val.Value

class Environment {

    var env : ArrayList<Pair<Symbol, Value>>

    init {
        env = ArrayList<Pair<Symbol, Value>>()
    }

    fun add(s : Symbol, v : Value) {
        env.add(Pair(s, v))
    }

    fun get(s: Symbol) : Value? {
        env.forEach {
            if(it.first.equals(s)) {
                return it.second
            }
        }
        return null
    }

}