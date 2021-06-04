package com.xt.garbage.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.xt.garbage.base.BaseConstant
import com.xt.garbage.bean.workmain.IndexBean
import com.xt.garbage.utils.ImageLoaderUtil
import com.youth.banner.adapter.BannerAdapter
import kotlin.contracts.contract

/**
 *@author:DIY
 *@date: 2021/6/4
 */
class ImageAdapter(datas: MutableList<IndexBean.ResultDTO.BannerListDTO>?, var context: Context) : BannerAdapter<IndexBean.ResultDTO.BannerListDTO, ImageAdapter.BannerViewHolder>(datas) {
    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView:ImageView = itemView as ImageView
    }

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        var imageView:ImageView = ImageView(parent?.context)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                        ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder?, data: IndexBean.ResultDTO.BannerListDTO?, position: Int, size: Int) {
        ImageLoaderUtil.loadImage(context,BaseConstant.URLPREFIX+data?.imgUrl,holder?.imageView)
    }
}