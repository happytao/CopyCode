package com.xt.garbage.adapter.shop;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xt.garbage.R;
import com.xt.garbage.base.BaseConstant;
import com.xt.garbage.bean.shop.BatchDetailsBean;
import com.xt.garbage.utils.ImageLoaderUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.regex.Pattern;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @author:DIY
 * @date: 2021/4/5
 */
public class OrderDetailsShopAdapter extends BaseQuickAdapter<BatchDetailsBean.ResultDTO.ChildOrderRespListDTO, BaseViewHolder> {


    public OrderDetailsShopAdapter(@Nullable List<BatchDetailsBean.ResultDTO.ChildOrderRespListDTO> data) {
        super(R.layout.item_order_details_shop, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, BatchDetailsBean.ResultDTO.ChildOrderRespListDTO childOrderRespListDTO) {
        TextView size = baseViewHolder.findView(R.id.size);
        String spe = "";
        if(childOrderRespListDTO.getOrderSpecList() != null) {
            if(childOrderRespListDTO.getOrderSpecList().size() >= 1) {
                for (int i = 0; i < childOrderRespListDTO.getOrderSpecList().size(); i++) {
                    spe += childOrderRespListDTO.getOrderSpecList().get(i).getRefSpecName() + ":"
                            + childOrderRespListDTO.getOrderSpecList().get(i).getRefAttrName() + " ";

                }
            }
        }
        TextView money = baseViewHolder.findView(R.id.money);
        size.setText(spe);
        baseViewHolder.setText(R.id.name,childOrderRespListDTO.getRefGoodsName())
                .setText(R.id.number,"X" + childOrderRespListDTO.getBuyNum());
        ImageLoaderUtil.loadCustRoundCircleImage(getContext(), BaseConstant.URLPREFIX + childOrderRespListDTO.getRefGoodsResourceUrl(),
                baseViewHolder.getView(R.id.logo), RoundedCornersTransformation.CornerType.ALL,8);
        switch (childOrderRespListDTO.getGoodsSellType()) {
            //积分
            case 1:
                money.setText(childOrderRespListDTO.getRefGoodsScorePrice() + "积分");
                break;
            //现金
            case 2:
                money.setText(childOrderRespListDTO.getRefGoodsCashPrice() + "元");
                break;
            case 3:
                money.setText(childOrderRespListDTO.getRefGoodsCashPrice() + "元+" + childOrderRespListDTO.getRefGoodsScorePrice() + "积分");
                break;
        }

    }
}
