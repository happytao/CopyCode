package com.xt.garbage.ui.activity.work.appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xt.garbage.R;
import com.xt.garbage.base.BaseActivity;
import com.xt.garbage.constant.RoutePathConstant;

@Route(path = RoutePathConstant.WORK_APPOINTMENT_WAITER)
public class WaitOrderActivity extends BaseActivity {

    @Override
    protected int initLayout() {
        return R.layout.activity_wait_order;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}