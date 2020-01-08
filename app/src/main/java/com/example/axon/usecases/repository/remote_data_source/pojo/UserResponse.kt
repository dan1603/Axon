package com.example.axon.usecases.repository.remote_data_source.pojo

import com.example.axon.usecases.repository.data_source.database.entity.UserEntity
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("results")
    var results: List<UserEntity>
)