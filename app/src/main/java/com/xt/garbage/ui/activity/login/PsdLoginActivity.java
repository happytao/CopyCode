package com.xt.garbage.ui.activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.xt.garbage.R;
import com.xt.garbage.base.BaseActivity;
import com.xt.garbage.base.BaseConstant;
import com.xt.garbage.bean.constant.SpConstant;
import com.xt.garbage.bean.login.LoginBean;
import com.xt.garbage.collector.ActivityCollector;
import com.xt.garbage.constant.RoutePathConstant;
import com.xt.garbage.netSubscribe.login.LoginSubscribe;
import com.xt.garbage.netapi.OnSuccessAndFaultListener;
import com.xt.garbage.netapi.OnSuccessAndFaultSub;
import com.xt.garbage.utils.EditStatusCheck;
import com.xt.garbage.utils.GsonUtils;
import com.xt.garbage.utils.PhoneUtils;
import com.xt.garbage.utils.SPUtils;
import com.xt.garbage.wigdt.IEditTextChangeListener;
import com.xt.garbage.wigdt.Toolbar;

import butterknife.BindView;

@Route(path = RoutePathConstant.APP_PSD_LOGIN)
public class PsdLoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_phone)
    EditText mPhone;
    @BindView(R.id.et_psd)
    EditText mPsd;
    @BindView(R.id.btn_logon)
    Button mBtnLogin;





    @Override
    protected int initLayout() {
        return R.layout.activity_psd_login;
    }

    @Override
    protected void initView() {
        mToolbar.setOnToolbarOnClickListener(new Toolbar.ToolbarClickListener() {
            @Override
            public void leftClick() {
                finish();

            }

            @Override
            public void rightClick() {
                finish();

            }
        });

        mBtnLogin.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        mBtnLogin.setEnabled(false);
        EditStatusCheck.TextChangeListener textChangeListener = new EditStatusCheck.TextChangeListener(mBtnLogin);
        textChangeListener.addAllEditText(mPhone,mPsd);
        EditStatusCheck.setChangeListener(new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    mBtnLogin.setBackgroundResource(R.drawable.bg_button_login_true);
                    mBtnLogin.setEnabled(true);
                } else {
                    mBtnLogin.setBackgroundResource(R.drawable.bg_button_login_false);
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logon:
                if(mPhone.getText().toString().isEmpty()) {
                    showToast("电话号码不能为空");
                    return;
                }
                if(mPsd.getText().toString().isEmpty()) {
                    showToast("密码不能为空");
                    return;
                }
                if(!PhoneUtils.isPhoneNumber(mPhone.getText().toString())) {
                    showToast("请输入正确的手机号码");
                    return;
                }
                login();
                break;
        }

    }

    private void login() {
        LoginSubscribe.login("","1",mPhone.getText().toString(),mPsd.getText().toString(),"","2",new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                if(!result.isEmpty()) {
                    LoginBean loginBean = GsonUtils.fromJson(result,LoginBean.class);
                    if(loginBean.getErrorCode() == 0) {
                        SPUtils.put(PsdLoginActivity.this, SpConstant.APP_LOGINBEAN,result);
                        SPUtils.put(PsdLoginActivity.this,SpConstant.APP_TOKEN,loginBean.getResult().getToken());
                        SPUtils.put(PsdLoginActivity.this,SpConstant.IS_LOGIN,true);
                        BaseConstant.TOKEN = loginBean.getResult().getToken();
                        BaseConstant.URLPREFIX = loginBean.getResult().getUrlPrefix();
                        SPUtils.put(PsdLoginActivity.this,SpConstant.URLPREFIX,loginBean.getResult().getUrlPrefix());
                        CheckoutLogin(loginBean.getResult().getUserInfoRespDTO().getUserType());
                    }
                    else {
                        showToast(loginBean.getErrorMsg());
                    }
                }
            }

            @Override
            public void onFailed(String errorMsg) {
                showToast("请求失败" + errorMsg);

            }
        }));

    }

    private void CheckoutLogin(int userType) {
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
        ARouter.getInstance().build(RoutePathConstant.APP_MAIN)
                .navigation();
//        ActivityCollector.finishAll();
    }

    private void goWorkMain() {
        ARouter.getInstance().build(RoutePathConstant.APP_WORKMAIN).navigation();
//        ActivityCollector.finishAll();

    }
}