package com.xt.garbage.adapter.garbage

import com.afollestad.materialdialogs.internal.main.BaseSubLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.DraggableModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xt.garbage.R
import com.xt.garbage.base.BaseConstant
import com.xt.garbage.bean.workmain.DriverBean
import com.xt.garbage.utils.ImageLoaderUtil
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 *@author:DIY
 *@date: 2021/5/18
 */
class DriverListVerticalAdapter(data: MutableList<DriverBean.ResultDTO>?) : BaseQuickAdapter<DriverBean.ResultDTO, BaseViewHolder>(layoutResId = R.layout.item_driverlistvertical,data),DraggableModule{


    override fun convert(holder: BaseViewHolder, item: DriverBean.ResultDTO) {
        holder.setText(R.id.name,item.nickName)
                .setText(R.id.phone,item.licensePlate)
                .setText(R.id.clean_tonnage,item.cleanTonnage)

        ImageLoaderUtil.loadCustRoundCircleImage(context,BaseConstant.URLPREFIX + item.head, holder.getView(R.id.head),RoundedCornersTransformation.CornerType.ALL,0);
    }
}