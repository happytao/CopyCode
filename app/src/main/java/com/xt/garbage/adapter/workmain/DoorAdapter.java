package com.xt.garbage.adapter.workmain;

import android.app.Presentation;
import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xt.garbage.R;
import com.xt.garbage.bean.workmain.CleanOrderDetailsBean;
import com.xt.garbage.utils.TextUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author:DIY
 * @date: 2021/4/8
 */
public class DoorAdapter extends BaseQuickAdapter<CleanOrderDetailsBean.ResultDTO.RecycleListDTO, BaseViewHolder> {
    public DoorAdapter(@Nullable List<CleanOrderDetailsBean.ResultDTO.RecycleListDTO> data) {
        super(R.layout.item_door, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, CleanOrderDetailsBean.ResultDTO.RecycleListDTO recycleListDTO) {
        baseViewHolder.setText(R.id.name, recycleListDTO.getGarbageCategoryName());
        TextView sys_money = baseViewHolder.getView(R.id.sys_money);
        TextView money = baseViewHolder.findView(R.id.money);
        TextView sys_kg = baseViewHolder.findView(R.id.sys_kg);
        TextView kg = baseViewHolder.findView(R.id.kg);
        TextView tol_money = baseViewHolder.findView(R.id.tol_money);
        TextView type = baseViewHolder.findView(R.id.type);
        if(recycleListDTO.isAll())  {
            type.setText("全部");
            type.setTextColor(Color.parseColor("#CC000000"));
        }
        else {
            type.setText("部分");
            type.setTextColor(Color.parseColor("#CC00BF60"));
        }
        setPhoneText(sys_money,"建议回收价 " + recycleListDTO.getCategoryCashPrice() + "元/KG");
        setKGText(money,"现场议价 " + recycleListDTO.getBargainCashPrice() + "元/KG");
        setPhoneText(sys_kg,"称重后重量 " + recycleListDTO.getBargainWeightNumber() + "KG");
        setKGText(kg,"库存重量 " + recycleListDTO.getRecyleWeightNumber() + "KG");
        setTotalText(tol_money,"总价(元):" + recycleListDTO.getRecylePrice());


    }

    private void setPhoneText(TextView textView,String text) {
        TextUtils.getBuilder()
                .setSize(text,14,0,5)
                .setColor(Color.parseColor("#CC000000"),6,text.length())
                .into(textView);
    }

    private void setKGText(TextView textView,String text) {
        TextUtils.getBuilder()
                .setSize(text,14,0,4)
                .setColor(Color.parseColor("#CC000000"),5,text.length())
                .into(textView);
    }

    private void setTotalText(TextView textView,String text) {
        TextUtils.getBuilder()
                .setSize(text,14,0,5)
                .setSize(18,6,text.length())
                .into(textView);
    }
}
