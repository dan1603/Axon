package com.example.axon.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.axon.data.card_models.UserDisplayModel
import com.example.axon.presentation.base.ItemsLoadListener
import com.example.axon.usecases.UserUseCases
import com.example.axon.utils.ConverterFactory
import java.util.*

class UserViewModel(private val repositoriesUseCases: UserUseCases) : BasePagingViewModel() {

    init {
        initPagedConfig()
    }

    fun initLiveData(listener: ItemsLoadListener<PagedList<UserDisplayModel>>) {
        itemLoadedListener = listener
        initPagedListLiveData(repositoriesUseCases.getFactory(ConverterFactory()))
    }

    fun getPagedList(): LiveData<PagedList<UserDisplayModel>> = listCards

    override fun fetchData() {
        compositeDisposable.add(repositoriesUseCases.fetchUsers()
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
        compositeDisposable.add(repositoriesUseCases.fetchUsersNext(page)
            .subscribe({ setLoading(false) },
                { setLoading(false) }
            )
        )
    }

    override fun clearCachedItems() {
        repositoriesUseCases.deleteCachedUsers()
    }
}
