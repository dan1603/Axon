package com.example.axon.domain

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.axon.presentation.base.LoadingState
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable=CompositeDisposable()
    val macroLoadingState = MediatorLiveData<LoadingState>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}