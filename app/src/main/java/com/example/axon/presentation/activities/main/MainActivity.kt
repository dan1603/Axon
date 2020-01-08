package com.example.axon.presentation.activities.main

import android.view.MenuItem
import com.example.axon.R
import com.example.axon.databinding.MainDataBinding
import com.example.axon.di.component.ViewModelComponent
import com.example.axon.presentation.base.BaseActivity
import com.example.axon.presentation.fragments.user_detail.UserDetailFragment
import com.example.axon.presentation.fragments.users_list.UsersListFragment
import com.example.axon.utils.extention.replaceFragment

const val EXTRAS_USER_ID = "EXTRAS_USER_ID"

class MainActivity : BaseActivity<MainDataBinding>(), MainListener {

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun setupViewLogic(binding: MainDataBinding) {
        openUsersList()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun openUserDetail(userId : Int) {
        this.replaceFragment(R.id.mainContent, UserDetailFragment.newInstance(userId), true, false)
    }

    private fun openUsersList() {
        this.replaceFragment(R.id.mainContent, UsersListFragment.newInstance(), false, false)
    }
}
