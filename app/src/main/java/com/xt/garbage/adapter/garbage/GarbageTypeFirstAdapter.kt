package com.xt.garbage.adapter.garbage

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.DraggableModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xt.garbage.R
import com.xt.garbage.bean.garbage.GarbageTypeBean

/**
 *@author:DIY
 *@date: 2021/6/5
 */
class GarbageTypeFirstAdapter(data: MutableList<GarbageTypeBean.Result>?) : BaseQuickAdapter<GarbageTypeBean.Result, BaseViewHolder>(layoutResId = R.layout.item_shop_category_first_list, data),DraggableModule {
    override fun convert(holder: BaseViewHolder, item: GarbageTypeBean.Result) {
        var name:TextView = holder.getView(R.id.name)
        if(item.isTrue) {
            holder.getView<View>(R.id.line).setBackgroundColor(Color.parseColor("#00BF60"))
            holder.getView<View>(R.id.item_layout).setBackgroundColor(Color.parseColor("#f8f8f8"))
            name.setTextColor(Color.parseColor("#00BF60"))
        }
        else {
            holder.getView<View>(R.id.item_layout).setBackgroundColor(Color.parseColor("#FFFFFF"))
            name.setTextColor(Color.parseColor("#000000"))
            holder.getView<View>(R.id.line).setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
        holder.setText(R.id.name,item.categoryName)
    }
}