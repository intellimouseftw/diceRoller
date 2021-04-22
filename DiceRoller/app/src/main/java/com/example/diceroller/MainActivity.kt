package com.example.diceroller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val diceResultTextView: TextView = findViewById(R.id.dice_result)
        val diceResultImageView: ImageView = findViewById(R.id.dice_image)

        findViewById<ExtendedFloatingActionButton>(R.id.lucky_dice_button).setOnClickListener {
            startActivity(Intent(this, LuckyDiceRollActivity::class.java))
        }

        val rollButton :ExtendedFloatingActionButton = findViewById(R.id.roll_button)
        rollButton.setOnClickListener{
            val diceResult = generateDiceVal()
            Log.i("dice value", "$diceResult")
            diceResultTextView.text = "$diceResult"

            val drawableResource = when (diceResult) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            diceResultImageView.setImageResource(drawableResource)

        }
    }

    private fun generateDiceVal(): Int {
        val diceRange = 1..6
        return diceRange.random()
    }
}