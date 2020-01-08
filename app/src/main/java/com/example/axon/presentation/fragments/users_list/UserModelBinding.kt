package com.example.axon.presentation.fragments.users_list

import android.content.Context
import android.graphics.Typeface
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.axon.R
import com.example.axon.data.card_models.UserDisplayModel
import com.example.axon.presentation.activities.main.MainListener
import com.example.axon.utils.binding.TextSpanModel

class UserModelBinding(
    private val data: UserDisplayModel,
    private val listener: MainListener
) : BaseObservable() {

    private var context: Context = listener as Context

    val userName: String
        @Bindable get() {
            return data.getBaseModel().name.getFullName()
        }

    val pictureUrl: String
        @Bindable get() {
            return data.getBaseModel().picture.large
        }

    fun onUserClicked(url: String) {
        listener.onRepositoryClicked(url)
    }
}
