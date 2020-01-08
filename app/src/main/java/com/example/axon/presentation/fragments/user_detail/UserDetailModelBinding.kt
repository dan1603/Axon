package com.example.axon.presentation.fragments.user_detail

import com.example.axon.presentation.activities.main.MainListener
import com.example.axon.usecases.repository.data_source.database.entity.UserEntity

class UserDetailModelBinding(
    private val user: UserEntity,
    private val listener: MainListener
) {

}