package com.example.axon

import androidx.room.Room
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.axon.di.module.DatabaseModule
import com.example.axon.di.component.*
import com.example.axon.di.module.*
import com.example.axon.usecases.repository.data_source.database.AppDatabase
import com.example.axon.utils.DATABASE_NAME

class App: MultiDexApplication() {

    private lateinit var viewModelComponent: ViewModelComponent
    private lateinit var database: AppDatabase

    init {
        applicationInstance = this
    }

    companion object {
        private lateinit var applicationInstance: App

        fun get(): App {
            return applicationInstance.applicationContext as App
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        initRoom()
        initDagger()
    }

    private fun initRoom() {
        database = Room.databaseBuilder(this, AppDatabase::class.java, DATABASE_NAME)
            .allowMainThreadQueries()
            .build()
    }

    private fun initDagger() {
        val appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        val apiComponent = DaggerApiComponent.builder()
            .appComponent(appComponent)
            .apiModule(ApiModule())
            .build()

        val databaseComponent = DaggerDatabaseComponent.builder()
            .databaseModule(DatabaseModule(database))
            .build()

        val repositoryComponent = DaggerRepositoryComponent.builder()
            .apiComponent(apiComponent)
            .databaseComponent(databaseComponent)
            .repositoryModule(RepositoryModule())
            .build()

        val useCasesComponent = DaggerUseCasesComponent.builder()
            .repositoryComponent(repositoryComponent)
            .useCasesModule(UseCasesModule())
            .build()

        viewModelComponent = DaggerViewModelComponent.builder()
            .useCasesComponent(useCasesComponent)
            .viewModelModule(ViewModelModule(this))
            .build()
    }

    fun getViewModelComponent(): ViewModelComponent {
        return this.viewModelComponent
    }
}