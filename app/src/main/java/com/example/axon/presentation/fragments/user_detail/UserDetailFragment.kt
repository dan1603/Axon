package com.example.axon.presentation.fragments.user_detail

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.axon.R
import com.example.axon.databinding.UserDetailBinding
import com.example.axon.di.component.ViewModelComponent
import com.example.axon.domain.UserViewModel
import com.example.axon.presentation.activities.main.EXTRAS_USER_ID
import com.example.axon.presentation.activities.main.MainActivity
import com.example.axon.presentation.base.BaseFragment
import javax.inject.Inject

class UserDetailFragment : BaseFragment<UserDetailBinding>() {

    var viewModel: UserViewModel? = null
        @Inject set

    private var userId: Int = 0

    override fun getLayoutId(): Int = R.layout.fragment_user_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(EXTRAS_USER_ID)?.let { userId = it }
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun setupViewLogic(binder: UserDetailBinding) {
        setupAppBar()
        viewModel?.getUserById(userId)?.observe(this, Observer { it?.let {
            binder.bindingModel = UserDetailModelBinding(it, this@UserDetailFragment.requireContext())
        }})
    }

    private fun setupAppBar() = (activity as MainActivity).supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.title_profile_screen)
    }

    companion object {
        @JvmStatic
        fun newInstance(userId: Int): UserDetailFragment = UserDetailFragment().apply {
            arguments = Bundle().apply { putInt(EXTRAS_USER_ID, userId) }
        }
    }
}
