package com.example.axon.usecases.repository.data_source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.axon.usecases.repository.data_source.database.converter.UserRoomConverter
import com.example.axon.usecases.repository.data_source.database.dao.UserDao
import com.example.axon.usecases.repository.data_source.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
@TypeConverters(UserRoomConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repositoryDao(): UserDao
}