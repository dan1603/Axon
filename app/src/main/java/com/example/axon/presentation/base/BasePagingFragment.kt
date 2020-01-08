package com.example.axon.presentation.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedList
import com.example.axon.App
import com.example.axon.di.component.ViewModelComponent
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.axon.data.card_models.UserDisplayModel
import com.example.axon.presentation.adapter.DiffCallbackBaseCardModel
import com.example.axon.presentation.adapter.PagingAdapter
import com.example.axon.utils.multistate.MultiStateView

abstract class BasePagingFragment<V : ViewDataBinding>: BaseFragment<V>(),
    ItemsLoadListener<PagedList<UserDisplayModel>> {

    protected var pagingAdapter: PagingAdapter = PagingAdapter(DiffCallbackBaseCardModel())

    abstract override fun injectDependency(component: ViewModelComponent)

    abstract fun initListView()

    abstract fun getListView(): RecyclerView

    abstract fun getRefreshView(): SwipeRefreshLayout

    abstract fun getStateView() : MultiStateView

    abstract fun initObserver()

    abstract fun removeObserver()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        displayProgress()
        initListView()
        initObserver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createDaggerDependencies()
    }

    override fun onDestroyView() {
        removeObserver()
        super.onDestroyView()
    }

    private fun createDaggerDependencies() {
       activity?.apply { injectDependency((application as App).getViewModelComponent()) }
    }
}