package com.jetbrains.simplelogin.multicalculator

class Calculator {
    fun add(left: Int, right: Int): Int {
        return left + right
    }
    fun subtract(left: Int, right: Int): Int {
        return left - right
    }

    fun divide(left: Int, right: Int): Double {
        if (right == 0) {
            throw IllegalArgumentException("Cannot divide by zero")
        }
        return left.toDouble() / right.toDouble()
    }

    fun multiply(left: Int, right: Int): Int {
        return left + right
    }
    fun main() {
        val calculator = Calculator()

        val sumResult = calculator.add(5, 3)
        println("Sum: $sumResult")

        val subtractResult = calculator.subtract(8, 2)
        println("Subtraction: $subtractResult")

        val divideResult = calculator.divide(10, 2)
        println("Division: $divideResult")

        val multiplyResult = calculator.multiply(4, 6)
        println("Multiplication: $multiplyResult")
    }
}