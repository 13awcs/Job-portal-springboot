package com.example.joblist.ui.main.fragments

import android.app.AlertDialog
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
import com.example.joblist.R
import com.example.joblist.base.BaseFragment
import com.example.joblist.databinding.FragmentJobBinding
import com.example.joblist.databinding.LayoutCreateJobDialogBinding
import com.example.joblist.entities.Job
import com.example.joblist.ui.main.MainViewModel
import com.example.joblist.ui.main.adapters.JobAdapter
import com.example.joblist.utils.Constants
import com.example.joblist.utils.Resource
import com.example.joblist.utils.SharedPreferencesUtils
import kotlinx.coroutines.launch

class JobFragment : BaseFragment() {
    private lateinit var binding: FragmentJobBinding
    private lateinit var bindingDialog: LayoutCreateJobDialogBinding
    private val activityViewModel: MainViewModel by viewModels()
    private lateinit var jobAdapter: JobAdapter
    private lateinit var pd: ProgressDialog
    private lateinit var dialog: AlertDialog

    override fun onSearch(query: String?) {
        super.onSearch(query)
        jobAdapter.filter.filter(query)
    }

    override fun onCreateJob() {
        super.onCreateJob()
        dialog.show()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.itCreateJob)?.isVisible = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobBinding.inflate(inflater, container, false)
        bindingDialog = LayoutCreateJobDialogBinding.inflate(layoutInflater)
        dialog = AlertDialog.Builder(requireContext())
            .setView(bindingDialog.root)
            .create()

        setView()
        setObserver()
        setListener()
        return binding.root
    }

    private fun setView() {
        pd = ProgressDialog(requireContext()).apply {
            setMessage("Loading")
        }

        jobAdapter = JobAdapter().apply {
            binding.rvJobs.adapter = this
            binding.rvJobs.setHasFixedSize(true)
        }
        activityViewModel.getAllJobs()
    }

    private fun setObserver() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    activityViewModel.jobsState.collect {
                        when (it) {
                            is Resource.Loading -> {
                                if (it.isLoading) pd.show() else pd.hide()
                            }
                            is Resource.Success -> {
                                it.message?.let { msg -> if (msg.isNotEmpty()) toast(msg) }
                                jobAdapter.submitData(it.data?.toMutableList())
                            }
                            is Resource.Error -> {
                                it.message?.let { msg -> if (msg.isNotEmpty()) toast(msg) }
                            }
                        }
                    }
                }
                launch {
                    activityViewModel.createJobState.collect {
                        when (it) {
                            is Resource.Loading -> {
                                if (it.isLoading) pd.show() else pd.hide()
                            }
                            is Resource.Success -> {
                                it.message?.let { msg -> if (msg.isNotEmpty()) toast(msg) }
                            }
                            is Resource.Error -> {
                                it.message?.let { msg -> if (msg.isNotEmpty()) toast(msg) }
                            }
                        }
                    }
                }
                launch {
                    activityViewModel.editJobState.collect {
                        when (it) {
                            is Resource.Loading -> {
                                if (it.isLoading) pd.show() else pd.hide()
                            }
                            is Resource.Success -> {
                                it.message?.let { msg -> if (msg.isNotEmpty()) toast(msg) }
                            }
                            is Resource.Error -> {
                                it.message?.let { msg -> if (msg.isNotEmpty()) toast(msg) }
                            }
                        }
                    }
                }
                launch {
                    activityViewModel.deleteJobState.collect {
                        when (it) {
                            is Resource.Loading -> {
                                if (it.isLoading) pd.show() else pd.hide()
                            }
                            is Resource.Success -> {
                                it.message?.let { msg -> if (msg.isNotEmpty()) toast(msg) }
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
        bindingDialog.btCancel.setOnClickListener {
            dialog.dismiss()
        }

        bindingDialog.btOK.setOnClickListener {
            if (bindingDialog.tvId.text.isNotEmpty()) {
                modifyJob(false)
            } else {
                modifyJob(true)
            }
            dialog.dismiss()
            activityViewModel.getAllJobs()
        }

        jobAdapter.listener = object : JobAdapter.Listener {
            override fun onClick(position: Int) {
            }

            override fun onRightSwipe(position: Int) {
                val existingJob = jobAdapter.currentList[position]
                bindingDialog.tvId.text = existingJob.id.toString()
                bindingDialog.etTitle.setText(existingJob.title)
                bindingDialog.etLevel.setText(existingJob.level)
                bindingDialog.etAddress.setText(existingJob.location)
                bindingDialog.etSalary.setText(existingJob.salary.toString())
                dialog.show()
            }

            override fun onLeftSwipe(position: Int) {
                activityViewModel.deleteJob(jobAdapter.currentList[position].id)
            }
        }
    }

    private fun modifyJob(isNew: Boolean) {
        if (isNew) {
            //todo add new Job
            activityViewModel.createJob(
                Job(
                    title = bindingDialog.etTitle.text.toString(),
                    level = bindingDialog.etLevel.text.toString(),
                    location = bindingDialog.etAddress.text.toString(),
                    salary = bindingDialog.etSalary.text.toString().toDouble(),
                    recruiterId = SharedPreferencesUtils.loadString(
                        requireContext(),
                        Constants.KEY_UID
                    ).toLong()
                ),
            )
        } else {
            // todo edit exiting Job
            activityViewModel.editJob(
                bindingDialog.tvId.text.toString().toLong(),
                Job(
                    title = bindingDialog.etTitle.text.toString(),
                    level = bindingDialog.etLevel.text.toString(),
                    location = bindingDialog.etAddress.text.toString(),
                    salary = bindingDialog.etSalary.text.toString().toDouble(),
                    recruiterId = SharedPreferencesUtils.loadString(
                        requireContext(),
                        Constants.KEY_UID
                    ).toLong()
                ),
            )
        }
    }
}
