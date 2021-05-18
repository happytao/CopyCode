package com.xt.garbage.ui.activity.message;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.xt.garbage.R;
import com.xt.garbage.base.BaseActivity;
import com.xt.garbage.bean.message.MessageListBean;
import com.xt.garbage.constant.RoutePathConstant;

@Route(path = RoutePathConstant.APP_MESSAGE_LIST)
public class MessageListActivity extends BaseActivity {
    @Autowired(name = RoutePathConstant.MESSAGE)
    MessageListBean messageListBean;
    @Autowired(name = RoutePathConstant.MESSAGE_TYPE)
    int messageType = 0;

    @Override
    protected int initLayout() {
        return R.layout.activity_message_list;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}