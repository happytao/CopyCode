package com.xt.garbage.ui.activity.work.clean;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xt.garbage.R;
import com.xt.garbage.base.BaseActivity;
import com.xt.garbage.constant.RoutePathConstant;

@Route(path = RoutePathConstant.WORK_DRIVER_LIST)
public class DriverListActivity extends BaseActivity {

    @Override
    protected int initLayout() {
        return R.layout.activity_driver_list;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}