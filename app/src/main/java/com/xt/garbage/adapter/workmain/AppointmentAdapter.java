package com.xt.garbage.adapter.workmain;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xt.garbage.R;
import com.xt.garbage.bean.workmain.AppointmentResultBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author:DIY
 * @date: 2021/3/29
 */
public class AppointmentAdapter extends BaseQuickAdapter<AppointmentResultBean.ResultDTO, BaseViewHolder> {
    public AppointmentAdapter(@Nullable List<AppointmentResultBean.ResultDTO> data) {
        super(R.layout.item_appointment, data);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, AppointmentResultBean.ResultDTO resultDTO) {
        ImageView icon = baseViewHolder.getView(R.id.iv_icon);
        baseViewHolder.setText(R.id.tv_order_id,"订单编号" + resultDTO.getId())
                .setText(R.id.tv_site,resultDTO.getDetailAddress())
                .setText(R.id.tv_name,resultDTO.getContactName())
                .setText(R.id.tv_time,resultDTO.getVisitTime());

        if(resultDTO.getDetailAddress() == null) {
            baseViewHolder.setText(R.id.tv_site,"现场投放")
                    .setText(R.id.tv_time,"投放事件" + resultDTO.getOrderFinishTime());
            Glide.with(getContext()).load(R.drawable.icon_yysm_list).into(icon);
        }
        TextView type = baseViewHolder.getView(R.id.tv_type);
        switch (resultDTO.getOrderStatus()) {
            case 1:
                type.setText("待接单");
                type.setTextColor(Color.parseColor("#FF0E3A"));
                break;
            case 2:
                type.setText("待上门");
                type.setTextColor(Color.parseColor("#00BF60"));
                break;
            case 3:
                type.setText("已到达");
                type.setTextColor(Color.parseColor("#00BF60"));
                break;
            case 4:
                type.setText("已完成");
                type.setTextColor(Color.parseColor("#CC000000"));
                break;
            case 5:
                type.setText("交易取消");
                type.setTextColor(Color.parseColor("#CC000000"));
                break;
            case 6:
                type.setText("已拒绝");
                type.setTextColor(Color.parseColor("#CC000000"));
                break;
            case 8:
                type.setText("用户已取消");
                type.setTextColor(Color.parseColor("#CC000000"));
                break;
        }

    }
}
