package com.example.joblist.entities

data class Data(
    val amount: Int,
    val category: String,
    val companyName: String,
    val createAt: String,
    val deadline: String,
    val description: String,
    val id: Int,
    val level: String,
    val location: String,
    val recruiter: User,
    val salary: Double,
    val status: String,
    val title: String,
    val type: String
)
