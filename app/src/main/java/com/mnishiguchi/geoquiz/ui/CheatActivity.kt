package com.mnishiguchi.geoquiz.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.mnishiguchi.geoquiz.R
import kotlinx.android.synthetic.main.activity_cheat.*
import org.jetbrains.anko.startActivity

class CheatActivity : AppCompatActivity() {

    companion object {
        val TAG = "CheatActivity"
        val EXTRA_ANSWER = "com.mnishiguchi.geoquiz.answer"

        fun start(context: Context, answer: Boolean) {
            context.startActivity<CheatActivity>(EXTRA_ANSWER to answer)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        showAnswerButton.setOnClickListener {
            when (intent.getBooleanExtra(EXTRA_ANSWER, true)) {
                true  -> { answerText.setText(R.string.true_button) }
                false -> { answerText.setText(R.string.false_button) }
            }
        }
    }

    /* Lifecycle methods */

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
