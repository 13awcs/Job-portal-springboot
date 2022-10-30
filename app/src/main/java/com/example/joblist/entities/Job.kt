package com.example.joblist.entities

data class Job(
    val id: Long = 0,
    val amount: Int = 0,
    val applyAmount: Int = 0,
    val category: String = "",
    val companyName: String = "",
    val createAt: String = "",
    val deadline: String = "",
    val description: String = "",
    val level: String,
    val location: String,
    val salary: Double,
    val status: String = "",
    val title: String,
    val type: String = "",
    val recruiterId: Long,
)
