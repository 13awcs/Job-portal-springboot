package com.example.joblist.ui.main.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.joblist.R
import com.example.joblist.base.BaseFragment
import com.example.joblist.databinding.FragmentProfileBinding
import com.example.joblist.entities.User
import com.example.joblist.ui.main.MainViewModel
import com.example.joblist.utils.Constants
import com.example.joblist.utils.Resource
import com.example.joblist.utils.SharedPreferencesUtils
import kotlinx.coroutines.launch

class ProfileFragment : BaseFragment() {
    private lateinit var binding: FragmentProfileBinding
    private val activityViewModel: MainViewModel by viewModels()
    lateinit var pd: ProgressDialog

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.itSearch)?.isVisible = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        setView()
        setObserver()
        setListener()
        return binding.root
    }

    private fun setView() {
        pd = ProgressDialog(requireContext()).apply {
            setMessage("Loading")
        }

        activityViewModel.getProfile(
            SharedPreferencesUtils.loadString(requireContext(), Constants.KEY_USERNAME)
        )
    }

    private fun setObserver() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    activityViewModel.profileState.collect {
                        when (it) {
                            is Resource.Loading -> {
                                if (it.isLoading) pd.show() else pd.hide()
                            }
                            is Resource.Success -> {
                                it.message?.let { msg -> if (msg.isNotEmpty()) toast(msg) }
                                Glide
                                    .with(requireContext())
                                    .load(it.data!!.avatar)
                                    .placeholder(R.drawable.ic_launcher_foreground)
                                    .centerCrop()
                                    .into(binding.ivAvatar)
                                binding.etCompanyName.setText(it.data.companyName)
                                binding.etName.setText(it.data.name)
                                binding.etEmail.setText(it.data.email)
                                binding.etPhone.setText(it.data.phone)
                            }
                            is Resource.Error -> {
                                it.message?.let { msg -> if (msg.isNotEmpty()) toast(msg) }
                            }
                        }
                    }
                }
                launch {
                    activityViewModel.editProfileState.collect {
                        when (it) {
                            is Resource.Loading -> {
                                if (it.isLoading) pd.show() else pd.hide()
                            }
                            is Resource.Success -> {
                                it.message?.let { msg -> if (msg.isNotEmpty()) toast(msg) }
                                Glide
                                    .with(requireContext())
                                    .load(it.data!!.avatar)
                                    .placeholder(R.drawable.ic_launcher_foreground)
                                    .centerCrop()
                                    .into(binding.ivAvatar)
                                binding.etCompanyName.setText(it.data.companyName)
                                binding.etName.setText(it.data.name)
                                binding.etEmail.setText(it.data.email)
                                binding.etPhone.setText(it.data.phone)
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
        binding.fabEdit.setOnClickListener {
            if (!binding.etCompanyName.isEnabled) {
                binding.etCompanyName.isEnabled = true
                binding.etName.isEnabled = true
                binding.etEmail.isEnabled = true
                binding.etPhone.isEnabled = true

                binding.fabYes.visibility = View.VISIBLE
                binding.fabNo.visibility = View.VISIBLE
                binding.fabEdit.visibility = View.GONE
            }
        }

        binding.fabYes.setOnClickListener {
            binding.etCompanyName.isEnabled = false
            binding.etName.isEnabled = false
            binding.etEmail.isEnabled = false
            binding.etPhone.isEnabled = false

            binding.fabYes.visibility = View.GONE
            binding.fabNo.visibility = View.GONE
            binding.fabEdit.visibility = View.VISIBLE

            // todo update profile
            activityViewModel.editProfile(
                SharedPreferencesUtils.loadString(requireContext(), Constants.KEY_UID).toLong(),
                User(
                    id = SharedPreferencesUtils.loadString(requireContext(), Constants.KEY_UID)
                        .toLong(),
                    companyName = binding.etCompanyName.text.toString(),
                    name = binding.etName.text.toString(),
                    email = binding.etEmail.text.toString(),
                    phone = binding.etPhone.text.toString(),
                )
            )
        }

        binding.fabNo.setOnClickListener {
            binding.etCompanyName.isEnabled = false
            binding.etName.isEnabled = false
            binding.etEmail.isEnabled = false
            binding.etPhone.isEnabled = false

            binding.fabYes.visibility = View.GONE
            binding.fabNo.visibility = View.GONE
            binding.fabEdit.visibility = View.VISIBLE
        }
    }
}
