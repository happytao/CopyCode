package com.xt.garbage.ui.fragment.workmain;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xt.garbage.R;
import com.xt.garbage.adapter.workmain.ShsmOrderAdapter;
import com.xt.garbage.base.BaseFragment;
import com.xt.garbage.bean.shop.OrderListBean;
import com.xt.garbage.constant.RoutePathConstant;
import com.xt.garbage.netSubscribe.shop.ShopSubscribe;
import com.xt.garbage.netapi.OnSuccessAndFaultListener;
import com.xt.garbage.netapi.OnSuccessAndFaultSub;
import com.xt.garbage.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kotlin.jvm.internal.PropertyReference0Impl;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProvideHomeDeliveryServiceFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    private RecyclerView mRecycleView;
    private String orderStatus = "";
    private String receiveStatus = "";
    private RadioGroup mRadioGroup;
    private List<OrderListBean.ResultDTO> mShopOrderList = new ArrayList<>();
    private ShsmOrderAdapter shsmOrderAdapter;




    @Override
    public int initLayout() {
        return R.layout.fragment_provide_home_delivery_service;
    }

    @Override
    public void initView(View view) {
        mRecycleView = view.findViewById(R.id.list);
        mRadioGroup = view.findViewById(R.id.login_radio_group);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRadioGroup.setOnCheckedChangeListener(this);

    }

    @Override
    public void initData() {
        orderStatus = "3";
        receiveStatus = "2";
        getOrderList();
        initAdapter();


    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getCheckedRadioButtonId()) {
            case R.id.admin:
                orderStatus = "3";
                receiveStatus = "2";
                getOrderList();
                break;
            case R.id.market:
                orderStatus = "5";
                receiveStatus = "3";
                getOrderList();
                break;
            case R.id.cancel:
                orderStatus = "4";
                receiveStatus = "0";
                getOrderList();
                break;
        }

    }
    private void getOrderList() {
        ShopSubscribe.getOrderList(orderStatus,receiveStatus,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                if(!result.isEmpty()) {
                    OrderListBean orderListBean = GsonUtils.fromJson(result,OrderListBean.class);
                    if(orderListBean.getResult() != null) {
                        mShopOrderList.clear();
                        mShopOrderList.addAll(orderListBean.getResult());
                        shsmOrderAdapter.notifyDataSetChanged();
                    }
                    else {
                        mShopOrderList.clear();
                        shsmOrderAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailed(String errorMsg) {
                showToast("请求失败" + errorMsg);

            }
        }));

    }

    private void initAdapter() {
        shsmOrderAdapter = new ShsmOrderAdapter(mShopOrderList);
        View emptyView = getLayoutInflater().inflate(R.layout.item_order_empty,null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        shsmOrderAdapter.setEmptyView(emptyView);
        mRecycleView.setAdapter(shsmOrderAdapter);
        shsmOrderAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if(mShopOrderList.get(position).getOrderStatus() == 3) {
                    if(mShopOrderList.get(position).getReceiveStatus() == 2) {
                        ARouter.getInstance().build(RoutePathConstant.SITE_SHSMORDERDETAILS)
                                .withString(RoutePathConstant.ORDER_ID_STRING,mShopOrderList.get(position).getId())
                                .withInt(RoutePathConstant.ORDER_TYPE,1)
                                .navigation();

                    }
                }

                else if(mShopOrderList.get(position).getOrderStatus() == 5) {
                    ARouter.getInstance().build(RoutePathConstant.SITE_SHSMORDERCOMPLETE)
                            .withString(RoutePathConstant.ORDER_ID_STRING,mShopOrderList.get(position).getId())
                            .navigation();
                }
                else {
                    ARouter.getInstance().build(RoutePathConstant.SITE_SHSMORDERCANCELDETAILS)
                            .withString(RoutePathConstant.ORDER_ID_STRING,mShopOrderList.get(position).getId())
                            .navigation();
                }
            }
        });

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            getOrderList();
        }
    }
}