package com.example.axon.presentation.fragments.users_list

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.axon.data.card_models.UserDisplayModel
import com.example.axon.presentation.activities.main.MainListener

class UserModelBinding(
    private val data: UserDisplayModel,
    private val listener: MainListener
) : BaseObservable() {

    val userId: Int
        @Bindable get() {
            return data.getBaseModel().uid
        }

    val userName: String
        @Bindable get() {
            return data.getBaseModel().name.getFullName()
        }

    val pictureUrl: String
        @Bindable get() {
            return data.getBaseModel().picture.large
        }

    fun onUserClicked(id: Int) {
        listener.openUserDetail(id)
    }
}
