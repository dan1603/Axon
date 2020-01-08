package com.example.axon.presentation.fragments.user_detail

import android.os.Bundle
import com.example.axon.R
import com.example.axon.databinding.SearchDataBinding
import com.example.axon.presentation.activities.main.EXTRAS_USER_ID
import com.example.axon.presentation.activities.main.MainActivity
import com.example.axon.presentation.base.BaseFragment

class UserDetailFragment : BaseFragment<SearchDataBinding>() {

    private lateinit var modelBinding: UserDetailModelBinding

    private var userId: String = ""

    override fun getLayoutId(): Int = R.layout.fragment_user_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(EXTRAS_USER_ID)?.let { userId = it }
        setupAppBar()
    }

    override fun setupViewLogic(binder: SearchDataBinding) {
        binder.bindingModel = modelBinding
        setupAppBar()
    }

    private fun setupAppBar() = (activity as MainActivity).supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(false)
        title = getString(R.string.app_title_search)
    }

    companion object {
        @JvmStatic
        fun newInstance(userId: String): UserDetailFragment = UserDetailFragment().apply {
            arguments = Bundle().apply { putString(EXTRAS_USER_ID, userId) }
        }
    }
}
