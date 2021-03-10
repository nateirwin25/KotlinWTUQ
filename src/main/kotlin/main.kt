import Exp.*
import Val.PrimV
import Val.Value

fun main() {

    println("Hello")

    var test = AppC(IdC(Symbol("+")), listOf(NumC(3), NumC(4)))
    var test2 = IfC(
        AppC(IdC(Symbol("<=")), listOf(NumC(2), NumC(3))),
        StringC("Passed"),
        StringC("Failed"))
    var test3 =
        AppC(
            LamC(listOf(Symbol("+")),
                AppC(IdC(Symbol("*")), listOf(IdC(Symbol("+")), IdC(Symbol("+"))))),
            listOf(NumC(14)))

    println(test.interp(TopLevelEnvironment.getEnvironment()).serialize())
    println(test2.interp(TopLevelEnvironment.getEnvironment()).serialize())
    println(test3.interp(TopLevelEnvironment.getEnvironment()).serialize())

}