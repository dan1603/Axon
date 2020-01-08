package com.example.axon.presentation.fragments.user_detail

import android.content.Context
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.axon.R
import com.example.axon.usecases.repository.data_source.database.entity.UserEntity

class UserDetailModelBinding(
    private val user: UserEntity,
    private val context: Context
) : BaseObservable() {

    val pictureUrl: String?
        @Bindable get() {
            return user.picture.thumbnail
        }

    val userName: String?
        @Bindable get() {
            return user.name.getFullName()
        }

    val age: String?
        @Bindable get() {
            return context.getString(R.string.format_age, user.dateOfBirth.age)
        }

    val phone: String?
        @Bindable get() {
            return user.cell
        }

    val email: String?
        @Bindable get() {
            return user.email
        }

    val location: String?
        @Bindable get() {
            return user.location.getFullAddress()
        }
}