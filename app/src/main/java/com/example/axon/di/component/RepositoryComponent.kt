package com.example.axon.di.component

import com.example.axon.di.module.RepositoryModule
import com.example.axon.di.scope.RepositoryScope
import com.example.axon.usecases.repository.UserRepository
import dagger.Component

@RepositoryScope
@Component(modules = [RepositoryModule::class], dependencies = [ApiComponent::class, DatabaseComponent::class])
interface RepositoryComponent {
    val userRepository: UserRepository
}