package com.example.joblist.entities

import java.io.Serializable

data class Candidate(
    val address: String,
    val avatar: String,
    val category: String,
    val dob: String,
    val email: String,
    val gender: String,
    val id: Long,
    val level: String,
    val linkCv: String,
    val major: String,
    val name: String,
    val phone: String
) : Serializable
