package com.mnishiguchi.geoquiz.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.mnishiguchi.geoquiz.R
import kotlinx.android.synthetic.main.activity_cheat.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult

class CheatActivity : AppCompatActivity() {

    companion object {
        private val TAG = "CheatActivity"
        private val EXTRA_ANSWER = "com.mnishiguchi.geoquiz.answer"
        private val EXTRA_WAS_ANSWER_SHOWN = "com.mnishiguchi.geoquiz.was_answer_shown"

        // Encapsulate the logic for a parent activity to start this activity.
        fun startForResult(activity: Activity, resultCode: Int, answer: Boolean) {
            activity.startActivityForResult<CheatActivity>(resultCode, EXTRA_ANSWER to answer)
        }

        // Encapsulate the logic for a parent activity to decode the result data.
        fun cheatResult(result: Intent) : Boolean {
            return result.getBooleanExtra(EXTRA_WAS_ANSWER_SHOWN, false)
        }
    }

    private fun setCheatResult(wasAnswerShown: Boolean) {
        // The result code is typically either Activity.RESULT_OK or Activity.RESULT_CANCELED.
        // Calling setResult is not requred of the child activity.
        // If setResult is not called, when the user presses the back button the parent will receive
        // Activity.RESULT_CANCELED.
        setResult(Activity.RESULT_OK, intentFor<CheatActivity>(EXTRA_WAS_ANSWER_SHOWN to wasAnswerShown))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        showAnswerButton.setOnClickListener {
            when (intent.getBooleanExtra(EXTRA_ANSWER, true)) {
                true  -> { answerText.setText(R.string.true_button) }
                false -> { answerText.setText(R.string.false_button) }
            }
            setCheatResult(true)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
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
