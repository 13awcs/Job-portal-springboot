package com.example.joblist.api.services

import com.example.joblist.entities.*
import com.example.joblist.entities.apply.ApplyResponse
import retrofit2.Call
import retrofit2.http.*

interface  AppService {
    @GET("/applies/newest")
    fun getAllApplies(): Call<ApplyResponse>

    @POST("/login")
    fun login(@Body login: Login): Call<UserResponse>

    @POST("/register")
    fun register(@Body register: Register): Call<UserResponse>

    @GET("/profile")
    fun getProfile(@Query("username") username: String): Call<User>

    @POST("/profile/edit")
    fun editProfile(@Query("id") id: Long, @Body newUser: User): Call<User>

    @GET("/candidates")
    fun getAllCandidate(): Call<List<Candidate>>

    @GET("/candidate/1/")
    fun getCandidateById(@Query("id") id: Long): Call<Candidate>

//    @GET("/candidates/search")
//    fun searchCandidate(@Query("keyword") keyword: String?): Call<List<Candidate?>?>?

    @GET("/jobs/recruiter/{id}")
    fun getAllJobs(@Path("id") recruiterId: Long): Call<List<Job>>

    @GET("/jobs/{id}")
    fun getJobById(@Path("id") id: Long): Call<Job?>

    @POST("/jobs/create")
    fun createJobs(@Body job: Job): Call<CreatedJobResponse>

    @PUT("/jobs/edit/{id}")
    fun editJobs(@Path("id") id: Long, @Body newJob: Job): Call<EditedJobResponse>

    @DELETE("/jobs/delete/{id}")
    fun deleteJobs(@Path("id") id: Long): Call<EditedJobResponse>
}
