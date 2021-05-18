package com.xt.garbage.ui.activity.login;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xt.garbage.R;
import com.xt.garbage.base.BaseActivity;
import com.xt.garbage.constant.RoutePathConstant;
import com.xt.garbage.utils.EditStatusCheck;
import com.xt.garbage.utils.TextUtils;
import com.xt.garbage.wigdt.IEditTextChangeListener;

import butterknife.BindView;
import butterknife.ButterKnife;
@Route(path = RoutePathConstant.APP_LOGIN_MAIN)
public class LoginMainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_psd_text)
    TextView mTitleRight;
    @BindView(R.id.et_phone)
    EditText mPhone;
    @BindView(R.id.btn_send)
    Button mSend;
    @BindView(R.id.tv_protocol)
    TextView tvProtocol;
    private boolean isChecked = false;


    @Override
    protected int initLayout() {
        return R.layout.activity_login_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mSend.setOnClickListener(this);
        mTitleRight.setOnClickListener(this);
        mSend.setEnabled(false);
        EditStatusCheck.TextChangeListener textChangeListener = new EditStatusCheck.TextChangeListener(mSend);
        textChangeListener.addAllEditText(mPhone);
        EditStatusCheck.setChangeListener(new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if(isHasContent) {
                    mSend.setBackgroundResource(R.drawable.bg_button_login_true);
                    mSend.setEnabled(true);
                }
                else {
                    mSend.setBackgroundResource(R.drawable.bg_button_login_false);
                }
            }
        });

        TextUtils.getBuilder().click("     同意《用户协议》", getResources().getColor(R.color.color_159ECC), new TextUtils.OnClickListener() {
            @Override
            public void onClick(int position) {
                switch (position) {
                    case 0 :
                        showToast("电话号码不能为空");
                }
            }
        },"《用户协议》").checkBox(this, tvProtocol, new TextUtils.OnImageClickListener() {
            @Override
            public void onChecked() {
                isChecked = true;
            }

            @Override
            public void onUnChecked() {
                isChecked = false;
            }
        }).clickInto(tvProtocol);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_psd_text :
                if(!isChecked) {
                    showToast("必须同意用户协议");
                    return;
                }
                ARouter.getInstance().build(RoutePathConstant.APP_PSD_LOGIN).navigation();
                break;
            default:
                break;
        }

    }



}