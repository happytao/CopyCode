package com.xt.garbage.adapter.workmain;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xt.garbage.R;
import com.xt.garbage.bean.shop.OrderListBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import butterknife.BindView;

/**
 * @author:DIY
 * @date: 2021/3/27
 */
public class ShsmOrderAdapter extends BaseQuickAdapter<OrderListBean.ResultDTO, BaseViewHolder> {
    public ShsmOrderAdapter(@Nullable List<OrderListBean.ResultDTO> data) {
        super(R.layout.item_providehome, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, OrderListBean.ResultDTO resultDTO) {
        TextView site = baseViewHolder.getView(R.id.tv_site);
        ImageView icon = baseViewHolder.getView(R.id.iv_icon);
        TextView address = baseViewHolder.getView(R.id.tv_address);
        baseViewHolder.setText(R.id.tv_orderId,"订单编号" + resultDTO.getId());
        if(resultDTO.getAddressResp() == null) {
            site.setText("门店自提");
            Glide.with(getContext()).load(R.drawable.icon_yysm_list).into(icon);
            address.setVisibility(View.GONE);
        }
        else {
            site.setText(resultDTO.getAddressResp().getDetailAddress());
            address.setText(resultDTO.getAddressResp().getName());
        }

        TextView type = baseViewHolder.getView(R.id.tv_type);
        switch (resultDTO.getOrderStatus()) {
            case 3:
                if(resultDTO.getReceiveStatus() == 2) {
                    type.setText("进行中");
                    type.setTextColor(Color.parseColor("#00BF60"));
                }
                break;
            case 4:
                type.setText("已取消");
                type.setTextColor(Color.parseColor("#CC000000"));
                break;
            case 5:
                type.setText("已完成");
                type.setTextColor(Color.parseColor("#CC000000"));
                break;
            case 6:
                type.setText("取消预约");
                type.setTextColor(Color.parseColor("#CC000000"));
                break;
            case 7:
                type.setText("已拒绝");
                type.setTextColor(Color.parseColor("#CC000000"));
                break;
            case 8:
                type.setText("超时取消");
                type.setTextColor(Color.parseColor("#CC000000"));
                break;
            case 9:
                type.setText("用户已取消");
                type.setTextColor(Color.parseColor("#CC000000"));
                break;
            case 10:
                type.setText("司机已取消");
                type.setTextColor(Color.parseColor("#CC000000"));
                break;
        }

    }
}
