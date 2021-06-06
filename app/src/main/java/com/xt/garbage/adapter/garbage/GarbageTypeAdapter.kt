package com.xt.garbage.adapter.garbage

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xt.garbage.R
import com.xt.garbage.base.BaseConstant
import com.xt.garbage.bean.garbage.GarbageTypeBean
import com.xt.garbage.bean.garbage.MySection
import com.xt.garbage.utils.ImageLoaderUtil

/**
 *@author:DIY
 *@date: 2021/6/5
 */
class GarbageTypeAdapter(sectionHeadResId: Int, layoutResId: Int, data: MutableList<MySection>?) : BaseSectionQuickAdapter<MySection, BaseViewHolder>(sectionHeadResId, layoutResId, data) {
    override fun convert(holder: BaseViewHolder, item: MySection) {
        if(item.myObject is String){
            holder.setText(R.id.name,item.myObject.toString())
        }

    }

    override fun convertHeader(helper: BaseViewHolder, item: MySection) {
        var categoryListBean:GarbageTypeBean.Result.CategoryList.Category = item.myObject as GarbageTypeBean.Result.CategoryList.Category
        helper.setText(R.id.name,categoryListBean.categoryName)
        ImageLoaderUtil.loadCircleImage(context,BaseConstant.URLPREFIX + categoryListBean.categoryResourceUrl,helper.getView(R.id.logo))

    }
}