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
        testIfC()
        testAppC()
        testLamC()
        testSymEq()
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

    @Test
    fun testIfC() {
        val test1 = (IfC (IdC((Symbol("true"))), NumC(4), NumC(3)))
        val test2 = (IfC (IdC((Symbol("false"))), NumC(4), NumC(3)))
        val test3 = (IfC (IdC((Symbol("true"))), StringC("yes"), StringC("no")))
        val test4 = (IfC (IdC((Symbol("false"))), StringC("yes"), StringC("no")))
        val test5 = (IfC (NumC(6), NumC(4), NumC(3)))

        assertEquals(test1.interp(TopLevelEnvironment.getEnvironment()).serialize(), "4")
        assertEquals(test2.interp(TopLevelEnvironment.getEnvironment()).serialize(), "3")
        assertEquals(test3.interp(TopLevelEnvironment.getEnvironment()).serialize(), "yes")
        assertEquals(test4.interp(TopLevelEnvironment.getEnvironment()).serialize(), "no")
        assertThrows(Exception::class.java) {
            test5.interp(TopLevelEnvironment.getEnvironment()).serialize()
        }
    }

    @Test
    fun testAppC() {
        val test1 = (AppC (IdC(Symbol("+")), listOf(NumC(5), NumC(6))))
        val test2 = (AppC (IdC(Symbol("+")), listOf(NumC(0), NumC(6))))
        val test3 = (AppC (IdC(Symbol("+")), listOf(NumC(5), NumC(0))))

        val test4 = (AppC (IdC(Symbol("-")), listOf(NumC(5), NumC(6))))
        val test5 = (AppC (IdC(Symbol("-")), listOf(NumC(0), NumC(6))))
        val test6 = (AppC (IdC(Symbol("-")), listOf(NumC(5), NumC(0))))

        val test7 = (AppC (IdC(Symbol("*")), listOf(NumC(5), NumC(6))))
        val test8 = (AppC (IdC(Symbol("*")), listOf(NumC(0), NumC(6))))
        val test9 = (AppC (IdC(Symbol("*")), listOf(NumC(5), NumC(0))))

        val test10 = (AppC (IdC(Symbol("/")), listOf(NumC(30), NumC(6))))
        val test11 = (AppC (IdC(Symbol("/")), listOf(NumC(0), NumC(6))))
        val test12 = (AppC (IdC(Symbol("/")), listOf(NumC(5), NumC(0))))

        val test13 = (AppC (IdC(Symbol("error")), listOf(StringC("test"))))

        val test14 = (AppC (IdC(Symbol("equal?")), listOf(NumC(5), NumC(0))))
        val test15 = (AppC (IdC(Symbol("equal?")), listOf(NumC(5), NumC(5))))
        val test16 = (AppC (IdC(Symbol("equal?")), listOf(IdC(Symbol("true")), IdC(Symbol("true")))))
        val test17 = (AppC (IdC(Symbol("equal?")), listOf(IdC(Symbol("true")), IdC(Symbol("false")))))

        val test18 = (AppC (IdC(Symbol("+")), listOf(
            (AppC (IdC(Symbol("+")), listOf(NumC(5), NumC(6)))),
            (AppC (IdC(Symbol("-")), listOf(NumC(5), NumC(6))))
        )))

        val test19 =
            (AppC (IdC(Symbol("*")), listOf(
            (IfC (
                (AppC (IdC(Symbol("equal?")), listOf(NumC(5), NumC(5)))),
                NumC(4), NumC(3))),
            (AppC (IdC(Symbol("-")), listOf(NumC(5), NumC(6)))))))

        val test20 = (AppC (IdC(Symbol("equal?")), listOf(
            (IfC (
                (AppC (IdC(Symbol("equal?")), listOf(NumC(5), NumC(6)))),
                (IdC(Symbol("true"))),
                (IdC(Symbol("false")))
            )),
            (AppC (IdC(Symbol("equal?")), listOf(IdC(Symbol("true")), IdC(Symbol("true"))))))))

        val test21 = (AppC (StringC("hello"), listOf(NumC(5), NumC(6))))

        val test22 = (AppC (IdC(Symbol("+")), listOf(NumC(5), NumC(6), NumC(7))))
        val test23 = (AppC (IdC(Symbol("+")), listOf(NumC(5), StringC("hello"))))

        val test24 = (AppC (IdC(Symbol("equal?")), listOf(StringC("hello"), StringC("hello"))))
        val test25 = (AppC (IdC(Symbol("equal?")), listOf(StringC("hello"), StringC("goodbye"))))
        val test26 = (AppC (IdC(Symbol("error")), listOf(StringC("test"), StringC("hello"))))
        val test27 = (AppC (IdC(Symbol("equal?")), listOf(StringC("hello"), StringC("goodbye"), StringC("bye"))))

        val test28 = (AppC (IdC(Symbol("<=")), listOf(NumC(30), NumC(6))))
        val test29 = (AppC (IdC(Symbol("<=")), listOf(NumC(0), NumC(0))))
        val test30 = (AppC (IdC(Symbol("<=")), listOf(NumC(-5), NumC(0))))
        val test31 = (AppC (IdC(Symbol("equal?")), listOf(StringC("hello"), NumC(5))))

        assertEquals(test1.interp(TopLevelEnvironment.getEnvironment()).serialize(), "11")
        assertEquals(test2.interp(TopLevelEnvironment.getEnvironment()).serialize(), "6")
        assertEquals(test3.interp(TopLevelEnvironment.getEnvironment()).serialize(), "5")
        assertEquals(test4.interp(TopLevelEnvironment.getEnvironment()).serialize(), "-1")
        assertEquals(test5.interp(TopLevelEnvironment.getEnvironment()).serialize(), "-6")
        assertEquals(test6.interp(TopLevelEnvironment.getEnvironment()).serialize(), "5")
        assertEquals(test7.interp(TopLevelEnvironment.getEnvironment()).serialize(), "30")
        assertEquals(test8.interp(TopLevelEnvironment.getEnvironment()).serialize(), "0")
        assertEquals(test9.interp(TopLevelEnvironment.getEnvironment()).serialize(), "0")
        assertEquals(test10.interp(TopLevelEnvironment.getEnvironment()).serialize(), "5")
        assertEquals(test11.interp(TopLevelEnvironment.getEnvironment()).serialize(), "0")
        assertThrows(Exception::class.java) {
            test12.interp(TopLevelEnvironment.getEnvironment()).serialize()
        }
        assertThrows(Exception::class.java) {
            test13.interp(TopLevelEnvironment.getEnvironment()).serialize()
        }
        assertEquals(test14.interp(TopLevelEnvironment.getEnvironment()).serialize(), "false")
        assertEquals(test15.interp(TopLevelEnvironment.getEnvironment()).serialize(), "true")
        assertEquals(test16.interp(TopLevelEnvironment.getEnvironment()).serialize(), "true")
        assertEquals(test17.interp(TopLevelEnvironment.getEnvironment()).serialize(), "false")
        assertEquals(test18.interp(TopLevelEnvironment.getEnvironment()).serialize(), "10")
        assertEquals(test19.interp(TopLevelEnvironment.getEnvironment()).serialize(), "-4")
        assertEquals(test20.interp(TopLevelEnvironment.getEnvironment()).serialize(), "false")
        assertThrows(Exception::class.java) {
            test21.interp(TopLevelEnvironment.getEnvironment()).serialize()
        }
        assertThrows(Exception::class.java) {
            test22.interp(TopLevelEnvironment.getEnvironment()).serialize()
        }
        assertThrows(Exception::class.java) {
            test23.interp(TopLevelEnvironment.getEnvironment()).serialize()
        }
        assertEquals(test24.interp(TopLevelEnvironment.getEnvironment()).serialize(), "true")
        assertEquals(test25.interp(TopLevelEnvironment.getEnvironment()).serialize(), "false")
        assertThrows(Exception::class.java) {
            test26.interp(TopLevelEnvironment.getEnvironment()).serialize()
        }
        assertThrows(Exception::class.java) {
            test27.interp(TopLevelEnvironment.getEnvironment()).serialize()
        }
        assertEquals(test28.interp(TopLevelEnvironment.getEnvironment()).serialize(), "false")
        assertEquals(test29.interp(TopLevelEnvironment.getEnvironment()).serialize(), "true")
        assertEquals(test30.interp(TopLevelEnvironment.getEnvironment()).serialize(), "true")
        assertEquals(test31.interp(TopLevelEnvironment.getEnvironment()).serialize(), "false")
    }

    @Test
    fun testLamC() {
        val test1 = (LamC(
            listOf(Symbol("x"), Symbol("y")),
            IfC(
                (AppC(IdC(Symbol("equal?")), listOf(NumC(5), NumC(6)))),
                (IdC(Symbol("true"))),
                (IdC(Symbol("false")))
            )
        ))

        val test2 = (AppC(
            LamC(
                listOf(Symbol("x")),
                AppC(
                    LamC(
                        listOf(Symbol("y")),
                        AppC(IdC(Symbol("+")), listOf(IdC(Symbol("x")), IdC(Symbol("y"))))
                    ),
                    listOf(NumC(3))
                )
            ),
            listOf(NumC(2))
        )
                )

        val test3 = (AppC(
            LamC(
                listOf(Symbol("x"), Symbol("y")),
                AppC(IdC(Symbol("+")), listOf(IdC(Symbol("x")), IdC(Symbol("y"))))
            ),
            listOf(NumC(5), (NumC(4)))
        )
                )

        val test4 = (AppC(
            LamC(
                listOf(Symbol("x"), Symbol("y")),
                AppC(IdC(Symbol("+")), listOf(IdC(Symbol("x")), IdC(Symbol("k"))))
            ),
            listOf(NumC(5), (NumC(4)))
        )
                )

        val test5 = (AppC(
            LamC(
                listOf(Symbol("x"), Symbol("y")),
                AppC(
                    LamC(
                        listOf(Symbol("z")),
                        AppC(
                            IdC(Symbol("+")),
                            listOf(
                                IdC(Symbol("x")),
                                AppC(
                                    IdC(Symbol("+")),
                                    listOf(IdC(Symbol("y")), IdC(Symbol("z")))
                                )
                            )
                        )
                    ),
                    listOf(NumC(3))
                )
            ),
            listOf(NumC(5), (NumC(4)))
        )
                )

        val test6 = (AppC(
            LamC(
                listOf(Symbol("x"), Symbol("y")),
                AppC(
                    LamC(
                        listOf(Symbol("z")),
                        AppC(
                            IdC(Symbol("+")),
                            listOf(
                                IfC(
                                    AppC(IdC(Symbol("<=")), listOf(IdC(Symbol("y")), (NumC(0)))),
                                    NumC(3),
                                    IdC(Symbol("x"))
                                ),
                                AppC(
                                    IdC(Symbol("+")),
                                    listOf(IdC(Symbol("y")), IdC(Symbol("z")))
                                )
                            )
                        )
                    ),
                    listOf(NumC(3))
                )
            ),
            listOf(NumC(5), (NumC(4)))
        )
                )

        // like (top-interp '{{lam {x y} {
        //                          {lam {z} {+ {if {<= y 0} 3 x} {+ y z}}} 3}} 5 -1})

        val test7 = AppC (
                        LamC(
                            listOf(Symbol("x"), Symbol("y")),
                            AppC(
                                LamC(
                                    listOf(Symbol("z")),
                                    AppC(
                                        IdC(Symbol("+")),
                                        listOf(IfC (
                                            AppC(IdC(Symbol("<=")), listOf(IdC(Symbol("y")), (NumC(0)))),
                                            NumC(3),
                                            IdC(Symbol("x"))
                                        ),
                                            AppC(
                                                IdC(Symbol("+")),
                                                listOf(IdC(Symbol("y")), IdC(Symbol("z")))
                                            )
                                        )
                                    )
                                ),
                                listOf(NumC(3))
                            )
                        ),
                        listOf(NumC(5), (NumC(-1)))
                    )


        assertEquals(test1.interp(TopLevelEnvironment.getEnvironment()).serialize(), "#<procedure>")
        assertEquals(test2.interp(TopLevelEnvironment.getEnvironment()).serialize(), "5")
        assertEquals(test3.interp(TopLevelEnvironment.getEnvironment()).serialize(), "9")
        assertThrows(Exception::class.java) {
            test4.interp(TopLevelEnvironment.getEnvironment()).serialize()
        }
        assertEquals(test5.interp(TopLevelEnvironment.getEnvironment()).serialize(), "12")
        assertEquals(test6.interp(TopLevelEnvironment.getEnvironment()).serialize(), "12")
        assertEquals(test7.interp(TopLevelEnvironment.getEnvironment()).serialize(), "5")
    }

    @Test
    fun testSymEq() {
        val sym1 = Symbol("hello")

        assertEquals(sym1.equals(Symbol("Hello")), false)
    }
}
