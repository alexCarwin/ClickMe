package com.example.clickme

import android.content.Intent.getIntent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_timer.*

class TimerActivity : AppCompatActivity(R.layout.activity_timer) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        editTimeText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkingEditTimeText()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        startButton.setOnClickListener {
            val timerText = editTimeText.text.toString()
            startActivity(ClickME.getIntent(this, timerText))
        }
    }


    private fun checkingEditTimeText() {
        startButton.isEnabled = editTimeText.text.isNotBlank()
    }

}