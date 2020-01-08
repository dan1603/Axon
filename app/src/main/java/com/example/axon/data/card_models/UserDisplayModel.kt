package com.example.axon.data.card_models

import com.example.axon.usecases.repository.data_source.database.entity.UserEntity

class UserDisplayModel(private var repositoryEntity : UserEntity) {

    fun getRepositoryId(): String {
        return repositoryEntity.uid.toString()
    }

    fun getBaseModel(): UserEntity {
        return repositoryEntity
    }

}