package com.example.axon.usecases.repository.data_source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.axon.data.card_models.UserDisplayModel
import com.example.axon.usecases.repository.data_source.database.AppDatabase
import com.example.axon.usecases.repository.data_source.database.entity.UserEntity
import com.example.axon.utils.ConverterFactory

interface UserDataSource {

    fun getUsersFactory(converterFactory: ConverterFactory)
            : DataSource.Factory<Int, UserDisplayModel>

    fun getUserById(id: Int): LiveData<UserEntity>

    fun deleteCachedRepositories()

    fun insert(items: List<UserEntity>)

    fun insertAndClearCache(items: List<UserEntity>)
}

class UserDataSourceImpl(private val database: AppDatabase) : UserDataSource {

    override fun getUsersFactory(
        converterFactory: ConverterFactory
    ): DataSource.Factory<Int, UserDisplayModel> {
        return database.usersDao().queryItems()
            .mapByPage(converterFactory::convert)
    }

    override fun deleteCachedRepositories(): Unit=
        database.usersDao().deleteCachedItems()

    override fun insert(items: List<UserEntity>) : Unit=
        database.usersDao().insert(items)

    override fun getUserById(id: Int): LiveData<UserEntity> =
        database.usersDao().queryById(id)

    override fun insertAndClearCache(items: List<UserEntity>): Unit=
        database.usersDao().insertAndClearCache(items)
}