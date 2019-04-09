package com.github.bradmartin.smartwearos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.widget.ImageButton
import android.widget.TextView

class PromptActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prompt)

        // Enables Always-on
        setAmbientEnabled()

        // handle the message
        val msgTextView: TextView = findViewById(R.id.message)
        val msg: String = this.intent.getStringExtra("MESSAGE")
        msgTextView.text = msg

        // handle cancel click
        val cancelBtn: ImageButton = findViewById(R.id.cancelBtn)
        cancelBtn.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            this.finish()
        }

        // handle confirm button by returning string in the user_input text view
        val confirmBtn: ImageButton = findViewById(R.id.confirmBtn)
        confirmBtn.setOnClickListener {
            val userInput = findViewById<TextView>(R.id.user_input)
            val textResult = userInput.text
            val resultIntent = Intent()
            resultIntent.putExtra("TEXT", textResult)
            setResult(Activity.RESULT_OK, resultIntent)
            this.finish()
        }

    }

}
