package com.example.axon.usecases.repository.data_source.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.example.axon.data.BaseModel
import com.example.axon.utils.*

@Entity(tableName="users")
data class UserEntity(

    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name = "uid")
    var uid: Int,

    @SerializedName("name")
    var name: Name,

    @SerializedName("location")
    var location: Location,

    @SerializedName("dob")
    var dateOfBirth: DateOfBirth,

    @SerializedName("email")
    var email: String,

    @SerializedName("phone")
    var phone: String,

    @SerializedName("cell")
    var cell: String,

    @SerializedName("picture")
    var picture: Picture,

    var screenType: String?=DEFAULT_SCREEN,

    var cached: Int?=DEFAULT_CACHE_VALUE
) : BaseModel() {

    override fun convertItemForDataSource(): UserEntity {
        return UserEntity(
            uid,
            name,
            location,
            dateOfBirth,
            email,
            phone,
            cell,
            picture,
            screenType,
            cached
        )
    }

    constructor() : this(
        0,
        Name("", "", ""),
        Location("", "", ""),
        DateOfBirth("", ""),
        "",
        "",
        "",
        Picture("", "", ""),
        DEFAULT_SCREEN,
        DEFAULT_CACHE_VALUE
    )

    val isUser: Boolean
        get()=true

    fun convertItemForDataSource(item: UserEntity, isCached: Boolean?): UserEntity {
        isCached?.let { item.cached=CACHED_VALUE }
        return item
    }
}