package com.hits.feature.login.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputFilter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.hits.feature.login.databinding.ActivityLoginBinding
import kotlinx.coroutines.flow.collectLatest

class LoginActivity : AppCompatActivity() {

    companion object {
        const val ACTION_OPEN_MAIN = "com.hits.app.OPEN_MAIN"
    }

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by lazy { LoginViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { state ->
                binding.loginButton.isEnabled = state.isLoginEnabled
            }
        }

        binding.loginButton.setOnClickListener {
            viewModel.onLoginClicked {
                openMain()
            }
        }

        binding.emailInputEditText.doAfterTextChanged {
            viewModel.onEmailChanged(it.toString())
        }

        binding.passwordInputEditText.doAfterTextChanged {
            viewModel.onPasswordChanged(it.toString())
        }

        val filter = InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (source[i].isLetter() && source[i].code in 0x0400..0x04FF) {
                    return@InputFilter ""
                }
            }
            null
        }

        binding.emailInputEditText.filters = arrayOf(filter)

        binding.btnVk.setOnClickListener {
            openUrl("https://vk.com/")
        }

        binding.btnOk.setOnClickListener {
            openUrl("https://ok.ru/")
        }
    }

    private fun openMain() {
        val intent = Intent("com.hits.app.OPEN_MAIN")
        startActivity(intent)
        finish()
    }

    private fun openUrl(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Нет приложения для открытия ссылки", Toast.LENGTH_SHORT).show()
        }
    }
}