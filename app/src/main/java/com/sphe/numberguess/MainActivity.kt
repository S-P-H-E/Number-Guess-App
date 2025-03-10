package com.sphe.numberguess

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Declarations
        val textView: TextView = findViewById(R.id.textView)
        val editText: EditText = findViewById(R.id.editText)
        var number = (0..10).random()
        var greetingNum = (0..4).random()
        val greetingTemp = "type a number between 1 and 10"

        // New message everytime
        fun greeting(greetingPreface : String, greetingNum : Int, greetingTemp : String): String {
            return when (greetingNum) {
                0 -> "$greetingPreface$greetingTemp."
                2 -> "Hmmmm, make a guess between 1 and 10, good luck."
                3 -> "Are you good at guessing? Well, $greetingTemp"
                4 -> "Try to $greetingTemp, see if you get it right!"
                else -> "Error"
            }
        }

        // Set the default greeting
        val greetingMessage = greeting(greetingPreface = "Greetings, ", greetingNum = greetingNum, greetingTemp = greetingTemp)
        textView.text = greetingMessage

        findViewById<Button>(R.id.button).setOnClickListener {
            val guess = editText.text.toString().toIntOrNull()
                if (guess != null) {
                    when {
                        guess < number -> textView.text = "Too low"
                        guess > number -> textView.text = "Too high"
                        else -> {
                            // generate new greeting and greetingPreface
                            greetingNum = (0..4).random()
                            val greetingMessage = greeting(greetingPreface = "Want to try again?, ", greetingNum = greetingNum, greetingTemp = greetingTemp)
                            textView.text = greetingMessage
                            //generate new number to be guessed
                            number = (0..10).random()
                        }
                    }
                } else {
                    textView.text = "Please enter a valid number"
                }
            }
    }
}