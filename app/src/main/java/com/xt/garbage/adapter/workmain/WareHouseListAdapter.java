package com.xt.garbage.adapter.workmain;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xt.garbage.R;
import com.xt.garbage.bean.workmain.WareHouseListBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author:DIY
 * @date: 2021/4/7
 */
public class WareHouseListAdapter extends BaseQuickAdapter<WareHouseListBean.ResultDTO, BaseViewHolder> {
    public WareHouseListAdapter(@Nullable List<WareHouseListBean.ResultDTO> data) {
        super(R.layout.item_warehouse_list, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, WareHouseListBean.ResultDTO resultDTO) {
        baseViewHolder.setText(R.id.name,resultDTO.getGarbageCategoryName() + "/" + resultDTO.getSurplusWeightNumber() + "kg");

    }
}
