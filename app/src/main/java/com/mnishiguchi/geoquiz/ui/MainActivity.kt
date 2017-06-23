package com.mnishiguchi.geoquiz.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mnishiguchi.geoquiz.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

// AppCompatActivity is a subclass of Android's Activity class that provides compatibility support
// for older versions of Android.
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate a layout and puts it on screen.
        setContentView(R.layout.activity_main)

        trueButton.setOnClickListener {
            toast(R.string.incorrect_message)
        }

        falseButton.setOnClickListener {
            toast(R.string.correct_message)
        }
    }
}
