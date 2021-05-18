package com.xt.garbage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.immersionbar.ImmersionBar;
import com.xt.garbage.base.BaseActivity;
import com.xt.garbage.base.BaseApplication;
import com.xt.garbage.base.BaseConstant;
import com.xt.garbage.base.BaseFragment;
import com.xt.garbage.bean.constant.SpConstant;
import com.xt.garbage.constant.RoutePathConstant;
import com.xt.garbage.ui.fragment.workmain.MessageFragment;
import com.xt.garbage.ui.fragment.workmain.WorkHomeFragment;
import com.xt.garbage.ui.fragment.workmain.WorkUserFragment;
import com.xt.garbage.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = RoutePathConstant.APP_WORKMAIN,extras = SpConstant.LOGIN_NEEDED)
public class WorkHomeActivity extends BaseActivity {
    private List<BaseFragment> mBaseFragments;
    //Fragment当前位置
    private int position;
    //上次切换的fragment
    private Fragment mContent;
    @BindView(R.id.rg_main)
    RadioGroup mRgMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setShowStatusBar(false);
        super.onCreate(savedInstanceState);
        //获取本地token
        BaseConstant.TOKEN = (String) SPUtils.get(WorkHomeActivity.this,SpConstant.APP_TOKEN,"");
        //获取图片前缀
        BaseConstant.URLPREFIX = (String) SPUtils.get(WorkHomeActivity.this,SpConstant.URLPREFIX,"");
        //初始化fragment
        initFragment();
        //设置RadioGroup的监听
        setListener();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_work_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    private void initFragment() {
        mBaseFragments = new ArrayList<>();
        mBaseFragments.add(new WorkHomeFragment());
        mBaseFragments.add(new MessageFragment());
        mBaseFragments.add(new WorkUserFragment());

    }

    private void setListener() {
        mRgMain.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        mRgMain.check(R.id.rb_common_frame);

    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_common_frame:
                    position = 0;
                    break;
                case R.id.rb_third_party:
                    position = 1;
                    break;
                case R.id.rb_user:
                    position = 2;
                    break;
            }
            BaseFragment to = getFragment();
            switchFragment(mContent,to);
        }
    }

    private BaseFragment getFragment() {
        BaseFragment fragment = mBaseFragments.get(position);
        return fragment;

    }

    private void switchFragment(Fragment from, Fragment to) {
        if(from != to) {
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if(!to.isAdded()) {
                if(from != null) {
                    ft.hide(from);
                }
                if(to != null) {
                    ft.add(R.id.fl_content,to).commit();
                }
            }
            else {
                if(from != null) {
                    ft.hide(from);
                }
                if(to != null) {
                    ft.show(to).commit();
                }
            }
        }
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .navigationBarColor(R.color.white)
                .statusBarColor(R.color.color_32394D)
                .statusBarDarkFont(false)
                .fitsSystemWindows(true)
                .init();
    }
}