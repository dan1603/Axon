package com.example.axon.usecases.repository.data_source.database.entity

import com.google.gson.annotations.SerializedName

data class DateOfBirth(
    @SerializedName("date")
    var date: String,
    @SerializedName("age")
    var age: String
)