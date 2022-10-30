package com.example.joblist.ui.main.fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.joblist.base.BaseFragment
import com.example.joblist.databinding.FragmentCandidateBinding
import com.example.joblist.ui.candidate.CandidateDetailsActivity
import com.example.joblist.ui.main.MainViewModel
import com.example.joblist.ui.main.adapters.CandidateAdapter
import com.example.joblist.utils.Constants
import com.example.joblist.utils.Resource
import kotlinx.coroutines.launch

class CandidateFragment : BaseFragment() {
    private lateinit var binding: FragmentCandidateBinding
    private val activityViewModel: MainViewModel by viewModels()
    private lateinit var candidateAdapter: CandidateAdapter
    private lateinit var pd: ProgressDialog

    override fun onSearch(query: String?) {
        super.onSearch(query)
        candidateAdapter.filter.filter(query)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCandidateBinding.inflate(inflater, container, false)
        setView()
        setObserver()
        setListener()
        return binding.root
    }

    private fun setView() {
        pd = ProgressDialog(requireContext()).apply {
            setMessage("Loading")
        }

        candidateAdapter = CandidateAdapter().apply {
            binding.rvCandidates.adapter = this
            binding.rvCandidates.setHasFixedSize(true)
            binding.rvCandidates.addItemDecoration(DividerItemDecoration(
                requireContext(),
                RecyclerView.VERTICAL,
            ))
        }
        activityViewModel.getAllCandidates()
    }

    private fun setObserver() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    activityViewModel.candidatesState.collect {
                        when (it) {
                            is Resource.Loading -> {
                                if (it.isLoading) pd.show() else pd.hide()
                            }
                            is Resource.Success -> {
                                it.message?.let { msg -> if (msg.isNotEmpty()) toast(msg) }
                                candidateAdapter.submitData(it.data?.toMutableList())
                            }
                            is Resource.Error -> {
                                it.message?.let { msg -> if (msg.isNotEmpty()) toast(msg) }
                            }
                        }
                    }
                }
                launch {
                    activityViewModel.candidateState.collect {
                        when (it) {
                            is Resource.Loading -> {
                                if (it.isLoading) pd.show() else pd.hide()
                            }
                            is Resource.Success -> {
                                it.message?.let { msg -> if (msg.isNotEmpty()) toast(msg) }
                                startActivity(Intent(requireContext(), CandidateDetailsActivity::class.java).apply {
                                    putExtra(Constants.EXTRAS_CANDIDATE, it.data)
                                })
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
        candidateAdapter.listener = object : CandidateAdapter.Listener {
            override fun onClick(position: Int) {
                activityViewModel.getCandidateById(candidateAdapter.currentList[position].id)
            }
        }
    }
}
