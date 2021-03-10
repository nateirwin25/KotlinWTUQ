import Exp.*
import Val.PrimV
import Val.Value
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class tests() {

    fun runTests() {
        testNumC()
        testStringC()
        testIdC()
    }

    @Test
    fun testNumC() {
        val test1 = (NumC(0))
        val test2 = (NumC(2312))
        val test3 = (NumC(-32))
        val test4 = (NumC(4))

        assertEquals(test1.interp(TopLevelEnvironment.getEnvironment()).serialize(), "0")
        assertEquals(test2.interp(TopLevelEnvironment.getEnvironment()).serialize(), "2312")
        assertEquals(test3.interp(TopLevelEnvironment.getEnvironment()).serialize(), "-32")
        assertEquals(test4.interp(TopLevelEnvironment.getEnvironment()).serialize(), "4")
    }

    @Test
    fun testStringC() {
        val test1 = (StringC(""))
        val test2 = (StringC("hello"))
        val test3 = (StringC("goodbye"))

        assertEquals(test1.interp(TopLevelEnvironment.getEnvironment()).serialize(), "")
        assertEquals(test2.interp(TopLevelEnvironment.getEnvironment()).serialize(), "hello")
        assertEquals(test3.interp(TopLevelEnvironment.getEnvironment()).serialize(), "goodbye")
    }

    @Test
    fun testIdC() {
        val test1 = (IdC(Symbol("+")))
        val test2 = (IdC(Symbol("-")))
        val test3 = (IdC(Symbol("*")))
        val test4 = (IdC(Symbol("/")))
        val test5 = (IdC(Symbol("true")))
        val test6 = (IdC(Symbol("false")))
        val test7 = (IdC(Symbol("equal?")))
        val test8 = (IdC(Symbol("error")))
        val test9 = (IdC(Symbol("<=")))

        assertEquals(test1.interp(TopLevelEnvironment.getEnvironment()).serialize(), "#<primop>")
        assertEquals(test2.interp(TopLevelEnvironment.getEnvironment()).serialize(), "#<primop>")
        assertEquals(test3.interp(TopLevelEnvironment.getEnvironment()).serialize(), "#<primop>")
        assertEquals(test4.interp(TopLevelEnvironment.getEnvironment()).serialize(), "#<primop>")
        assertEquals(test5.interp(TopLevelEnvironment.getEnvironment()).serialize(), "true")
        assertEquals(test6.interp(TopLevelEnvironment.getEnvironment()).serialize(), "false")
        assertEquals(test7.interp(TopLevelEnvironment.getEnvironment()).serialize(), "#<primop>")
        assertEquals(test8.interp(TopLevelEnvironment.getEnvironment()).serialize(), "#<primop>")
        assertEquals(test9.interp(TopLevelEnvironment.getEnvironment()).serialize(), "#<primop>")
    }
}
