package com.xt.garbage.adapter.shop

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xt.garbage.R
import com.xt.garbage.bean.shop.CommodityDetailsBean

/**
 *@author:DIY
 *@date: 2021/6/6
 */
class GoodsAdapter(data: MutableList<CommodityDetailsBean.Result.GoodsDetail>?) : BaseQuickAdapter<CommodityDetailsBean.Result.GoodsDetail, BaseViewHolder>(layoutResId = R.layout.item_goods, data) {
    override fun convert(holder: BaseViewHolder, item: CommodityDetailsBean.Result.GoodsDetail) {
        holder.setText(R.id.key,item.detailKey)
                .setText(R.id.value,item.detailKey)
    }
}