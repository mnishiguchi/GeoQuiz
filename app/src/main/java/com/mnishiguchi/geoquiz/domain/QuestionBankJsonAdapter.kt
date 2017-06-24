package com.mnishiguchi.geoquiz.domain

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi

class QuestionBankJsonAdapter {
    fun fromJson(json: String) : QuestionBank {
        // Get a moshi instance.
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        // Convert json to DTO.
        val dto = moshi.adapter(QuestionBank::class.java).fromJson(json)

        // Without questions, this app will not work.
        return dto ?: throw RuntimeException("Could not load json quesions")
    }
}