package com.example.axon.usecases.repository.remote_data_source.communicator

import com.example.axon.usecases.repository.remote_data_source.pojo.UserResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api")
    fun fetchUsers(
        @Query("page") lastPage: Int,
        @Query("results") perPage: Int
    ): Single<Response<UserResponse>>
}
