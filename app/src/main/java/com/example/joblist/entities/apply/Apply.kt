package com.example.joblist.entities.apply

import com.example.joblist.entities.Candidate
import com.example.joblist.entities.Job

data class Apply(
    val applyDate: String,
    val candidateApply: Candidate,
    val id: ApplyId,
    val jobApply: Job,
    val status: String
)
