package com.example.axon.di.component

import com.google.gson.Gson
import com.example.axon.di.module.AppModule
import com.example.axon.di.scope.AppScope
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    val gson: Gson
}