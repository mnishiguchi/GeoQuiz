package com.mnishiguchi.geoquiz.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mnishiguchi.geoquiz.R
import com.mnishiguchi.geoquiz.domain.Question
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

// AppCompatActivity is a subclass of Android's Activity class that provides compatibility support
// for older versions of Android.
class MainActivity : AppCompatActivity() {

    companion object {
        private val questionBank = listOf<Question>(
                Question(R.string.quiz_01, true),
                Question(R.string.quiz_02, false),
                Question(R.string.quiz_03, false),
                Question(R.string.quiz_04, true),
                Question(R.string.quiz_05, true))
    }

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate a layout and puts it on screen.
        setContentView(R.layout.activity_main)

        questionText.setText(questionBank[currentIndex].textResId)

        trueButton.setOnClickListener {
            toast(R.string.incorrect_message)
        }

        falseButton.setOnClickListener {
            toast(R.string.correct_message)
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
    }

    private fun updateQuestion() {
        val question = questionBank[currentIndex].textResId
        questionText.setText(question)
    }
}
