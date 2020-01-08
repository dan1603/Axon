package com.example.axon.di.component

import com.example.axon.di.module.DatabaseModule
import com.example.axon.usecases.repository.data_source.UserDataSource
import dagger.Component

@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    val repositoryDataSource: UserDataSource
}
