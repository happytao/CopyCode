package com.xt.garbage.adapter.shop

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.DraggableModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xt.garbage.R
import com.xt.garbage.bean.shop.GetAddressResultBean

/**
 *@author:DIY
 *@date: 2021/5/25
 */
class AddressDialogAdapter(data: MutableList<GetAddressResultBean.ResultDTO>?) : BaseQuickAdapter<GetAddressResultBean.ResultDTO, BaseViewHolder>(layoutResId = R.layout.item_address_list, data),DraggableModule {
    override fun convert(holder: BaseViewHolder, item: GetAddressResultBean.ResultDTO) {
        holder.setText(R.id.details,item.province + item.city + item.area + item.detailAddress)
                .setText(R.id.mobile,item.name + " " +item.mobile)
    }
}