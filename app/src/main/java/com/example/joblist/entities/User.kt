package com.example.joblist.entities

data class User(
    val id: Long,
    val name: String,
    val dob: String = "",
    val address: String = "",
    val phone: String,
    val email: String,
    val avatar: String = "",
    val companyName: String,
    val username: String = "",
    val password: String = "",
)
