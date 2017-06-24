package com.mnishiguchi.geoquiz.domain

data class QuestionBank(val questions: List<Question>) {
    val size: Int
        get() = questions.size

    // a[i]	=> a.get(i)
    // https://kotlinlang.org/docs/reference/operator-overloading.html#indexed
    operator fun get(position: Int) = questions[position]
}

data class Question(val question: String, val answer: Boolean)