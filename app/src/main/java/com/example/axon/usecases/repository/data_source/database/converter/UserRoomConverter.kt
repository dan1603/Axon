package com.example.axon.usecases.repository.data_source.database.converter

import androidx.room.TypeConverter
import com.example.axon.usecases.repository.data_source.database.entity.DateOfBirth
import com.example.axon.usecases.repository.data_source.database.entity.Location
import com.example.axon.usecases.repository.data_source.database.entity.Name
import com.example.axon.usecases.repository.data_source.database.entity.Picture
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserRoomConverter {

    @TypeConverter
    fun fromStringToName(item: Name?): String? =
        if (item == null) "" else Gson().toJson(item)

    @TypeConverter
    fun toStringFromName(json: String?): Name? =
        if (json == null) Name("", "", "")
        else Gson().fromJson(json, object : TypeToken<Name>() {}.type)

    @TypeConverter
    fun fromStringToLocation(item: Location?): String? =
        if (item == null) "" else Gson().toJson(item)

    @TypeConverter
    fun toStringFromLocation(json: String?): Location? =
        if (json == null) Location("", "", "")
        else Gson().fromJson(json, object : TypeToken<Location>() {}.type)

    @TypeConverter
    fun fromStringToDob(item: DateOfBirth?): String? =
        if (item == null) "" else Gson().toJson(item)

    @TypeConverter
    fun toStringFromDob(json: String?): DateOfBirth? =
        if (json == null) DateOfBirth("", "")
        else Gson().fromJson(json, object : TypeToken<DateOfBirth>() {}.type)

    @TypeConverter
    fun fromStringToPicture(item: Picture?): String? =
        if (item == null) "" else Gson().toJson(item)

    @TypeConverter
    fun toStringFromPicture(json: String?): Picture? =
        if (json == null) Picture("", "", "")
        else Gson().fromJson(json, object : TypeToken<Picture>() {}.type)
}