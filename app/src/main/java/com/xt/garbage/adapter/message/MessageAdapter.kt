package com.xt.garbage.adapter.message

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.DraggableModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xt.garbage.R
import com.xt.garbage.bean.message.MessageListBean

/**
 *@author:DIY
 *@date: 2021/5/19
 */
class MessageAdapter(data: MutableList<MessageListBean.ResultDTO.CleanMessageListDTO>?) : BaseQuickAdapter<MessageListBean.ResultDTO.CleanMessageListDTO, BaseViewHolder>(layoutResId = R.layout.item_message, data),DraggableModule {
    override fun convert(holder: BaseViewHolder, item: MessageListBean.ResultDTO.CleanMessageListDTO) {
        holder.setText(R.id.tittle,item.msgTitle)
                .setText(R.id.content,item.msgContent)
                .setText(R.id.date,item.createTime)

        var icon = holder.getView<ImageView>(R.id.icon)
        var red = holder.getView<TextView>(R.id.number)

        when(item.msgType){
            0 -> {
                Glide.with(context).load(R.drawable.icon_sys_message).into(icon)
            }
            1 -> {
                Glide.with(context).load(R.drawable.home_goto_icon).into(icon)
            }
            2 -> {
                Glide.with(context).load(R.drawable.home_item_four).into(icon)
            }
            3 -> {
                Glide.with(context).load(R.drawable.home_item_three).into(icon)
            }
            else -> {
            }
        }
        if(item.isRead) {
            red.visibility = View.GONE
        }
        else {
            red.visibility = View.VISIBLE
        }
    }
}