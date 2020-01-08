package com.example.axon.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.axon.data.card_models.UserDisplayModel

class DiffCallbackBaseCardModel : DiffUtil.ItemCallback<UserDisplayModel>() {

    companion object {
        val CONTENT = Any()
    }

    override fun areItemsTheSame(oldRepository: UserDisplayModel, newRepository: UserDisplayModel): Boolean {
        return oldRepository.getRepositoryId() == newRepository.getRepositoryId()
    }

    override fun areContentsTheSame(oldRepository: UserDisplayModel, newRepository: UserDisplayModel): Boolean {
        return oldRepository.getRepositoryId() == newRepository.getRepositoryId()
    }
}