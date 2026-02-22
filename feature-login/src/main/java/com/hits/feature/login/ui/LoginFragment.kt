package com.hits.feature.login.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hits.feature.login.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emailInputEditText.doAfterTextChanged {
            viewModel.onEmailChanged(it.toString())
        }

        binding.passwordInputEditText.doAfterTextChanged {
            viewModel.onPasswordChanged(it.toString())
        }

        val filter = InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (source[i].isLetter() && source[i].toInt() in 0x0400..0x04FF) {
                    return@InputFilter ""
                }
            }
            null
        }

        binding.emailInputEditText.filters = arrayOf(filter)

        binding.loginButton.setOnClickListener {

        }

        binding.btnVk.setOnClickListener {
            openUrl("https://vk.com/")
        }

        binding.btnOk.setOnClickListener {
            openUrl("https://ok.ru/")
        }
    }

    private fun openUrl(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(requireContext(), "Нет приложения для открытия ссылки", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
