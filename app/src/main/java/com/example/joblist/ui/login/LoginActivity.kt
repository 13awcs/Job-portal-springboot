package com.example.joblist.ui.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.joblist.base.BaseActivity
import com.example.joblist.databinding.ActivityLoginBinding
import com.example.joblist.ui.main.MainActivity
import com.example.joblist.ui.register.RegisterActivity
import com.example.joblist.utils.Constants
import com.example.joblist.utils.Resource
import com.example.joblist.utils.SharedPreferencesUtils
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var pd: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
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
                    viewModel.loginState.collect {
                        when (it) {
                            is Resource.Loading -> {
                                if (it.isLoading) pd.show() else pd.hide()
                            }
                            is Resource.Success -> {
                                it.message?.let { msg -> if (msg.isNotEmpty()) toast(msg) }
                                SharedPreferencesUtils.saveString(
                                    this@LoginActivity,
                                    mapOf(
                                        Constants.KEY_UID to it.data!!.id.toString(),
                                        Constants.KEY_USERNAME to it.data!!.username,
                                    ),
                                )
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
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
        binding.tvGoToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btLogin.setOnClickListener {
            viewModel.login(binding.etUsername.text.toString(), binding.etPassword.text.toString())
        }

        pd.setOnCancelListener {
            viewModel.cancel()
        }
    }
}
