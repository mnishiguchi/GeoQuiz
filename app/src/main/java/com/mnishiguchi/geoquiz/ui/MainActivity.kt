package com.mnishiguchi.geoquiz.ui

import android.app.Activity
import android.content.Intent
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
        private val KEY_INDEX = "index"
        private val REQUEST_CODE_CHEAT = 0
    }

    private val questionBank by lazy { loadQuestions() }
    private var currentIndex = 0
    private var didCheat = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Restore the state.
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX)
        }

        updateQuestion()

        trueButton.setOnClickListener { checkAnswer(true) }
        falseButton.setOnClickListener { checkAnswer(false) }

        cheatButton.setOnClickListener {
            // Start CheatActivity and get the result from it.
            CheatActivity.startForResult(this,
                    resultCode = REQUEST_CODE_CHEAT,
                    answer = questionBank[currentIndex].answer)
        }

        prevButton.setOnClickListener {
            setPrevIndex()
            updateQuestion()
            didCheat = false
        }
        nextButton.setOnClickListener {
            setNextIndex()
            updateQuestion()
            didCheat = false
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

    // Show message according to the passed in user input.
    private fun checkAnswer(userInput: Boolean) {
        if (didCheat) {
            toast(R.string.judgement_toast)
        } else {
            when (userInput == questionBank[currentIndex].answer) {
                true  -> { toast(R.string.correct_message) }
                false -> { toast(R.string.incorrect_message) }
            }
        }
    }

    private fun loadQuestions() : QuestionBank {
        // Read json from app/src/main/assets folder.
        val json = readAssetFile("questions.json")
        Log.d(TAG, json)
        return QuestionBankJsonAdapter().fromJson(json)
    }

    // https://developer.android.com/guide/components/activities/activity-lifecycle.html#saras.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putInt(KEY_INDEX, currentIndex)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // The result code is typically either Activity.RESULT_OK or Activity.RESULT_CANCELED.
        if (resultCode != Activity.RESULT_OK) return

        when (requestCode) {
            REQUEST_CODE_CHEAT -> {
                data?.let { didCheat = CheatActivity.cheatResult(data) }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }
}