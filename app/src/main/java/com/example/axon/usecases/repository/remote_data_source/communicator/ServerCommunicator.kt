package com.example.axon.usecases.repository.remote_data_source.communicator

import com.example.axon.usecases.repository.remote_data_source.pojo.UserResponse
import io.reactivex.Single
import retrofit2.Response

const val ITEMS_PER_PAGE = 20

class ServerCommunicator(private val apiService: ApiService) {

    fun fetchRepositories(lastItemId: Int): Single<Response<UserResponse>> {
        return apiService.fetchUsers(lastItemId, ITEMS_PER_PAGE)
            .doOnError { t: Throwable -> t.printStackTrace() }
    }
}
