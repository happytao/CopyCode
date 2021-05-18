package com.xt.garbage.ui.fragment.workmain;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xt.garbage.R;
import com.xt.garbage.base.BaseFragment;
import com.xt.garbage.bean.constant.SpConstant;
import com.xt.garbage.bean.login.LoginBean;
import com.xt.garbage.bean.message.MessageListBean;
import com.xt.garbage.constant.RoutePathConstant;
import com.xt.garbage.netSubscribe.user.UserSubscribe;
import com.xt.garbage.netapi.OnSuccessAndFaultListener;
import com.xt.garbage.netapi.OnSuccessAndFaultSub;
import com.xt.garbage.utils.GsonUtils;
import com.xt.garbage.utils.SPUtils;
import com.xt.garbage.wigdt.Toolbar;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.

 */
public class MessageFragment extends BaseFragment implements View.OnClickListener{
    private ConstraintLayout mSysLayout;
    private TextView mSysTitle;
    private TextView mContent;
    private TextView mSysTime;
    private TextView mSysNumber;

    private ConstraintLayout mCleanLayout;
    private TextView mCleanTitle;
    private TextView mCleanContent;
    private TextView mCleanTime;
    private TextView mCleanNumber;

    private ConstraintLayout mAppointmentLayout;
    private TextView mAppointmentTittle;
    private TextView mAppointmentContent;
    private TextView mAppointmentTime;
    private TextView mAppointmentNumber;

    private ConstraintLayout mDeliverLayout;
    private TextView mDeliverTittle;
    private TextView mDeliverContent;
    private TextView mDeliverTime;
    private TextView mDeliverNumber;

    private MessageListBean messageListBean;
    private Toolbar mToolbar;

    private View mSysLine;
    private View mCleanLine;
    private View mAppointmentLine;
    private View mDeliverLine;




    @Override
    public int initLayout() {
        return R.layout.fragment_message;
    }

    @Override
    public void initView(View view) {
        mToolbar = view.findViewById(R.id.tb_toolbar);
        mSysLayout = view.findViewById(R.id.sys_layout);
        mSysTitle = view.findViewById(R.id.tittle);
        mContent = view.findViewById(R.id.content);
        mSysTime = view.findViewById(R.id.date);
        mSysNumber =view.findViewById(R.id.number);
        mSysLayout.setOnClickListener(this);

        mCleanLayout = view.findViewById(R.id.qy_layout);
        mCleanTitle = view.findViewById(R.id.qy_tittle);
        mCleanContent = view.findViewById(R.id.qy_content);
        mCleanTime = view.findViewById(R.id.qy_date);
        mCleanNumber = view.findViewById(R.id.qy_number);
        mCleanLayout.setOnClickListener(this);

        mAppointmentLayout = view.findViewById(R.id.yy_layout);
        mAppointmentTittle = view.findViewById(R.id.yy_tittle);
        mAppointmentContent = view.findViewById(R.id.yy_content);
        mAppointmentTime = view.findViewById(R.id.yy_date);
        mAppointmentNumber = view.findViewById(R.id.yy_number);
        mAppointmentLayout.setOnClickListener(this);

        mDeliverLayout = view.findViewById(R.id.sh_layout);
        mDeliverTittle = view.findViewById(R.id.sh_tittle);
        mDeliverContent = view.findViewById(R.id.sh_content);
        mDeliverTime = view.findViewById(R.id.sh_date);
        mDeliverNumber = view.findViewById(R.id.sh_number);
        mDeliverLayout.setOnClickListener(this);

        mSysLine = view.findViewById(R.id.sys_line);
        mCleanLine = view.findViewById(R.id.qy_line);
        mAppointmentLine = view.findViewById(R.id.yy_line);
        mDeliverLine = view.findViewById(R.id.sh_line);

    }

    @Override
    public void initData() {
        LoginBean loginBean = GsonUtils.fromJson((String) SPUtils.get(getActivity(), SpConstant.APP_LOGINBEAN,""),LoginBean.class);
        checkUserType(loginBean.getResult().getUserInfoRespDTO().getUserType());
        getData();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sys_layout:
                ARouter.getInstance().build(RoutePathConstant.APP_MESSAGE_LIST)
                        .withObject(RoutePathConstant.MESSAGE,messageListBean)
                        .withInt(RoutePathConstant.MESSAGE_TYPE,1)
                        .navigation();
                break;
            case R.id.qy_layout:
                ARouter.getInstance().build(RoutePathConstant.APP_MESSAGE_LIST)
                        .withObject(RoutePathConstant.MESSAGE,messageListBean)
                        .withInt(RoutePathConstant.MESSAGE_TYPE,2)
                        .navigation();
                break;
            case R.id.yy_layout:
                ARouter.getInstance().build(RoutePathConstant.APP_MESSAGE_LIST)
                        .withObject(RoutePathConstant.MESSAGE,messageListBean)
                        .withInt(RoutePathConstant.MESSAGE_TYPE,3)
                        .navigation();
                break;
            case R.id.sh_layout:
                ARouter.getInstance().build(RoutePathConstant.APP_MESSAGE_LIST)
                        .withObject(RoutePathConstant.MESSAGE,messageListBean)
                        .withInt(RoutePathConstant.MESSAGE_TYPE,4)
                        .navigation();
                break;
        }

    }

    private void checkUserType(int userType) {
        switch (userType) {
            case 4:
                mToolbar.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    private void getData() {
        UserSubscribe.getMessageList(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                if(!result.isEmpty()) {
                    messageListBean = GsonUtils.fromJson(result,MessageListBean.class);
                    if(messageListBean.getErrorCode() == 0) {
                        if(messageListBean.getResult() != null) {
                            if(messageListBean.getResult().getSysMessageList() != null) {
                                initSysMessage(messageListBean);
                            }
                            else {
                                setInvisible(mSysLayout,mSysLine);
                            }
                            if(messageListBean.getResult().getCleanMessageList() != null) {
                                initCleanMessage(messageListBean);
                            }
                            else {
                                setInvisible(mCleanLayout,mCleanLine);
                            }
                            if(messageListBean.getResult().getSubscribeMessageList() != null) {
                                initAppointmentMessage(messageListBean);
                            }
                            else {
                                setInvisible(mAppointmentLayout,mAppointmentLine);
                            }
                            if(messageListBean.getResult().getDeliveryMessageList() != null) {
                                initDeliverMessage(messageListBean);
                            }
                            else {
                                setInvisible(mDeliverLayout,mDeliverLine);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailed(String errorMsg) {
                showToast("请求失败" + errorMsg);

            }
        },getActivity()));
    }

    private void initSysMessage(MessageListBean messageListBean) {
        mSysTitle.setText(messageListBean.getResult().getSysMessageList().get(0).getMsgTitle());
        mContent.setText(messageListBean.getResult().getSysMessageList().get(0).getMsgContent());
        mSysTime.setText(messageListBean.getResult().getSysMessageList().get(0).getCreateTime());
        if(messageListBean.getResult().getUnreadSysMessage() > 0) {
            mSysNumber.setText(messageListBean.getResult().getUnreadSysMessage()+"");
        }
        else {
            setInvisible(mSysNumber);
        }

    }

    private void initCleanMessage(MessageListBean messageListBean) {
        mCleanTitle.setText(messageListBean.getResult().getCleanMessageList().get(0).getMsgTitle());
        mCleanContent.setText(messageListBean.getResult().getCleanMessageList().get(0).getMsgContent());
        mCleanTime.setText(messageListBean.getResult().getCleanMessageList().get(0).getCreateTime());
        if(messageListBean.getResult().getUnreadCleanMessage() > 0) {
            mCleanNumber.setText(messageListBean.getResult().getUnreadCleanMessage()+"");
        }
        else {
            setInvisible(mCleanNumber);
        }

    }

    private void initAppointmentMessage(MessageListBean messageListBean) {
        mAppointmentTittle.setText(messageListBean.getResult().getSubscribeMessageList().get(0).getMsgTitle());
        mAppointmentContent.setText(messageListBean.getResult().getSubscribeMessageList().get(0).getMsgContent());
        mAppointmentTime.setText(messageListBean.getResult().getSubscribeMessageList().get(0).getCreateTime());
        if(messageListBean.getResult().getUnreadSubscribeMessage() > 0) {
            mAppointmentNumber.setText(messageListBean.getResult().getUnreadSubscribeMessage()+"");
        }
        else {
            setInvisible(mAppointmentNumber);
        }

    }

    private void initDeliverMessage(MessageListBean messageListBean) {
        mDeliverTittle.setText(messageListBean.getResult().getDeliveryMessageList().get(0).getMsgTitle());
        mDeliverContent.setText(messageListBean.getResult().getDeliveryMessageList().get(0).getMsgContent());
        mDeliverTime.setText(messageListBean.getResult().getDeliveryMessageList().get(0).getCreateTime());
        if(messageListBean.getResult().getUnreadDeliveryMessage() > 0) {
            mDeliverNumber.setText(messageListBean.getResult().getUnreadDeliveryMessage()+"");
        }
        else {
            setInvisible(mDeliverNumber);
        }
    }

    private void setInvisible(View... views) {
        for (View view : views) {
            view.setVisibility(View.GONE);
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            getData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }
}