package com.example.axon.di.module

import com.example.axon.usecases.repository.data_source.UserDataSource
import com.example.axon.usecases.repository.data_source.UserDataSourceImpl
import com.example.axon.usecases.repository.data_source.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(private val appDatabase: AppDatabase) {
    @Provides
    internal fun providesAppDatabase(): AppDatabase {
        return appDatabase
    }

    @Provides
    internal fun providesUserDataSource(appDatabase: AppDatabase): UserDataSource {
        return UserDataSourceImpl(appDatabase)
    }
}