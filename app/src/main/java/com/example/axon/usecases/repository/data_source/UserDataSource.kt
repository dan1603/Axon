package com.example.axon.usecases.repository.data_source

import androidx.paging.DataSource
import com.example.axon.data.card_models.UserDisplayModel
import com.example.axon.usecases.repository.data_source.database.AppDatabase
import com.example.axon.usecases.repository.data_source.database.entity.UserEntity
import com.example.axon.utils.ConverterFactory

interface UserDataSource {

    fun getUsersFactory(converterFactory: ConverterFactory)
            : DataSource.Factory<Int, UserDisplayModel>

    fun deleteCachedRepositories()

    fun insert(repositoryItems: List<UserEntity>)

    fun insertAndClearCache(repositoryItems: List<UserEntity>)
}

class UserDataSourceImpl(private val database: AppDatabase) : UserDataSource {

    override fun getUsersFactory(
        converterFactory: ConverterFactory
    ): DataSource.Factory<Int, UserDisplayModel> {
        return database.repositoryDao().queryItems()
            .mapByPage(converterFactory::convert)
    }

    override fun deleteCachedRepositories(): Unit=
        database.repositoryDao().deleteCachedItems()

    override fun insert(repositoryItems: List<UserEntity>) : Unit=
        database.repositoryDao().insert(repositoryItems)

    override fun insertAndClearCache(repositoryItems: List<UserEntity>): Unit=
        database.repositoryDao().insertAndClearCache(repositoryItems)
}