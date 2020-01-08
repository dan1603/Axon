package com.example.axon.domain

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.axon.data.card_models.UserDisplayModel
import com.example.axon.presentation.base.ItemsLoadListener
import com.example.axon.usecases.repository.data_source.database.entity.UserEntity
import com.example.axon.utils.CONTENT_PAGE_SIZE
import com.example.axon.utils.DEFAULT_INITIAL_LOADED_KEY

abstract class BasePagingViewModel : BaseViewModel() {

    protected lateinit var listCards: LiveData<PagedList<UserDisplayModel>>
    protected lateinit var itemLoadedListener: ItemsLoadListener<PagedList<UserDisplayModel>>

    private val refreshing=ObservableBoolean()
    private val loading=ObservableBoolean()

    private lateinit var pagedListConfiguration: PagedList.Config
    private var currentPage: Int = 1

    fun initPagedConfig() {
        pagedListConfiguration=PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(CONTENT_PAGE_SIZE)
            .setPrefetchDistance(CONTENT_PAGE_SIZE / 2)
            .build()
    }

    fun initPagedListLiveData(factory: DataSource.Factory<Int, UserDisplayModel>) {
        listCards=initPagedList(factory, DEFAULT_INITIAL_LOADED_KEY)
    }

    private fun initPagedList(
        factory: DataSource.Factory<Int, UserDisplayModel>,
        initialLoadKey: Int
    ): LiveData<PagedList<UserDisplayModel>> {
        setLoading(false)
        return LivePagedListBuilder(factory, pagedListConfiguration)
            .setBoundaryCallback(object : PagedList.BoundaryCallback<UserDisplayModel>() {
                override fun onItemAtEndLoaded(itemAtEnd: UserDisplayModel) {
                    super.onItemAtEndLoaded(itemAtEnd)
                    currentPage.let { page ->
                        if (itemAtEnd.getBaseModel() is UserEntity)
                            rangeData(page)
                        currentPage++
                    }
                }
            })
            .setInitialLoadKey(initialLoadKey)
            .build()
    }

    fun getRefreshing(): ObservableBoolean {
        return refreshing
    }

    fun setRefreshing(isRefreshing: Boolean) {
        refreshing.set(isRefreshing)
    }

    fun isLoading(): ObservableBoolean {
        return loading
    }

    protected fun setLoading(isLoading: Boolean) {
        loading.set(isLoading)
    }

    abstract fun fetchData()

    abstract fun rangeData(page: Int)

    abstract fun clearCachedItems()
}