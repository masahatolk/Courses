package com.hits.feature.login.ui

import androidx.lifecycle.ViewModel
import com.hits.feature.login.utils.Validation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun onEmailChanged(email: String) {
        val current = _state.value
        _state.value = current.copy(
            email = email,
            isLoginEnabled = isFormValid(email, current.password)
        )
    }

    fun onPasswordChanged(password: String) {
        val current = _state.value
        _state.value = current.copy(
            password = password,
            isLoginEnabled = isFormValid(current.email, password)
        )
    }

    private fun isFormValid(email: String, password: String): Boolean {
        return email.isNotBlank() && password.isNotBlank() && Validation.isEmailValid(email)
    }

    fun onLoginClicked(onSuccess: () -> Unit) {
        if (_state.value.isLoginEnabled) {
            // Тут вызов API
            onSuccess()
        }
    }
}