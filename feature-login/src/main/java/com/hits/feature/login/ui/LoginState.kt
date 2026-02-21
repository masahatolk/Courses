package com.hits.feature.login.ui

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoginEnabled: Boolean = false
)
