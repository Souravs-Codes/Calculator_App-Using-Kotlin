package com.example.mycalc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.toString

class MainActivity : AppCompatActivity() {
    private lateinit var previousCalculationTextView: TextView
    private lateinit var resultTextView: TextView
    private var firstNumber = 0.0
    private var operation = ""
    private var isNewerOperation = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        resultTextView = findViewById(R.id.resultTextView)
        previousCalculationTextView = findViewById(R.id.previousCalculationTextView)
        val button0: Button = findViewById(R.id.button_0)
        val button1: Button = findViewById(R.id.button_1)
        val button2: Button = findViewById(R.id.button_2)
        val button3: Button = findViewById(R.id.button_3)
        val button4: Button = findViewById(R.id.button_4)
        val button5: Button = findViewById(R.id.button_5)
        val button6: Button = findViewById(R.id.button_6)
        val button7: Button = findViewById(R.id.button_7)
        val button8: Button = findViewById(R.id.button_8)
        val button9: Button = findViewById(R.id.button_9)

        val buttonAdd: Button = findViewById(R.id.button_add)
        val buttonMinus: Button = findViewById(R.id.button_minus)
        val buttonMultiply: Button = findViewById(R.id.button_multiply)
        val buttonDivide: Button = findViewById(R.id.button_Divide)
        val buttonModulus: Button = findViewById(R.id.button_Modulus)

        val buttonPoint: Button = findViewById(R.id.button_point)
        val buttonEqual: Button = findViewById(R.id.button_equal)
        val buttonClear: Button = findViewById(R.id.button_clear)
        val buttonDelete: Button = findViewById(R.id.button_Delete)
        val button00: Button = findViewById(R.id.button_00)

        button0.setOnClickListener { appendNumber("0") }
        button1.setOnClickListener { appendNumber("1") }
        button2.setOnClickListener { appendNumber("2") }
        button3.setOnClickListener { appendNumber("3") }
        button4.setOnClickListener { appendNumber("4") }
        button5.setOnClickListener { appendNumber("5") }
        button6.setOnClickListener { appendNumber("6") }
        button7.setOnClickListener { appendNumber("7") }
        button8.setOnClickListener { appendNumber("8") }
        button9.setOnClickListener { appendNumber("9") }
        buttonPoint.setOnClickListener { appendNumber(".") }

        buttonAdd.setOnClickListener { setOperation("+") }
        buttonMinus.setOnClickListener { setOperation("-") }
        buttonMultiply.setOnClickListener { setOperation("*") }
        buttonDivide.setOnClickListener { setOperation("/") }
        buttonModulus.setOnClickListener { setOperation("%") }

        buttonEqual.setOnClickListener { calculateResult() }
        buttonClear.setOnClickListener { clear() }
        buttonDelete.setOnClickListener { delete() }
        button00.setOnClickListener { appendNumber("00") }


    }

    private fun appendNumber(number: String) {
        if (isNewerOperation) {
            resultTextView.text = number
            isNewerOperation = false
        } else {
            resultTextView.text = "${resultTextView.text}$number"
        }
    }

    private fun clear() {
        resultTextView.text = "0"
        previousCalculationTextView.text = ""
        firstNumber = 0.0
        operation = ""
        isNewerOperation = true
    }

    private fun delete() {
        if (resultTextView.text.isNotEmpty() && resultTextView.text!= "0.0" && resultTextView.text != "Error") {
            resultTextView.text = resultTextView.text.toString().dropLast(1)
        }else{
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show()
        }
    }



    private fun setOperation(op: String) {
        firstNumber = resultTextView.text.toString().toDouble()
        operation = op
        isNewerOperation = true
        previousCalculationTextView.text = "$firstNumber $operation"
        resultTextView.text = "0"
    }

    private fun calculateResult() {
        try {
            val secondNumber = resultTextView.text.toString().toDouble()

            val result = when (operation) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "*" -> firstNumber * secondNumber
                "/" -> firstNumber / secondNumber
                "%" -> firstNumber % secondNumber
                else -> secondNumber
            }
            resultTextView.text = result.toString()
            previousCalculationTextView.text = "$firstNumber $operation $secondNumber ="
            firstNumber = result
            isNewerOperation = true
        } catch (e: NumberFormatException) {
            resultTextView.text = "Error"
            previousCalculationTextView.text = ""
            firstNumber = 0.0
            operation = ""
            isNewerOperation = true
        }
    }
}












