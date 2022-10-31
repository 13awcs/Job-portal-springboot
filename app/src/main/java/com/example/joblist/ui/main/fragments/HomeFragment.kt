package com.example.joblist.ui.main.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.joblist.base.BaseFragment
import com.example.joblist.databinding.FragmentHomeBinding
import com.example.joblist.entities.apply.Apply
import com.example.joblist.ui.main.MainViewModel
import com.example.joblist.ui.main.adapters.ApplyAdapter
import com.example.joblist.utils.Constants
import com.example.joblist.utils.Resource
import com.example.joblist.utils.SharedPreferencesUtils
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    private val activityViewModel: MainViewModel by viewModels()
    lateinit var acceptedAdapter: ApplyAdapter
    lateinit var rejectedAdapter: ApplyAdapter
    lateinit var pd: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setView()
        setObserver()
        return binding.root
    }

    private fun setView() {
        pd = ProgressDialog(requireContext()).apply {
            setMessage("Loading")
        }

        acceptedAdapter = ApplyAdapter().apply {
            binding.rvAcceptedApplies.adapter = this
            binding.rvAcceptedApplies.setHasFixedSize(true)
        }

        rejectedAdapter = ApplyAdapter().apply {
            binding.rvRejectedApplies.adapter = this
            binding.rvRejectedApplies.setHasFixedSize(true)
        }

        binding.tvUsername.text = "Welcome, ${
            SharedPreferencesUtils.loadString(requireContext(), Constants.KEY_USERNAME)
        }"

        activityViewModel.getAllApplies()
    }

    private fun setObserver() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    activityViewModel.applyState.collect {
                        when (it) {
                            is Resource.Loading -> {
                                if (it.isLoading) pd.show() else pd.hide()
                            }
                            is Resource.Success -> {
                                it.message?.let { msg -> if (msg.isNotEmpty()) toast(msg) }
                                Log.e("TAG", "setObserver: ${it.data}")

                                with(it.data!!.data) {
                                    val acceptedList = mutableListOf<Apply>()
                                    val rejectedList = mutableListOf<Apply>()
                                    forEach { apply ->
                                        if (apply.status == "accepted") acceptedList.add(apply)
                                        else if (apply.status == "rejected") rejectedList.add(apply)
                                    }

                                    acceptedAdapter.submitData(acceptedList)
                                    rejectedAdapter.submitData(rejectedList)
                                }
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
}
