package com.example.axon.usecases.repository

import androidx.paging.DataSource
import com.example.axon.data.card_models.UserDisplayModel
import com.example.axon.usecases.repository.data_source.UserDataSource
import com.example.axon.usecases.repository.data_source.database.entity.UserEntity
import com.example.axon.usecases.repository.remote_data_source.UserRemoteDataSource
import com.example.axon.utils.ConverterFactory
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {

    fun fetchUsers(): Completable

    fun fetchUsersNext(lastItemId: Int): Completable

    fun deleteCachedUsers(): Completable

    fun getFactory(
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, UserDisplayModel>
}

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userDataSource: UserDataSource
) : UserRepository {

    override fun fetchUsers(): Completable {
        return userRemoteDataSource
            .fetchUsers()
            .doOnSuccess {
                it.body()?.let { response -> saveItems(response.results, false) }
            }
            .doOnError {
                it.printStackTrace()
            }
            .flatMapCompletable {
                Completable.fromAction { }
            }
    }

    override fun fetchUsersNext(lastPage: Int): Completable {
        return userRemoteDataSource
            .fetchUsersNext(lastPage)
            .flatMap {
                it.body()?.let { response ->
                    response.results.forEach { it.convertItemForDataSource(item = it, isCached = true) }
                    return@let Single.just(response)
                }
            }
            .flatMapCompletable { response ->
                Completable.fromAction { saveItems(response.results, true) }
            }
    }

    override fun deleteCachedUsers(): Completable=
        Completable.fromAction { userDataSource.deleteCachedRepositories() }


    override fun getFactory(
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, UserDisplayModel> =
        userDataSource.getUsersFactory(modelConverter)

    private fun saveItems(
        feedItems: List<UserEntity>, isCached: Boolean
    ) {
        if (isCached) {
            userDataSource.insert(feedItems)
        } else {
            userDataSource.insertAndClearCache(feedItems)
        }
    }
}