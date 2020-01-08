package com.example.axon.di.component

import com.example.axon.di.module.ViewModelModule
import com.example.axon.di.scope.ViewModelScope
import com.example.axon.presentation.activities.main.MainActivity
import com.example.axon.presentation.activities.splash.SplashActivity
import com.example.axon.presentation.fragments.user_detail.UserDetailFragment
import com.example.axon.presentation.fragments.users_list.UsersListFragment
import dagger.Component

@ViewModelScope
@Component(modules = [ViewModelModule::class], dependencies = [UseCasesComponent::class])
interface ViewModelComponent {
    fun inject(activity: SplashActivity)
    fun inject(activity: MainActivity)
    fun inject(fragment: UsersListFragment)
    fun inject(fragment: UserDetailFragment)
}