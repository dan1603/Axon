package com.example.axon.usecases.repository.data_source.database.entity

import com.google.gson.annotations.SerializedName

data class Picture(
    @SerializedName("large")
    var large: String,
    @SerializedName("medium")
    var medium: String,
    @SerializedName("thumbnail")
    var thumbnail: String
)