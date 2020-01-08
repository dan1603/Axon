package com.example.axon.usecases.repository.remote_data_source

import com.example.axon.usecases.repository.remote_data_source.communicator.ServerCommunicator
import com.example.axon.usecases.repository.remote_data_source.pojo.UserResponse
import io.reactivex.Single
import retrofit2.Response

interface UserRemoteDataSource {

    fun fetchUsers(): Single<Response<UserResponse>>

    fun fetchUsersNext(lastItemId: Int): Single<Response<UserResponse>>
}

class RepositoryRemoteDataSourceImpl(private val serverCommunicator: ServerCommunicator) : UserRemoteDataSource {

    override fun fetchUsers(): Single<Response<UserResponse>> =
        serverCommunicator.fetchRepositories(lastItemId=1)


    override fun fetchUsersNext(lastItemId: Int): Single<Response<UserResponse>> =
        serverCommunicator.fetchRepositories(lastItemId=lastItemId)
}