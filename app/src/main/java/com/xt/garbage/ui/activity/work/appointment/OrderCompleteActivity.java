package com.xt.garbage.ui.activity.work.appointment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.telecom.Call;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.xt.garbage.R;
import com.xt.garbage.adapter.garbage.OrderSubscribeSuccessAdapter;
import com.xt.garbage.base.BaseActivity;
import com.xt.garbage.bean.workmain.OrderSiteDetailsBean;
import com.xt.garbage.constant.RoutePathConstant;
import com.xt.garbage.netSubscribe.garbage.GarbageSubscribe;
import com.xt.garbage.netapi.OnSuccessAndFaultListener;
import com.xt.garbage.netapi.OnSuccessAndFaultSub;
import com.xt.garbage.utils.GsonUtils;
import com.xt.garbage.utils.TextUtils;
import com.xt.garbage.wigdt.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = RoutePathConstant.WORK_APPOINTMENT_COMPLETE)
public class OrderCompleteActivity extends BaseActivity {
    @Autowired(name = RoutePathConstant.ORDER_ID)
    long id = 0;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private OrderSiteDetailsBean mOrderSiteDetailsBean;
    private OrderSubscribeSuccessAdapter mOrderSubscribeSuccessAdapter;
    private List<OrderSiteDetailsBean.ResultDTO.RecycleListDTO> mList = new ArrayList<>();
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private View headView;
    private View footView;
    private TextView mTotalPrice;
    private TextView mPayStatus;
    private TextView mGpayStatus;
    private TextView mCpayStatus;
    @BindView(R.id.user_name)
    TextView mName;
    @BindView(R.id.phone)
    TextView mPhone;
    @BindView(R.id.pay_status)
    TextView mPay;
    @BindView(R.id.site)
    TextView mSite;
    @BindView(R.id.create_time)
    TextView mCreateTime;
    @BindView(R.id.complete_time)
    TextView mCompleteTime;


    @Override
    protected int initLayout() {
        return R.layout.activity_order_complete;
    }

    @Override
    protected void initView() {
        headView = View.inflate(context,R.layout.item_subscribe_success_head,null);
        footView = View.inflate(context,R.layout.item_subscribe_success_foot,null);
        mTotalPrice = footView.findViewById(R.id.total_price);
        mPayStatus = headView.findViewById(R.id.pay_status);
        mGpayStatus = headView.findViewById(R.id.gpay_status);
        mCpayStatus = headView.findViewById(R.id.cpay_status);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);



    }

    @Override
    protected void initData() {
        mOrderSubscribeSuccessAdapter = new OrderSubscribeSuccessAdapter(mList);
        mOrderSubscribeSuccessAdapter.addHeaderView(headView);
        mOrderSubscribeSuccessAdapter.addFooterView(footView);
        mRecyclerView.setAdapter(mOrderSubscribeSuccessAdapter);
        getOrderInfo();

    }

    private void getOrderInfo() {
        GarbageSubscribe.getOrderSiteDetails(id,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                if(!result.isEmpty()) {
                    mOrderSiteDetailsBean = GsonUtils.fromJson(result,OrderSiteDetailsBean.class);
                    if(mOrderSiteDetailsBean.getErrorCode() == 0) {
                        mList.addAll(mOrderSiteDetailsBean.getResult().getRecycleList());
                        mOrderSubscribeSuccessAdapter.notifyDataSetChanged();
                        if(mOrderSiteDetailsBean.getResult().isSettleType()) {
                            setRmbFootText(mTotalPrice,"总价(元)：" + mOrderSiteDetailsBean.getResult().getSettleTotalCashPrice());
                            mPayStatus.setText("(元)");
                            mGpayStatus.setText("(元)");
                            mCpayStatus.setText("(元)");
                            setPhoneText(mPay,"支付方式： " + "现金");
                        }
                        else {
                            setFootText(mTotalPrice,"总价(积分)：" + mOrderSiteDetailsBean.getResult().getSettleTotalScorePrice());
                            mPayStatus.setText("(积分)");
                            mGpayStatus.setText("(积分)");
                            mCpayStatus.setText("(积分)");
                            setPhoneText(mPay,"支付方式： " + "积分");
                        }
                        if(mOrderSiteDetailsBean.getResult().getDetailAddress() == null) {
                            mSite.setVisibility(View.GONE);
                        }
                        if(mOrderSiteDetailsBean.getResult().getVisitTime() == null) {
                            mCreateTime.setVisibility(View.GONE);
                        }
                        mOrderSubscribeSuccessAdapter.setType(mOrderSiteDetailsBean.getResult().isSettleType());
                        setNameText(mName,"联系人：" + mOrderSiteDetailsBean.getResult().getContactName());
                        setPhoneText(mPhone,"联系方式：" + mOrderSiteDetailsBean.getResult().getContactMobile());
                        setPhoneText(mSite,"详细地址：" + mOrderSiteDetailsBean.getResult().getDetailAddress());
                        setPhoneText(mCreateTime,"预约时间：" + mOrderSiteDetailsBean.getResult().getVisitTime());
                        setPhoneText(mCompleteTime,"完成时间：" + mOrderSiteDetailsBean.getResult().getOrderFinishTime());
                    }
                }
            }

            @Override
            public void onFailed(String errorMsg) {
                showToast("请求失败：" + errorMsg );

            }
        }));
    }

    private void setRmbFootText(TextView textView, String text) {
        TextUtils.getBuilder().setSize(text,12,0,5)
                .setSize(18,6,text.length())
                .into(textView);
    }

    private void setPhoneText(TextView textView,String text) {
        TextUtils.getBuilder().setSize(text,14,0,4)
                .setColor(Color.parseColor("#CC000000"),5,text.length())
                .into(textView);
    }

    private void setFootText(TextView textView,String text) {
        TextUtils.getBuilder().setSize(text,12,0,6)
                .setSize(18,7,text.length())
                .into(textView);
    }

    private void setNameText(TextView textView,String text) {
        TextUtils.getBuilder().setSize(text,14,0,4)
                .setColor(Color.parseColor("#CC000000"),4,text.length())
                .into(textView);
    }
}