package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
//This calculator has problems
//I can't do multiple operation at one time, I need to press equal every single time
//I can press Dot two times after each other
class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var buttonPlus: Button
    lateinit var buttonMinus: Button
    lateinit var buttonDiv: Button
    lateinit var buttonResult: Button
    lateinit var buttonMulti: Button
    lateinit var buttonClear: Button
    lateinit var buttonPercentage : Button
    lateinit var buttonSignChange:Button
    lateinit var buttonDot:Button
    var lastNumber:Double = 0.0
    var currentOperation:Operation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        addCallBacks()
    }

    private fun addCallBacks() {
        buttonClear.setOnClickListener{
            clearInput()
        }
        buttonPlus.setOnClickListener{
            prepareOperation(Operation.Plus)

        }
        buttonMinus.setOnClickListener{
            prepareOperation(Operation.Minus)
        }
        buttonMulti.setOnClickListener{
            prepareOperation(Operation.Mul)
        }
        buttonDiv.setOnClickListener{
            prepareOperation(Operation.Div)
        }
        buttonPercentage.setOnClickListener{
            prepareOperation(Operation.Percen)
        }
        buttonResult.setOnClickListener{
            val result = doCurrentOperation()
            textView.text = result.toString()
        }
        buttonSignChange.setOnClickListener{
            lastNumber = textView.text.toString().toDouble()*(-1)
            textView.text = lastNumber.toString()
        }

    }

    private fun doCurrentOperation():Double {
        val secondNumber = textView.text.toString().toDouble()
        return when(currentOperation){
            Operation.Plus -> lastNumber+secondNumber
            Operation.Minus -> lastNumber-secondNumber
            Operation.Mul -> lastNumber*secondNumber
            Operation.Div -> lastNumber/secondNumber
            Operation.Percen -> lastNumber % secondNumber
            null -> 0.0
        }
    }

    private fun initView() {
        buttonSignChange = findViewById(R.id.btn_plusMinus)
        buttonDot = findViewById(R.id.btn_Dot)
        buttonPlus = findViewById(R.id.btn_plus)
        buttonDiv = findViewById(R.id.btn_divide)
        buttonMinus = findViewById(R.id.btn_minus)
        buttonMulti = findViewById(R.id.btn_multi)
        buttonResult = findViewById(R.id.btn_equal)
        textView = findViewById(R.id.tv_number)

        buttonClear = findViewById(R.id.btn_c)
        buttonPercentage = findViewById(R.id.btn_percentage)
    }
    private fun clearInput(){
        textView.text = ""
    }
    fun onClickNumber(v:View){
        val oldDigit = textView.text.toString()

       //buttonDot.isEnabled = !oldDigit.contains('.')
       val newDigit = (v as Button).text.toString()
        buttonDot.isEnabled = !oldDigit.contains('.')
        val newTextNumber = oldDigit + newDigit
        textView.text = newTextNumber

    }
    private fun prepareOperation(operation: Operation){
        lastNumber = textView.text.toString().toDouble()
        clearInput()
        currentOperation = operation
    }
}