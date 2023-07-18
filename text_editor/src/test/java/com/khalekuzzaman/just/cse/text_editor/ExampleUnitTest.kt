package com.khalekuzzaman.just.cse.text_editor

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Calculator(
    val num1: Int, val num2: Int) {
    fun add(): Int {
        return num1 + num2
    }
}

            class CalculatorTest {

            @Test
            fun addition_isCorrect() {
                assertEquals(4, Calculator(2,2).add())
    }
}
