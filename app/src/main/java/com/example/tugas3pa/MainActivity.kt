package com.example.tugas3pa

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentInput = ""
    private var operator = ""
    private var firstNumber = ""
    private var secondNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.tvDisplay)

        // Numbers
        val numberButtons = listOf<Button>(
            findViewById(R.id.btn0), findViewById(R.id.btn1), findViewById(R.id.btn2),
            findViewById(R.id.btn3), findViewById(R.id.btn4), findViewById(R.id.btn5),
            findViewById(R.id.btn6), findViewById(R.id.btn7), findViewById(R.id.btn8), findViewById(R.id.btn9)
        )

        numberButtons.forEach { button ->
            button.setOnClickListener { appendNumber(button.text.toString()) }
        }

        // Operators
        findViewById<Button>(R.id.btnAdd).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { setOperator("/") }

        // Equals
        findViewById<Button>(R.id.btnEquals).setOnClickListener { calculateResult() }

        // Clear
        findViewById<Button>(R.id.btnClear).setOnClickListener { clearDisplay() }
    }

    private fun appendNumber(number: String) {
        currentInput += number
        display.text = currentInput
    }

    private fun setOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            firstNumber = currentInput
            operator = op
            currentInput = ""
        }

    }

    private fun calculateResult() {
        if (firstNumber.isNotEmpty() && currentInput.isNotEmpty()) {
            secondNumber = currentInput
            val result = when (operator) {
                "+" -> firstNumber.toDouble() + secondNumber.toDouble()
                "-" -> firstNumber.toDouble() - secondNumber.toDouble()
                "*" -> firstNumber.toDouble() * secondNumber.toDouble()
                "/" -> firstNumber.toDouble() / secondNumber.toDouble()
                else -> 0.0
            }
            display.text = result.toString()
            currentInput = result.toString()
            firstNumber = ""
            secondNumber = ""
        }
    }

    private fun clearDisplay() {
        currentInput = ""
        firstNumber = ""
        secondNumber = ""
        operator = ""
        display.text = "0"
    }
}
