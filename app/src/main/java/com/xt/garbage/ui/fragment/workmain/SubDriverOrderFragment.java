package com.xt.garbage.ui.fragment.workmain;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xt.garbage.R;
import com.xt.garbage.adapter.workmain.SubDriverOrderAdapter;
import com.xt.garbage.base.BaseFragment;
import com.xt.garbage.bean.workmain.SubDriverOrderListBean;
import com.xt.garbage.constant.RoutePathConstant;
import com.xt.garbage.netSubscribe.garbage.DriverSubscribe;
import com.xt.garbage.netapi.OnSuccessAndFaultListener;
import com.xt.garbage.netapi.OnSuccessAndFaultSub;
import com.xt.garbage.utils.GsonUtils;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubDriverOrderFragment extends BaseFragment {
    private int OrderStatus = 0;
    private List<SubDriverOrderListBean.ResultDTO> mList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private SubDriverOrderAdapter subDriverOrderAdapter;
    @Override
    public int initLayout() {
        return R.layout.fragment_sub_driver_order;
    }

    @Override
    public void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }

    @Override
    public void initData() {
        OrderStatus = getArguments().getInt("orderStatus");
        subDriverOrderAdapter= new SubDriverOrderAdapter(mList);
        View emptyView = getLayoutInflater().inflate(R.layout.item_order_empty,null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        subDriverOrderAdapter.setEmptyView(emptyView);
        mRecyclerView.setAdapter(subDriverOrderAdapter);
        getSubOrderList();
        subDriverOrderAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                int orderStatus = mList.get(position).getOrderStatus();
                if(orderStatus == 3) {
                    ARouter.getInstance().build(RoutePathConstant.WORK_CLEAN_DOOR)
                            .withLong(RoutePathConstant.ORDER_ID,mList.get(position).getId())
                            .withInt(RoutePathConstant.ORDER_STATUS,orderStatus)
                            .navigation();
                }
                else if(orderStatus == 4) {
                    ARouter.getInstance().build(RoutePathConstant.WORK_CLEAN_DOOR)
                            .withLong(RoutePathConstant.ORDER_ID,mList.get(position).getId())
                            .withInt(RoutePathConstant.ORDER_STATUS,orderStatus)
                            .navigation();
                }
                else {
                    ARouter.getInstance().build(RoutePathConstant.WORK_CLEAN_WAITER)
                            .withLong(RoutePathConstant.ORDER_ID,mList.get(position).getId())
                            .withInt(RoutePathConstant.ORDER_STATUS,orderStatus)
                            .navigation();
                }

            }
        });

    }

    private void getSubOrderList() {
        DriverSubscribe.getSubDriverList(setOrderList(),new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                if(!result.isEmpty()) {
                    SubDriverOrderListBean subDriverOrderListBean = GsonUtils.fromJson(result,SubDriverOrderListBean.class);
                    if(subDriverOrderListBean.getErrorCode() == 0) {
                        if(subDriverOrderListBean.getResult() != null) {
                            mList.clear();
                            mList.addAll(subDriverOrderListBean.getResult());
                            subDriverOrderAdapter.notifyDataSetChanged();
                        }
                        else {
                            subDriverOrderAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailed(String errorMsg) {
                showToast("请求失败"+errorMsg);
            }
        },getActivity()));

    }

    private List<String> setOrderList() {
        List<String> orderList = new ArrayList<>();
        switch (OrderStatus) {
            case 0:
                orderList.clear();
                orderList.addAll(Arrays.asList("1","2","3","4"));
                break;
            case 1:
                orderList.clear();
                orderList.add("5");
                break;
            case 2:
                orderList.clear();
                orderList.addAll(Arrays.asList("6","7","8","9","10"));
                break;
            case 3:
                orderList.clear();
                orderList.addAll(Arrays.asList("1","2","3","4","5","6","7","8","9","10"));
                break;
        }
        return orderList;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            getSubOrderList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getSubOrderList();
    }
}