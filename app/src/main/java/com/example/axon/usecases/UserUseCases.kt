package com.example.axon.usecases

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.axon.data.card_models.UserDisplayModel
import com.example.axon.usecases.repository.UserRepository
import com.example.axon.usecases.repository.data_source.database.entity.UserEntity
import com.example.axon.utils.ConverterFactory
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface UserUseCases {

    fun fetchUsers(): Completable

    fun fetchUsersNext(lastItemId: Int): Completable

    fun deleteCachedUsers(): Completable

    fun getUserById(id: Int): LiveData<UserEntity>

    fun getFactory(
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, UserDisplayModel>
}

class UserUseCasesImpl(private val repository: UserRepository) : UserUseCases {

    override fun fetchUsers(): Completable=
        repository.fetchUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun fetchUsersNext(lastItemId: Int): Completable=
        repository.fetchUsersNext(lastItemId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun deleteCachedUsers(): Completable=
        repository.deleteCachedUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())

    override fun getUserById(id: Int): LiveData<UserEntity> =
        repository.getUserById(id)

    override fun getFactory(
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, UserDisplayModel> =
        repository.getFactory(modelConverter)
}