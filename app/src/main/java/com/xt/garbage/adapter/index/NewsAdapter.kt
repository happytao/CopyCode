package com.xt.garbage.adapter.index

import android.text.Html
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.DraggableModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xt.garbage.R
import com.xt.garbage.base.BaseConstant
import com.xt.garbage.bean.workmain.IndexBean
import com.xt.garbage.utils.ImageLoaderUtil
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 *@author:DIY
 *@date: 2021/6/2
 */
class NewsAdapter(data: MutableList<IndexBean.ResultDTO.NewsListDTO>?) : BaseQuickAdapter<IndexBean.ResultDTO.NewsListDTO, BaseViewHolder>(layoutResId = R.layout.item_news, data),DraggableModule {
    override fun convert(holder: BaseViewHolder, item: IndexBean.ResultDTO.NewsListDTO) {
        holder.setText(R.id.title,item.newsTitle)
                .setText(R.id.content,Html.fromHtml(item.newsContent))
        ImageLoaderUtil.loadCustRoundCircleImage(context,BaseConstant.URLPREFIX + item.newsResourceUrl,holder.getView(R.id.logo),RoundedCornersTransformation.CornerType.ALL,8)
    }
}