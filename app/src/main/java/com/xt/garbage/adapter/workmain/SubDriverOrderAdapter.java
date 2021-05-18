package com.xt.garbage.adapter.workmain;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xt.garbage.R;
import com.xt.garbage.base.BaseConstant;
import com.xt.garbage.bean.workmain.SubDriverOrderListBean;
import com.xt.garbage.utils.ImageLoaderUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author:DIY
 * @date: 2021/4/3
 */
public class SubDriverOrderAdapter extends BaseQuickAdapter<SubDriverOrderListBean.ResultDTO, BaseViewHolder> {
    public SubDriverOrderAdapter(@Nullable List<SubDriverOrderListBean.ResultDTO> data) {
        super(R.layout.item_sub_driver_order, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, SubDriverOrderListBean.ResultDTO resultDTO) {
        TextView type = baseViewHolder.findView(R.id.type);
        baseViewHolder.setText(R.id.name,resultDTO.getMotormanResp().getNickName())
                .setText(R.id.phone,resultDTO.getMotormanResp().getLicensePlate())
                .setText(R.id.clean_tonnage,resultDTO.getMotormanResp().getCleanTonnage())
                .setText(R.id.number,"订单编号:" + resultDTO.getId())
                .setText(R.id.time,"下单时间：" + resultDTO.getCreateTime());

        ImageLoaderUtil.loadCircleImage(getContext(), BaseConstant.URLPREFIX + resultDTO.getMotormanResp().getHead(),baseViewHolder.getView(R.id.head));
        switch (resultDTO.getOrderStatus()) {
            case 1:
                type.setText("待接单");
                break;
            case 2:
                type.setText("已接单");
                break;
            case 3:
                type.setText("已到达");
                break;
            case 4:
                type.setText("待确认");
                break;
            case 5:
                type.setText("已完成");
                break;
            case 6:
                type.setText("已取消");
                break;
            case 7:
                type.setText("已取消");
                break;
            case 8:
                type.setText("已取消");
                break;
            case 9:
                type.setText("已取消");
                break;
            case 10:
                type.setText("已取消");
                break;
        }

    }
}
