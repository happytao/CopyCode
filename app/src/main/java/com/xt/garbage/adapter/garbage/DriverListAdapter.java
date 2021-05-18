package com.xt.garbage.adapter.garbage;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.DraggableModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xt.garbage.R;
import com.xt.garbage.base.BaseConstant;
import com.xt.garbage.bean.workmain.DriverBean;
import com.xt.garbage.utils.ImageLoaderUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author:DIY
 * @date: 2021/3/31
 */
public class DriverListAdapter extends BaseQuickAdapter<DriverBean.ResultDTO, BaseViewHolder> implements DraggableModule {
    public DriverListAdapter(@Nullable List<DriverBean.ResultDTO> data) {
        super(R.layout.item_driver, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, DriverBean.ResultDTO resultDTO) {

        baseViewHolder.setText(R.id.name,resultDTO.getNickName())
                .setText(R.id.phone,resultDTO.getMobile())
                .setText(R.id.clean_tonnage,resultDTO.getCleanTonnage());
        ImageLoaderUtil.loadCircleImage(getContext(), BaseConstant.URLPREFIX+resultDTO.getHead(),baseViewHolder.getView(R.id.head));
        int screenWidth = getScreenWidth(getContext());
        ConstraintLayout constraintLayout = baseViewHolder.getView(R.id.layout);
        ViewGroup.LayoutParams params = constraintLayout.getLayoutParams();
        params.width = (int) (screenWidth / 1.5);
        constraintLayout.setLayoutParams(params);



    }

    public static int getScreenWidth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
