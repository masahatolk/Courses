package com.hits.feature.login.utils

object Validation {
    private val EMAIL_REGEX = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex()

    fun isEmailValid(email: String): Boolean {
        return EMAIL_REGEX.matches(email)
    }
}