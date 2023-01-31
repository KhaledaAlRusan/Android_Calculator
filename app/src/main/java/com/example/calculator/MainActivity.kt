package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.calculator.databinding.ActivityMainBinding

//This calculator has problems
//I can't do multiple operation at one time, I need to press equal every single time
//I can press Dot two times after each other
class MainActivity : AppCompatActivity() {
    var lastNumber:Double = 0.0
    var currentOperation:Operation? = null
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addCallBacks()
    }

    private fun addCallBacks() {

        binding.btnC.setOnClickListener{
            clearInput()
        }

        binding.btnPlus.setOnClickListener{
            prepareOperation(Operation.Plus)

        }
        binding.btnMinus.setOnClickListener{
            prepareOperation(Operation.Minus)
        }
        binding.btnMulti.setOnClickListener{
            prepareOperation(Operation.Mul)
        }
        binding.btnDivide.setOnClickListener{
            prepareOperation(Operation.Div)
        }
        binding.btnPercentage.setOnClickListener{
            prepareOperation(Operation.Percen)
        }
        binding.btnEqual.setOnClickListener{
            val result = doCurrentOperation()
            binding.tvNumber.text = result.toString()
        }
        binding.btnPlusMinus.setOnClickListener{
            lastNumber = binding.tvNumber.text.toString().toDouble()*(-1)
            binding.tvNumber.text = lastNumber.toString()
        }
    }

    private fun doCurrentOperation():Double {
        val secondNumber = binding.tvNumber.text.toString().toDouble()
        return when(currentOperation){
            Operation.Plus -> lastNumber+secondNumber
            Operation.Minus -> lastNumber-secondNumber
            Operation.Mul -> lastNumber*secondNumber
            Operation.Div -> lastNumber/secondNumber
            Operation.Percen -> lastNumber % secondNumber
            null -> 0.0
        }
    }

    private fun clearInput(){
        binding.tvNumber.text = ""
    }

    fun onClickNumber(v:View){
        val oldDigit = binding.tvNumber.text.toString()
       val newDigit = (v as Button).text.toString()
        binding.btnDot.isEnabled = !oldDigit.contains('.')
        val newTextNumber = oldDigit + newDigit
        binding.tvNumber.text = newTextNumber
    }

    private fun prepareOperation(operation: Operation){
        lastNumber = binding.tvNumber.text.toString().toDouble()
        clearInput()
        currentOperation = operation
    }
}