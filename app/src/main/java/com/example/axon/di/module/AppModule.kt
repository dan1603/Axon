package com.example.axon.di.module

import android.app.Application
import com.google.gson.Gson
import com.example.axon.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app : Application) {

    @Provides
    @AppScope
    fun provideApplication(): Application {
        return app
    }

    @Provides
    @AppScope
    fun provideGson(): Gson {
        return Gson()
    }
}