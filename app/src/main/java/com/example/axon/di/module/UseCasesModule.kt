package com.example.axon.di.module

import com.example.axon.di.scope.UseCasesScope
import com.example.axon.usecases.*
import com.example.axon.usecases.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @UseCasesScope
    @Provides
    internal fun providesUserUseCases(repository: UserRepository): UserUseCases {
        return UserUseCasesImpl(repository)
    }
}