package com.example.axon.utils

import com.example.axon.data.BaseModel
import com.example.axon.data.card_models.UserDisplayModel
import com.example.axon.usecases.repository.data_source.database.entity.UserEntity
import java.util.ArrayList

class ConverterFactory {

    fun convert(list: List<BaseModel>): List<UserDisplayModel> {

        val items = ArrayList<UserDisplayModel>()

        list.forEach {
            convert(it)?.apply {
                items.add(this)
            }
        }
        return items
    }

    private fun convert(item: BaseModel): UserDisplayModel? {
        if(item is UserEntity && item.isUser){
            return UserDisplayModel(item)
        }
        return null
    }
}
