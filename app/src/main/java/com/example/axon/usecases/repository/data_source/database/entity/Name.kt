package com.example.axon.usecases.repository.data_source.database.entity

import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("title")
    var title: String,
    @SerializedName("first")
    var firstName: String,
    @SerializedName("last")
    var lastName: String
) {
    fun getFullName(): String = "$title $firstName $lastName"
}