package com.example.joblist.ui.register

import android.app.ProgressDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.joblist.base.BaseActivity
import com.example.joblist.databinding.ActivityRegisterBinding
import com.example.joblist.utils.Resource
import kotlinx.coroutines.launch

class RegisterActivity : BaseActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var pd: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setView()
        setObserver()
        setListener()
    }

    private fun setView() {
        pd = ProgressDialog(this).apply { setMessage("Loading") }
    }

    private fun setObserver() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.registerState.collect {
                        when (it) {
                            is Resource.Loading -> {
                                if (it.isLoading) pd.show() else pd.hide()
                            }
                            is Resource.Success -> {
                                it.message?.let { msg -> if (msg.isNotEmpty()) toast(msg) }
                                finish()
                            }
                            is Resource.Error -> {
                                it.message?.let { msg -> if (msg.isNotEmpty()) toast(msg) }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setListener() {
        binding.tvGoToLogin.setOnClickListener {
            finish()
        }

        binding.btRegister.setOnClickListener {
            viewModel.register(
                binding.etEmail.text.toString(),
                binding.etUsername.text.toString(),
                binding.etPassword.text.toString(),
            )
        }

        pd.setOnCancelListener {
            viewModel.cancel()
        }
    }
}
