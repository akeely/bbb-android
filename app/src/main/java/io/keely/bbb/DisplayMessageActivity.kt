package io.keely.bbb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.content.Intent



class DisplayMessageActivity : AppCompatActivity() {

    val EXTRA_MESSAGE = "io.keely.bbb.MESSAGE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        val intent = intent
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        val textView = TextView(this)
        textView.textSize = 40f
        textView.text = message

        val layout = findViewById(R.id.activity_display_message) as ViewGroup
        layout.addView(textView)
    }
}
