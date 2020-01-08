package com.example.axon.presentation.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.example.axon.R
import com.example.axon.data.card_models.UserDisplayModel
import com.example.axon.databinding.ItemUserBinding
import com.example.axon.presentation.activities.main.MainListener
import com.example.axon.presentation.fragments.users_list.UserModelBinding

class PagingAdapter(diffUtil: DiffUtil.ItemCallback<UserDisplayModel>) :
    MultiTypeDataBoundAdapter<UserDisplayModel, ViewDataBinding>(diffUtil) {

    lateinit var listener: MainListener

    override fun getItemLayoutId(position: Int): Int {
        getItem(position)?.let {
            return R.layout.item_user
        }
        return -1
    }

    override fun bindItem(
        holder: DataBoundViewHolder<ViewDataBinding>,
        position: Int,
        payloads: List<Any>
    ) {
        super.bindItem(holder, position, payloads)
        val item = getItem(position)
        if (holder.binding is ItemUserBinding) {
            item?.let {
                holder.binding.bindingModel = UserModelBinding(it, listener)
            }
        }
    }
}