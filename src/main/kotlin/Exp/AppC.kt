package Exp

import Environment
import Val.CloV
import Val.PrimV
import Val.Value
import java.lang.Exception

class AppC(var func: ExprC, var args: List<ExprC>) : ExprC() {

    override fun interp(env: Environment): Value {
        if (func.interp(env) is CloV) {
            val interpretedFunc: CloV = func.interp(env) as CloV
            val evaluatedArgs: ArrayList<Value> = arrayListOf()

            for (argument in args)
                evaluatedArgs.add(argument.interp(env))


            for ((index, evaluatedValue) in evaluatedArgs.withIndex()) {
//                we are adding clov's symbols and their respective evaluated values to clov's env
                interpretedFunc.env.extendEnv(interpretedFunc.params[index], evaluatedValue)
            }

//            interpretedFunc.env should be a new env
            return interpretedFunc.body.interp(interpretedFunc.env)

        } else if (func.interp(env) is PrimV) {
            val interpretedFunc: PrimV = func.interp(env) as PrimV
            val evaluatedArgs: ArrayList<Value> = arrayListOf()

            for (argument in args)
                evaluatedArgs.add(argument.interp(env))

            return interpretedFunc.op(evaluatedArgs)

        } else {
            throw Exception("Not of CloV or PrimV type!")
        }
    }
}
