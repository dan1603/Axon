package com.example.axon.di.component

import com.example.axon.di.module.ApiModule
import com.example.axon.di.scope.ApiScope
import com.example.axon.usecases.repository.remote_data_source.UserRemoteDataSource
import dagger.Component

@ApiScope
@Component(modules = [ApiModule::class], dependencies = [AppComponent::class])
interface ApiComponent {
    val userRemoteDataSource: UserRemoteDataSource
}
