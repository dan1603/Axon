package com.example.axon.di.module

import com.example.axon.di.scope.RepositoryScope
import com.example.axon.usecases.repository.*
import com.example.axon.usecases.repository.data_source.UserDataSource
import com.example.axon.usecases.repository.remote_data_source.UserRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @RepositoryScope
    @Provides
    internal fun providesUserRepository(userRemoteDataSource: UserRemoteDataSource, repositoryDataSource: UserDataSource): UserRepository {
        return UserRepositoryImpl(userRemoteDataSource, repositoryDataSource)
    }
}