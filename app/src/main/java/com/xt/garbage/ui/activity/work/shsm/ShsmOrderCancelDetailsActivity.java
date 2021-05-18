package com.xt.garbage.ui.activity.work.shsm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.xt.garbage.R;
import com.xt.garbage.adapter.shop.OrderDetailsShopAdapter;
import com.xt.garbage.base.BaseActivity;
import com.xt.garbage.bean.shop.BatchDetailsBean;
import com.xt.garbage.constant.RoutePathConstant;
import com.xt.garbage.netSubscribe.shop.ShopSubscribe;
import com.xt.garbage.netapi.OnSuccessAndFaultListener;
import com.xt.garbage.netapi.OnSuccessAndFaultSub;
import com.xt.garbage.utils.GsonUtils;
import com.xt.garbage.wigdt.Toolbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;

@Route(path = RoutePathConstant.SITE_SHSMORDERCANCELDETAILS)
public class ShsmOrderCancelDetailsActivity extends BaseActivity {
    @Autowired(name = RoutePathConstant.ORDER_ID)
    String id = "";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private List<BatchDetailsBean.ResultDTO.ChildOrderRespListDTO> mGoodList = new ArrayList<>();
    private OrderDetailsShopAdapter orderDetailsShopAdapter;
    @BindView(R.id.order_number)
    TextView mOrderNumber;
    @BindView(R.id.top_money)
    TextView mTotal;
    private BatchDetailsBean batchDetailsBean;
    @BindView(R.id.freight_text)
    TextView mFreightText;
    @BindView(R.id.site_name)
    TextView mSiteName;
    @BindView(R.id.site_address)
    TextView mSiteAddress;
    @BindView(R.id.pull_time)
    TextView mPullTime;
    @BindView(R.id.date)
    TextView mOrderType;



    public ShsmOrderCancelDetailsActivity() {
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_shsm_order_cancel_details;
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mToolbar.setOnToolbarOnClickListener(new Toolbar.ToolbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });


    }

    @Override
    protected void initData() {
        orderDetailsShopAdapter = new OrderDetailsShopAdapter(mGoodList);
        mRecyclerView.setAdapter(orderDetailsShopAdapter);
        batchDetails(id);

    }

    private void batchDetails(String id) {
        ShopSubscribe.getParentOrderDetails(id,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                if(!result.isEmpty()) {
                    batchDetailsBean = GsonUtils.fromJson(result,BatchDetailsBean.class);
                    if(batchDetailsBean != null) {
                        mGoodList.addAll(batchDetailsBean.getResult().getChildOrderRespList());
                        orderDetailsShopAdapter.notifyDataSetChanged();
                        mOrderNumber.setText("订单编号" + batchDetailsBean.getResult().getId());
                        if(!batchDetailsBean.getResult().isDistributionWay()) {
                            mSiteName.setText("送货上门");
                        }
                        else {
                            mSiteName.setText("门店自提");
                        }
                        if(batchDetailsBean.getResult().getOrderCancelType() == 0) {
                            mOrderNumber.setText("超时取消");
                        }
                        else if(batchDetailsBean.getResult().getOrderCancelType() == 1) {
                            mOrderNumber.setText("用户取消");
                        }
                        else if(batchDetailsBean.getResult().getOrderCancelType() == 2) {
                            mOrderNumber.setText(batchDetailsBean.getResult().getOrderCancelCause());
                        }
                        mSiteAddress.setText(batchDetailsBean.getResult().getCreateTime());
                        mPullTime.setText(batchDetailsBean.getResult().getOrderCancelTime());

                    }
                }
            }

            @Override
            public void onFailed(String errorMsg) {

            }
        }));
    }

    private void setMoneyText(BatchDetailsBean batchDetailsBean) {
        if(batchDetailsBean.getResult().isDistributionWay()) {
            setAllMoneyData();
            mFreightText.setVisibility(View.GONE);
            return;
        }
        if(batchDetailsBean.getResult().isFreightChargeWay()) {
            setAllMoneyData();
            mFreightText.setText("运费" + batchDetailsBean.getResult().getFreightChargeVal() + "元");
        }
        else {
            setAllMoneyData();
            mFreightText.setText("运费" + batchDetailsBean.getResult().getFreightChargeVal() + "积分");
        }
    }

    private void setAllMoneyData() {
        if(batchDetailsBean.getResult().getTotalConsumeCash() == 0) {
            mTotal.setText("总计" + batchDetailsBean.getResult().getTotalConsumeScore() + "积分");
        }
        if(batchDetailsBean.getResult().getTotalConsumeScore() == 0) {
            mTotal.setText("总计" + batchDetailsBean.getResult().getTotalConsumeCash() + "元");
        }
        if(batchDetailsBean.getResult().getTotalConsumeCash() != 0 && batchDetailsBean.getResult().getTotalConsumeScore() != 0) {
            mTotal.setText("总计" + batchDetailsBean.getResult().getTotalConsumeCash() + "元＋" + batchDetailsBean.getResult().getTotalConsumeScore() + "积分");
        }

    }
}