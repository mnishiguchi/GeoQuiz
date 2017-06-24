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
        // TODO("How best can I store questions and answers?")
        private val questionBank = listOf (
                Question(R.string.quiz_01, true),
                Question(R.string.quiz_02, false),
                Question(R.string.quiz_03, false),
                Question(R.string.quiz_04, true),
                Question(R.string.quiz_05, true))
    }

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Inflate a layout and puts it on screen.

        questionText.setText(questionBank[currentIndex].textResId)
        trueButton.setOnClickListener { checkAnswer(true) }
        falseButton.setOnClickListener { checkAnswer(false) }
        prevButton.setOnClickListener {
            setPrevIndex()
            updateQuestion()
        }
        nextButton.setOnClickListener {
            setNextIndex()
            updateQuestion()
        }
    }

    // Decrement the current index. Loop infinitely.
    private fun setPrevIndex() {
        currentIndex = if (currentIndex == 0) questionBank.size - 1 else currentIndex - 1
    }

    // Increment the current index. Loop infinitely.
    private fun setNextIndex() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    // Set a question based on the current index.
    private fun updateQuestion() {
        val question = questionBank[currentIndex].textResId
        questionText.setText(question)
    }

    private fun checkAnswer(isUserInputTrue: Boolean) {
        if (isUserInputTrue == questionBank[currentIndex].isTrue) {
            toast(R.string.correct_message)
        } else {
            toast(R.string.incorrect_message)
        }
    }
}
