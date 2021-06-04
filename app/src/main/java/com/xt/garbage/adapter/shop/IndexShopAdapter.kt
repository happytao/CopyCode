package com.xt.garbage.adapter.shop

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.DraggableModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xt.garbage.R
import com.xt.garbage.base.BaseConstant
import com.xt.garbage.bean.shop.HomeShopBean
import com.xt.garbage.bean.workmain.IndexBean
import com.xt.garbage.utils.ImageLoaderUtil
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 *@author:DIY
 *@date: 2021/6/2
 */
class IndexShopAdapter(data: MutableList<IndexBean.ResultDTO.GoodsListDTO>?) : BaseQuickAdapter<IndexBean.ResultDTO.GoodsListDTO, BaseViewHolder>(layoutResId = R.layout.item_index_shop, data),DraggableModule {
    override fun convert(holder: BaseViewHolder, item: IndexBean.ResultDTO.GoodsListDTO) {
        var money: TextView = holder.getView(R.id.money)
        holder.setText(R.id.name,item.goodsName)
        ImageLoaderUtil.loadCustRoundCircleImage(context,BaseConstant.URLPREFIX + item.goodsResourceUrl,holder.getView(R.id.logo),RoundedCornersTransformation.CornerType.ALL,8)
        when(item.goodsSellType) {
            1 -> {
                money.text = item.goodsScorePrice.toString() + "积分"
            }
            2 -> {
                money.text = item.goodsCashPrice.toString() + "元"
            }
            else -> {
                money.text = item.goodsCashPrice.toString() + "元" + item.goodsScorePrice.toString() + "积分"
            }
        }
    }
}