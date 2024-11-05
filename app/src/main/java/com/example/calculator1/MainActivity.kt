package com.example.calculator1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var display: EditText
    private var operator: String? = null
    private var firstOperand: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        val buttons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.buttonPlus, R.id.buttonMinus,
            R.id.buttonMultiply, R.id.buttonDivide, R.id.buttonClear, R.id.buttonEquals
        )

        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener { onButtonClick(it) }
        }
    }

    private fun onButtonClick(view: View) {
        val button = view as Button
        when (button.text) {
            "C" -> clear()
            "=" -> calculate()
            "+", "-", "*", "/" -> setOperator(button.text.toString())
            else -> display.append(button.text)
        }
    }

    private fun clear() {
        display.text.clear()
        operator = null
        firstOperand = 0.0
    }

    private fun setOperator(op: String) {
        if (operator == null) {
            firstOperand = display.text.toString().toDoubleOrNull() ?: 0.0
            operator = op
            display.text.clear()
        }
    }

    private fun calculate() {
        if (operator != null) {
            val secondOperand = display.text.toString().toDoubleOrNull() ?: 0.0
            val result = when (operator) {
                "+" -> firstOperand + secondOperand
                "-" -> firstOperand - secondOperand
                "*" -> firstOperand * secondOperand
                "/" -> if (secondOperand != 0.0) firstOperand / secondOperand else "Ошибка"
                else -> 0.0
            }
            display.setText(result.toString())
            operator = null
        }
    }
}
