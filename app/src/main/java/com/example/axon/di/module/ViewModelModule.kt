package com.example.axon.di.module

import android.app.Application
import com.example.axon.App
import com.example.axon.di.scope.ViewModelScope
import com.example.axon.domain.UserViewModel
import com.example.axon.usecases.UserUseCases
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule(app: App) {

    internal var app: Application = app

    @ViewModelScope
    @Provides
    internal fun providesUserViewModel(userUseCases: UserUseCases): UserViewModel {
        return UserViewModel(userUseCases)
    }
}