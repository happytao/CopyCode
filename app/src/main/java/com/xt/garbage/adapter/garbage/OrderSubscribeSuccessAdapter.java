package com.xt.garbage.adapter.garbage;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xt.garbage.R;
import com.xt.garbage.bean.workmain.OrderSiteDetailsBean;
import com.xt.garbage.netapi.OnSuccessAndFaultListener;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author:DIY
 * @date: 2021/4/5
 */
public class OrderSubscribeSuccessAdapter extends BaseQuickAdapter<OrderSiteDetailsBean.ResultDTO.RecycleListDTO, BaseViewHolder> {
    private boolean settly;
    public OrderSubscribeSuccessAdapter(@Nullable List<OrderSiteDetailsBean.ResultDTO.RecycleListDTO> data) {
        super(R.layout.item_appointment_complete, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, OrderSiteDetailsBean.ResultDTO.RecycleListDTO recycleListDTO) {
        TextView totalPrice = baseViewHolder.findView(R.id.total_price);
        TextView publicPrice = baseViewHolder.findView(R.id.g_pay);
        TextView number = baseViewHolder.findView(R.id.number);
        baseViewHolder.setText(R.id.name,recycleListDTO.getGarbageCategoryName())
                .setText(R.id.kg,Double.toString(recycleListDTO.getWeightNumber()));
        if(settly) {
            number.setText(recycleListDTO.getSellCashPrice() + "");
            totalPrice.setText(recycleListDTO.getTotalCashPrice() + "");
            publicPrice.setText(recycleListDTO.getPublicCashPrice() + "");
        }
        else {
            number.setText(recycleListDTO.getSellScorePrice() + "");
            totalPrice.setText(recycleListDTO.getTotalScorePrice() + "");
            publicPrice.setText(recycleListDTO.getPublicScorePrice() + "");
        }
    }

    public void setType(boolean settly) {
        this.settly = settly;
        notifyDataSetChanged();
    }

}
