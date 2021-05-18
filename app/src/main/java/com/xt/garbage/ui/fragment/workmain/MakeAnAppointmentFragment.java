package com.xt.garbage.ui.fragment.workmain;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.GetChars;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xt.garbage.R;
import com.xt.garbage.adapter.workmain.AppointmentAdapter;
import com.xt.garbage.base.BaseFragment;
import com.xt.garbage.bean.workmain.AppointmentResultBean;
import com.xt.garbage.bean.workmain.GetAppointmentOrderStatusBean;
import com.xt.garbage.constant.RoutePathConstant;
import com.xt.garbage.netSubscribe.garbage.GarbageSubscribe;
import com.xt.garbage.netapi.OnSuccessAndFaultListener;
import com.xt.garbage.netapi.OnSuccessAndFaultSub;
import com.xt.garbage.utils.GsonUtils;
import com.xt.garbage.utils.PhoneUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MakeAnAppointmentFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    private RecyclerView mRecycleView;
    private List<Integer> orderStatuses = new ArrayList<>();
    private RadioGroup mRadioGroup;
    private AppointmentAdapter appointmentAdapter;
    private List<AppointmentResultBean.ResultDTO> mList = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.fragment_make_an_appointment;
    }

    @Override
    public void initView(View view) {
        mRecycleView = view.findViewById(R.id.list);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRadioGroup = view.findViewById(R.id.rg_login);
        mRadioGroup.setOnCheckedChangeListener(this);

    }

    @Override
    public void initData() {
        View emptyView = getLayoutInflater().inflate(R.layout.item_order_empty,null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        appointmentAdapter = new AppointmentAdapter(mList);
        appointmentAdapter.setEmptyView(emptyView);
        mRecycleView.setAdapter(appointmentAdapter);
        orderStatuses.add(1);
        getOrderList();
        appointmentAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                switch (mList.get(position).getOrderStatus()) {
                    case 1:
                    case 2:
                        goWait(mList.get(position).getId());
                        break;
                    case 3:
                        goUp(mList.get(position).getId(),mList.get(position).getContactName(),mList.get(position).getContactMobile());
                        break;
                    case 4:
                        goComplete(mList.get(position).getId());

                }
            }
        });

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getCheckedRadioButtonId()) {
            case R.id.admin:
                orderStatuses.clear();
                orderStatuses.add(1);
                getOrderList();
                break;
            case R.id.tech:
                orderStatuses.clear();
                orderStatuses.add(2);
                orderStatuses.add(3);
                getOrderList();
                break;
            case R.id.market:
                orderStatuses.clear();
                orderStatuses.addAll(Arrays.asList(4,5,6,7,8));
                getOrderList();
                break;
        }

    }

    private void getOrderList() {
        GetAppointmentOrderStatusBean getOrderStatusBean = new GetAppointmentOrderStatusBean();
        getOrderStatusBean.setOrderStatusList(orderStatuses);
        GarbageSubscribe.getAppointmentList(getOrderStatusBean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                if(!result.isEmpty()) {
                    AppointmentResultBean appointmentResultBean = GsonUtils.fromJson(result,AppointmentResultBean.class);
                    if(appointmentResultBean.getErrorCode() == 0) {
                        mList.clear();
                        if(appointmentResultBean.getResult() == null) {
                            appointmentAdapter.notifyDataSetChanged();
                        }
                        else {
                            mList.addAll(appointmentResultBean.getResult());
                            appointmentAdapter.notifyDataSetChanged();
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
    private void goWait(long orderId) {
        ARouter.getInstance().build(RoutePathConstant.WORK_APPOINTMENT_WAITER)
                .withLong(RoutePathConstant.ORDER_ID,orderId)
                .navigation();

    }

    private void goUp(long orderId, String contactName, String contactMobile) {
        ARouter.getInstance().build(RoutePathConstant.WORK_APPOINTMENT_ORDER_UP)
                .withLong(RoutePathConstant.ORDER_ID,orderId)
                .withString(RoutePathConstant.USER_NAME,contactName)
                .withString(RoutePathConstant.USER_PHONE,contactMobile)
                .navigation();
    }
    private void goComplete(long orderId) {
        ARouter.getInstance().build(RoutePathConstant.WORK_APPOINTMENT_COMPLETE)
                .withLong(RoutePathConstant.ORDER_ID,orderId)
                .navigation();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            getOrderList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getOrderList();
    }
}