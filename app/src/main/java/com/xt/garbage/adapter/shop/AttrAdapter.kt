package com.xt.garbage.adapter.shop

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.nex3z.flowlayout.FlowLayout
import com.xt.garbage.R
import com.xt.garbage.bean.shop.CommodityDetailsBean

/**
 *@author:DIY
 *@date: 2021/6/6
 */
class AttrAdapter(data: MutableList<CommodityDetailsBean.Result.Spec>?) : BaseQuickAdapter<CommodityDetailsBean.Result.Spec, BaseViewHolder>(layoutResId = R.layout.item_attr, data) {
    private var flowItemClick:FlowItemClick? = null
    override fun convert(holder: BaseViewHolder, item: CommodityDetailsBean.Result.Spec) {
        var flowLayout:FlowLayout = holder.getView(R.id.size_flow)
        holder.setText(R.id.size,item.specName)
        initFlowLayout(item.attrList,flowLayout,holder)
    }

    @SuppressLint("NewApi")
    private fun initFlowLayout(attrList: List<CommodityDetailsBean.Result.Spec.Attr>, flowLayout: FlowLayout, holder: BaseViewHolder) {
        for (index in attrList.indices) {
            val view:TextView = TextView(context)
            view.text = attrList[index].attrName
            if(attrList[index].isTrue) {
                view.background = context.resources.getDrawable(R.drawable.bg_commodity_selector_text_true,null)
                view.setTextColor(context.resources.getColor(R.color.color_00BF60,null))
            }
            else {
                view.background = context.resources.getDrawable(R.drawable.bg_commodity_selector_text,null)
                view.setTextColor(context.resources.getColor(R.color.color_000000,null))
            }
            var pxLr:Int = view.resources.getDimensionPixelOffset(R.dimen.commodity_mar)
            var pxLb:Int = view.resources.getDimensionPixelOffset(R.dimen.commodity_mar)
            view.setPadding(pxLr,pxLb,pxLr,pxLr)
            flowLayout.addView(view,index)
            view.setOnClickListener {
                flowItemClick?.onItemClick(it,holder.adapterPosition,holder)
            }
        }

    }

    interface FlowItemClick {
        fun onItemClick(view: View, position:Int,holder:BaseViewHolder)
    }

    fun setOnFlowItemClick(flowItemClick:FlowItemClick) {
        this.flowItemClick = flowItemClick
    }

}