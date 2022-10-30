package com.example.joblist.api.services

import com.example.joblist.entities.*
import retrofit2.Call
import retrofit2.http.*

interface  AppService {
    @POST("/login")
    fun login(@Body login: Login): Call<UserResponse>

    @POST("/register")
    fun register(@Body register: Register): Call<UserResponse>

    @GET("/profile")
    fun getProfile(@Query("username") username: String): Call<User>

    @GET("/candidates")
    fun getAllCandidate(): Call<List<Candidate>>

//    @GET("/candidates/search")
//    fun searchCandidate(@Query("keyword") keyword: String?): Call<List<Candidate?>?>?

    @GET("/jobs")
    fun getAllJobs(): Call<List<Job>>

    @POST("/jobs/create")
    fun createJobs(@Body job: Job): Call<CreatedJobResponse>

    @PUT("/jobs/edit/{id}")
    fun editJobs(@Path("id") id: Long, @Body newJob: Job): Call<EditedJobResponse>

    @DELETE("/jobs/delete/{id}")
    fun deleteJobs(@Path("id") id: Long): Call<EditedJobResponse>
}
