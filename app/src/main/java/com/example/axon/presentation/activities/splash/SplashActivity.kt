package com.example.axon.presentation.activities.splash

import android.os.Handler
import com.example.axon.R
import com.example.axon.databinding.SplashBinding
import com.example.axon.di.component.ViewModelComponent
import com.example.axon.presentation.base.BaseActivity
import com.example.axon.utils.DELAY_3000

class SplashActivity : BaseActivity<SplashBinding>() {

    override fun injectDependency(component: ViewModelComponent) {
       component.inject(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun setupViewLogic(binding: SplashBinding) {
        Handler().postDelayed({
            navigator.openMainScreen()
            finish()
        }, DELAY_3000)
    }
}
