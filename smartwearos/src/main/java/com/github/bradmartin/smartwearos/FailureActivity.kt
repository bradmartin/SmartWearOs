package com.github.bradmartin.smartwearos

import android.os.Bundle
import android.os.Handler
import android.support.wearable.activity.WearableActivity
import android.widget.TextView

class FailureActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_failure)

        // Enables Always-on
        setAmbientEnabled()

        val text: String = this.intent.getStringExtra("MESSAGE")
        val dismissTime = this.intent.getIntExtra("DISMISS_TIMEOUT", 3)
        val textView: TextView = findViewById(R.id.message)

        // set the default success message if nothing is passed with the intent
        if (text.isEmpty()) {
            textView.text = getString(R.string.cowabunga_failure_message)
        } else {
            textView.text = text
        }

        val convertedDismissTime: Long = (dismissTime * 1000).toLong()

        // run the handler after the dismissal time
        Handler().postDelayed(
            {
                this.finish()
            }, convertedDismissTime
        )

    }
}
