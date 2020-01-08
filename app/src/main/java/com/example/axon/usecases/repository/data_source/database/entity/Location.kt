package com.example.axon.usecases.repository.data_source.database.entity

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("city")
    var city: String,
    @SerializedName("state")
    var state: String,
    @SerializedName("country")
    var country: String
)