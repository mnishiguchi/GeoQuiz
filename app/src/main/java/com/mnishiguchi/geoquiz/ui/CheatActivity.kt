package com.mnishiguchi.geoquiz.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mnishiguchi.geoquiz.R
import kotlinx.android.synthetic.main.activity_cheat.*
import org.jetbrains.anko.toast

class CheatActivity : AppCompatActivity() {

    companion object {
        val TAG = "CheatActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        showAnswerButton.setOnClickListener {
            toast("showAnswerButton clicked")
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
