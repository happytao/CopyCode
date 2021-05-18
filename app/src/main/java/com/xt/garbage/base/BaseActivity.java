package com.xt.garbage.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.immersionbar.ImmersionBar;
import com.xt.garbage.R;
import com.xt.garbage.bean.constant.SpConstant;
import com.xt.garbage.bean.login.LoginBean;
import com.xt.garbage.collector.ActivityCollector;
import com.xt.garbage.utils.EventBusUtils;
import com.xt.garbage.utils.GsonUtils;
import com.xt.garbage.utils.SPUtils;
import com.xt.garbage.workmain.EventMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * @author: xt
 * @date: 2021/3/23
 * @description $
 */
public abstract class BaseActivity extends AppCompatActivity {
    //获取TAG的activity名称
    protected final String TAG = this.getClass().getSimpleName();

    //是否显示状态栏
    private boolean isShowStatusBar = true;

    //是否允许旋转屏幕
    private boolean isAllowScreenRotate = true;

    //封装toast
    private static Toast toast;

    public Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        //activity管理
        ActivityCollector.addActivity(this);
        ARouter.getInstance().inject(this);
        //设置activity无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //设置布局
        setContentView(initLayout());
        if(isRegisteredEventBus()) {
            EventBusUtils.register(this);
        }
        //初始化沉浸式
        initImmersionBar();

        //设置屏幕是否可旋转
        if(!isAllowScreenRotate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        ButterKnife.bind(this);

        initView();
        initData();

    }

    /**
     * 初始化布局
     * @return 布局id
     */
    protected abstract int initLayout();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 是否注册事件分发
     * @return true 注册；false 不注册，默认不注册
     */
    protected boolean isRegisteredEventBus() {
        return false;
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .navigationBarColor(R.color.white)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)
                .init();
    }

    /**
     * 设置是否显示状态栏
     * @param showStatusBar
     */
    public void setShowStatusBar(boolean showStatusBar){
        isShowStatusBar = showStatusBar;
    }

    /**
     * 设置是否允许旋转屏幕
     * @param allowScreenRotate
     */
    public void setAllowScreenRotate(boolean allowScreenRotate) {
        isAllowScreenRotate = allowScreenRotate;
    }

    /**
     * 1秒内只能点击一次
     */
    public abstract class OnSingleClickListener implements View.OnClickListener {
        private static final int MIN_CLICK_DELAY_TIME = 1000;
        private long lastClickTime;

        public abstract void onSingleClick(View view);

        @Override
        public void onClick(View view) {
            long curClickTime = System.currentTimeMillis();
            if((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
                lastClickTime = curClickTime;
                onSingleClick(view);
            }
        }
    }

    /**
     * 1s内多次点击事件
     */
    public abstract class OnMultiClickListener implements View.OnClickListener {
        public abstract void onMultiClick(View view);

        @Override
        public void onClick(View view) {
            onMultiClick(view);

        }
    }

    /**
     * 显示提示信息 toast
     * @param msg 提示信息
     */
    public void showToast(String msg) {
        try {
            if(toast == null) {
                toast = Toast.makeText(context,msg,Toast.LENGTH_SHORT);
            }
            else {
                toast.setText(msg);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    toast.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            //解决子线程中调用Toast异常情况处理
            Looper.prepare();
            Toast.makeText(context,msg,Toast.LENGTH_SHORT);
            Looper.loop();
        }
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if(getCurrentFocus() != null && imm != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
    }

    public void showSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if(getCurrentFocus() != null && imm != null) {
            imm.showSoftInputFromInputMethod(getCurrentFocus().getWindowToken(),0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isRegisteredEventBus()) {
            EventBusUtils.unRegister(this);
        }

        ActivityCollector.removeActivity(this);

    }

    /**
     * 接收分发的事件
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(EventMessage event) {
    }

    /**
     * 接收粘性分发事件
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onReceiveStickyEvent(EventMessage event) {
    }

    public LoginBean getLogin() {
        LoginBean loginBean = GsonUtils.fromJson((String) SPUtils.get(this, SpConstant.APP_LOGINBEAN,""),LoginBean.class);
        return loginBean;
    }
}
