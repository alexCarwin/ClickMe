package com.example.clickme

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*


class ClickME : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerTextView.text = intent.getStringExtra(KEY_MESSAGE)

        clickButton.setOnClickListener {
            processingClickButton()
        }
    }

    private var isTimerStarted = false
    private var count: Long = 0

    private fun startTimerFun(time: Long) {
        val timer =
            object : CountDownTimer(time, 250) {
                override fun onTick(millisUntilFinished: Long) {
                    timerTextView.text = "${millisUntilFinished / 1000}"
                    clickSpeedTextView.text = "${
                        String.format(
                            "%.3f",
                            count * 1000 / (time - millisUntilFinished).toDouble()
                        )
                    } clicks per sec"
                }

                override fun onFinish() {
                    timerTextView.text = "${time / 1000} sec done!"
                    clickButton.isEnabled = false
                }
            }
        timer.start()
    }

    private fun processingClickButton() {
        if (!isTimerStarted) {
            isTimerStarted = true
            startTimerFun(timerTextView.text.toString().toLong() * 1000)
        } else {
            countClicks()
        }
    }

    private fun countClicks() {
        count++
        clicksCounterTextView.text = "$count Clicks"
    }

    companion object {
        private const val KEY_MESSAGE = "message key"

        fun getIntent(context: Context, message: String?): Intent {
            return Intent(context, ClickME::class.java)
                .putExtra(KEY_MESSAGE, message)
        }
    }
}