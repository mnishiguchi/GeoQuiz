package com.mnishiguchi.geoquiz.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.mnishiguchi.geoquiz.R
import com.mnishiguchi.geoquiz.domain.QuestionBank
import com.mnishiguchi.geoquiz.domain.QuestionBankJsonAdapter
import com.mnishiguchi.geoquiz.ext.readAssetFile
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

// AppCompatActivity is a subclass of Android's Activity class that provides compatibility support
// for older versions of Android.
class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = "MainActivity"
    }

    val questionBank by lazy { loadQuestions() }
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Inflate a layout and puts it on screen.

        questionText.text = questionBank[currentIndex].question
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
        questionText.text = questionBank[currentIndex].question
    }

    private fun checkAnswer(userInput: Boolean) {
        if (userInput == questionBank[currentIndex].answer) {
            toast(R.string.correct_message)
        } else {
            toast(R.string.incorrect_message)
        }
    }

    private fun loadQuestions() : QuestionBank {
        // Read json from app/src/main/assets folder.
        val json = readAssetFile("questions.json")
        Log.d(TAG, json)
        return QuestionBankJsonAdapter().fromJson(json)
    }
}