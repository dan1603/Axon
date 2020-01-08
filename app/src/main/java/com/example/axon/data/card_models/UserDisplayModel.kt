package com.example.axon.data.card_models

import com.example.axon.usecases.repository.data_source.database.entity.UserEntity

class UserDisplayModel(private var userEntity : UserEntity) {

    fun getRepositoryId(): String {
        return userEntity.uid.toString()
    }

    fun getBaseModel(): UserEntity {
        return userEntity
    }

}