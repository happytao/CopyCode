package com.xt.garbage.ui.activity.work.shsm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xt.garbage.R;
import com.xt.garbage.base.BaseActivity;
import com.xt.garbage.constant.RoutePathConstant;

@Route(path = RoutePathConstant.SITE_SHSMORDERDETAILS)
public class ShsmOrderDetailsActivity extends BaseActivity {


    @Override
    protected int initLayout() {
        return R.layout.activity_shsm_order_details;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}