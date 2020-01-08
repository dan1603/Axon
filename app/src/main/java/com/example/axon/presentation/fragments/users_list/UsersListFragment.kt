package com.example.axon.presentation.fragments.users_list

import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.axon.BR
import com.example.axon.R
import com.example.axon.data.card_models.UserDisplayModel
import com.example.axon.databinding.UsersListDataBinding
import com.example.axon.di.component.ViewModelComponent
import com.example.axon.domain.UserViewModel
import com.example.axon.presentation.base.BasePagingFragment
import com.example.axon.utils.FIRST_LIST_POSITION
import com.example.axon.utils.RepositoriesLayoutManager
import com.example.axon.utils.MIN_LIST_SIZE
import com.example.axon.utils.extention.showSnack
import com.example.axon.utils.multistate.MultiStateView
import javax.inject.Inject

class UsersListFragment : BasePagingFragment<UsersListDataBinding>() {

    var viewModel: UserViewModel?=null
        @Inject set


    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun getLayoutId(): Int=R.layout.fragment_users_list

    override fun getListView(): RecyclerView=viewBinder.multiStateView.listView

    override fun getRefreshView(): SwipeRefreshLayout=viewBinder.swipeRefresh

    override fun getStateView(): MultiStateView = viewBinder.multiStateView.multiState

    override fun initListView() {
        context?.apply {
            val layoutManager = RepositoriesLayoutManager(this)

            getListView().layoutManager = layoutManager
            getListView().adapter = pagingAdapter

            listener?.let {
                pagingAdapter.listener = it
            }

            pagingAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {

                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    super.onItemRangeInserted(positionStart, itemCount)
                    if (positionStart == FIRST_LIST_POSITION && itemCount == MIN_LIST_SIZE) {
                        getListView().scrollToPosition(FIRST_LIST_POSITION)
                    }
                }
            })

            viewModel?.getRefreshing().let { viewBinder.setVariable(BR.refreshing, it) }

            viewModel?.isLoading().let { viewBinder.setVariable(BR.loading, it) }
        }
    }

    override fun initObserver() {
        viewModel?.initLiveData(this)
        viewModel?.getPagedList()?.observe(this, Observer(this@UsersListFragment::onItemsLoaded))
    }

    override fun removeObserver() {
        viewModel?.getPagedList()?.removeObservers(this)
    }

    override fun setupViewLogic(binding: UsersListDataBinding) {
        //todo adapt for API
        viewModel?.fetchData()
        binding.swipeRefresh.setOnRefreshListener {
            viewModel?.setRefreshing(true)
            //todo adapt for API
            viewModel?.fetchData()
        }
    }

    override fun onItemsLoaded(items: PagedList<UserDisplayModel>?) {
        if (items.isNullOrEmpty()) {
            getStateView().viewState=MultiStateView.VIEW_STATE_EMPTY
        } else {
            pagingAdapter.submitList(items)
            getStateView().viewState=MultiStateView.VIEW_STATE_CONTENT
        }
    }

    override fun displayProgress() {
        getStateView().viewState=MultiStateView.VIEW_STATE_LOADING
    }

    override fun onLoadError(errorMessage: String) {
        getStateView().viewState=MultiStateView.VIEW_STATE_ERROR
        activity?.showSnack(errorMessage)
    }

    override fun onDetach() {
        viewModel?.clearCachedItems()
        super.onDetach()
    }

    companion object {
        @JvmStatic
        fun newInstance(): UsersListFragment = UsersListFragment()
    }
}
