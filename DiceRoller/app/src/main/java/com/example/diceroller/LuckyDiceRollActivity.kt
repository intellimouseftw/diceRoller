package com.example.diceroller

import android.graphics.Typeface
import android.media.Image
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton


class LuckyDiceRollActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_luckydice)

        var triesLeft = 3
        var toastMessage: Toast? = null

        val quitLuckyDiceButton: ExtendedFloatingActionButton = findViewById(R.id.quit_lucky_dice)
        quitLuckyDiceButton.setOnClickListener{
            toastMessage?.cancel()
            finish()
        }

        val luckyDiceResult = generateDiceVal()

        val luckyDiceTextView: TextView = findViewById(R.id.title)
        luckyDiceTextView.text = "Lucky Dice to Roll to is $luckyDiceResult"
        val luckyDiceImageView: ImageView = findViewById(R.id.lucky_dice_image)
        val drawableResource = when (luckyDiceResult) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        luckyDiceImageView.setImageResource(drawableResource)

        val boldText = "$luckyDiceResult"
        val normalText = "Lucky Dice to Roll to is "
        val str = SpannableString(normalText + boldText)
        str.setSpan(
            StyleSpan(Typeface.BOLD),
            normalText.length,
            normalText.length + boldText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        luckyDiceTextView.text = str

        val diceResultImageView: ImageView = findViewById(R.id.dice_image)
        val diceResultTextView: TextView = findViewById(R.id.dice_result)

        val rollButton : ExtendedFloatingActionButton = findViewById(R.id.roll_button)
        rollButton.setOnClickListener{
            if (toastMessage != null) {
                toastMessage?.cancel()
                toastMessage = null
            }

            if (triesLeft!=0) {
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

                val isLuckyDiceRolled = checkLuckyDiceResult(luckyDiceResult, diceResult)

                if (isLuckyDiceRolled) {
                    toastMessage =
                        Toast.makeText(this, "Congratulations, you won!", Toast.LENGTH_LONG)
                    rollButton.visibility = View.INVISIBLE
                } else {
                    triesLeft -= 1
                    toastMessage = Toast.makeText(
                        this,
                        "Sorry, try again. You have ${triesLeft} more tries",
                        Toast.LENGTH_LONG
                    )
                }

            } else {
                toastMessage = Toast.makeText(this, "You have ran out of tries... Try again next time", Toast.LENGTH_LONG)
            }
            toastMessage?.show()
        }
    }

    private fun generateDiceVal(): Int {
        val diceRange = 1..6
        return diceRange.random()
    }

    private fun checkLuckyDiceResult(luckyDiceVal: Int, currentDiceVal: Int): Boolean {
        return luckyDiceVal == currentDiceVal
    }
}