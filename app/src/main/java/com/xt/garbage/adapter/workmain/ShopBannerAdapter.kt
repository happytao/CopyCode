package com.xt.garbage.adapter.workmain

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.xt.garbage.base.BaseConstant
import com.xt.garbage.bean.shop.CommodityDetailsBean
import com.xt.garbage.utils.ImageLoaderUtil
import com.youth.banner.adapter.BannerAdapter

/**
 *@author:DIY
 *@date: 2021/6/6
 */
class ShopBannerAdapter(datas: MutableList<CommodityDetailsBean.Result.Resource>?,var context:Context) : BannerAdapter<CommodityDetailsBean.Result.Resource, ShopBannerAdapter.BannerViewHolder>(datas) {
    inner class BannerViewHolder(var imageView: ImageView) : RecyclerView.ViewHolder(imageView) {

    }

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        var imageView:ImageView = ImageView(parent?.context)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                        ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder?, data: CommodityDetailsBean.Result.Resource?, position: Int, size: Int) {
        ImageLoaderUtil.loadImage(context,BaseConstant.URLPREFIX + data?.resourceUrl,holder?.imageView)
    }
}