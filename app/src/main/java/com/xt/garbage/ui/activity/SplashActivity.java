package com.xt.garbage.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xt.garbage.R;
import com.xt.garbage.base.BaseActivity;
import com.xt.garbage.bean.login.LoginBean;
import com.xt.garbage.collector.ActivityCollector;
import com.xt.garbage.constant.RoutePathConstant;

public class SplashActivity extends BaseActivity {


    @Override
    protected int initLayout() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        LoginBean loginBean = getLogin();
        if(loginBean == null) {
            goWorkMain();
        }
        else {
            checkoutLogin(loginBean.getResult().getUserInfoRespDTO().getUserType());

        }

    }

    private void goWorkMain() {
        ARouter.getInstance().build(RoutePathConstant.APP_WORKMAIN).navigation();
        ActivityCollector.finishAll();
    }

    private void checkoutLogin(int userType) {
        switch (userType) {
            case 4:
                goWorkMain();
                break;

            case 1:
            case 2:
            case 3:
                goMain();
                break;
            default:
                break;
        }
    }

    private void goMain() {
        ARouter.getInstance().build(RoutePathConstant.APP_MAIN).navigation();
        ActivityCollector.finishAll();
    }
}