package com.xt.garbage.ui.fragment.workmain;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.callbacks.DialogCallbackExtKt;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.dialog.MaterialDialogs;
import com.xt.garbage.R;
import com.xt.garbage.base.BaseConstant;
import com.xt.garbage.base.BaseFragment;
import com.xt.garbage.bean.constant.SpConstant;
import com.xt.garbage.bean.login.LoginBean;
import com.xt.garbage.constant.RoutePathConstant;
import com.xt.garbage.utils.GsonUtils;
import com.xt.garbage.utils.ImageLoaderUtil;
import com.xt.garbage.utils.SPUtils;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/**
 * A simple {@link Fragment} subclass.

 */
public class WorkUserFragment extends BaseFragment implements View.OnClickListener{
    private ImageView mHeadImage;
    private TextView mNickname,mPhone;
    private RelativeLayout mFeedback,mOut,mAbout,mPrivate,mHeadLayout,mPsdLayout;

    @Override
    public int initLayout() {
        return R.layout.fragment_work_user;
    }

    @Override
    public void initView(View view) {
        mHeadImage = view.findViewById(R.id.head_image);
        mNickname = view.findViewById(R.id.nickname);
        mPhone = view.findViewById(R.id.phone);
        mFeedback = view.findViewById(R.id.feedback_layout);
        mOut = view.findViewById(R.id.out_layout);
        mAbout = view.findViewById(R.id.about_layout);
        mPrivate = view.findViewById(R.id.pri_layout);
        mHeadLayout = view.findViewById(R.id.head_layout);
        mPsdLayout = view.findViewById(R.id.psd_layout);
        mPsdLayout.setOnClickListener(this);
        mHeadLayout.setOnClickListener(this);
        mPrivate.setOnClickListener(this);
        mAbout.setOnClickListener(this);
        mOut.setOnClickListener(this);
        mFeedback.setOnClickListener(this);

    }

    @Override
    public void initData() {
        initUserInfo();

    }

    private void initUserInfo() {
        LoginBean loginBean = GsonUtils.fromJson((String) SPUtils.get(getContext(), SpConstant.APP_LOGINBEAN,""),LoginBean.class);
        ImageLoaderUtil.loadCircleImage(getContext(), BaseConstant.URLPREFIX + loginBean.getResult().getUserInfoRespDTO().getHead(),mHeadImage);
        mNickname.setText(loginBean.getResult().getUserInfoRespDTO().getNickName());
        mPhone.setText(loginBean.getResult().getUserInfoRespDTO().getMobile());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.feedback_layout:
                break;
            case R.id.out_layout:
                showDialog();
                break;
            case R.id.about_layout:
                break;
            case R.id.pri_layout:
                break;
            case R.id.head_layout:
                break;
            case R.id.psd_layout:
                break;
        }

    }

    private void showDialog() {
        MaterialDialog dialog = new MaterialDialog(getActivity(),MaterialDialog.getDEFAULT_BEHAVIOR());
        dialog.title(R.string.dialog_title,null);
        dialog.message(R.string.dialog_logout_confirm,null,null);
        dialog.positiveButton(R.string.dialog_right_button, null, new Function1<MaterialDialog, Unit>() {
            @Override
            public Unit invoke(MaterialDialog materialDialog) {
                CleanLogin();
                return null;
            }
        });
        dialog.negativeButton(R.string.dialog_left_button,null,materialDialog -> {
            dialog.dismiss();
            return null;
        });
        dialog.show();
    }

    /**
     * 清楚登录缓存
     */
    private void CleanLogin() {
        SPUtils.clear(getActivity());
        ARouter.getInstance().build(RoutePathConstant.APP_LOGIN_MAIN).navigation();
        showToast("退出成功");
        getActivity().finish();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            initUserInfo();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initUserInfo();
    }
}