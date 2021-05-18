package com.xt.garbage.adapter.workmain;

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
 * @date: 2021/4/10
 */
public class CleanOrderAdapter extends BaseQuickAdapter<CleanOrderDetailsBean.ResultDTO.RecycleListDTO, BaseViewHolder> {
    private List<CleanOrderDetailsBean.ResultDTO.RecycleListDTO> mList;
    public CleanOrderAdapter(@Nullable List<CleanOrderDetailsBean.ResultDTO.RecycleListDTO> data) {
        super(R.layout.item_sub_reclear, data);
        mList = data;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, CleanOrderDetailsBean.ResultDTO.RecycleListDTO recycleListDTO) {
        baseViewHolder.setText(R.id.name,recycleListDTO.getGarbageCategoryName())
                .setText(R.id.kg,Double.toString(recycleListDTO.getRecyleWeightNumber()));

    }

}
