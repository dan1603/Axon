package com.example.axon.di.component

import com.example.axon.di.module.UseCasesModule
import com.example.axon.di.scope.UseCasesScope
import com.example.axon.usecases.UserUseCases
import dagger.Component

@UseCasesScope
@Component(modules = [UseCasesModule::class], dependencies = [RepositoryComponent::class])
interface UseCasesComponent {
    val userUseCases : UserUseCases
}