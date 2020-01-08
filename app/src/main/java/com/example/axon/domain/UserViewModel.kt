package com.example.axon.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.axon.data.card_models.UserDisplayModel
import com.example.axon.presentation.base.ItemsLoadListener
import com.example.axon.usecases.UserUseCases
import com.example.axon.usecases.repository.data_source.database.entity.UserEntity
import com.example.axon.utils.ConverterFactory
import java.util.*

class UserViewModel(private val userUseCases: UserUseCases) : BasePagingViewModel() {

    init {
        initPagedConfig()
    }

    fun initLiveData(listener: ItemsLoadListener<PagedList<UserDisplayModel>>) {
        itemLoadedListener = listener
        initPagedListLiveData(userUseCases.getFactory(ConverterFactory()))
    }

    fun getPagedList(): LiveData<PagedList<UserDisplayModel>> = listCards

    override fun fetchData() {
        compositeDisposable.add(userUseCases.fetchUsers()
            .subscribe({ setRefreshing(false) }, { throwable ->
                if (throwable is NoSuchElementException) {
                    itemLoadedListener.onItemsLoaded(null)
                } else {
                    throwable.message?.let { itemLoadedListener.onLoadError(it) }
                }
                setRefreshing(false)
            })
        )
    }

    override fun rangeData(page: Int) {
        setLoading(true)
        compositeDisposable.add(userUseCases.fetchUsersNext(page)
            .subscribe({ setLoading(false) },
                { setLoading(false) }
            )
        )
    }

    override fun clearCachedItems() {
        userUseCases.deleteCachedUsers()
    }

    fun getUserById(id: Int): LiveData<UserEntity> =
        userUseCases.getUserById(id)
}
