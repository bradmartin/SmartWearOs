package com.github.bradmartin.smartwearos

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.support.wearable.activity.WearableActivity
import android.view.View
import android.widget.ImageButton
import android.widget.TextView


class ConfirmationActivity : WearableActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        // Enables Always-on
        setAmbientEnabled()

        // handle the title, if not provided remove it
        val titleTextView: TextView = findViewById(R.id.title)
        if (this.intent.hasExtra("TITLE")) {
            val title: String = this.intent.getStringExtra("TITLE")
            titleTextView.text = title
            titleTextView.visibility = View.VISIBLE
        } else {
            titleTextView.visibility = View.GONE
        }

        // if auto close time is provided
        // set the handler to run after time provided and returning CANCELED
        if (this.intent.hasExtra("AUTO_CLOSE_TIME")) {
            val autoCloseTime = this.intent.getIntExtra("AUTO_CLOSE_TIME", 5)
            val convertedDismissTime: Long = (autoCloseTime * 1000).toLong()
            // run the handler after the auto close time
            Handler().postDelayed(
                {
                    setResult(Activity.RESULT_CANCELED)
                    this.finish()
                }, convertedDismissTime
            )
        }


        // handle the message
        val msgText: String = this.intent.getStringExtra("MESSAGE")
        val msgTextView: TextView = findViewById(R.id.message)
        msgTextView.text = msgText

        // handle cancel click
        val cancelBtn: ImageButton = findViewById(R.id.cancelBtn)
        cancelBtn.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            this.finish()
        }

        // handle confirm button by returning true
        val confirmBtn: ImageButton = findViewById(R.id.confirmBtn)
        confirmBtn.setOnClickListener {
            setResult(Activity.RESULT_OK)
            this.finish()
        }

    }

}
