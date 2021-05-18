package com.xt.garbage.ui.activity.work.clean;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.xt.garbage.R;
import com.xt.garbage.adapter.workmain.CleanOrderAdapter;
import com.xt.garbage.adapter.workmain.DoorAdapter;
import com.xt.garbage.base.BaseActivity;
import com.xt.garbage.base.BaseConstant;
import com.xt.garbage.bean.workmain.CleanOrderDetailsBean;
import com.xt.garbage.constant.RoutePathConstant;
import com.xt.garbage.netSubscribe.garbage.DriverSubscribe;
import com.xt.garbage.netapi.OnSuccessAndFaultListener;
import com.xt.garbage.netapi.OnSuccessAndFaultSub;
import com.xt.garbage.utils.GsonUtils;
import com.xt.garbage.utils.ImageLoaderUtil;
import com.xt.garbage.utils.TextUtils;
import com.xt.garbage.wigdt.Toolbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Route(path = RoutePathConstant.WORK_CLEAN_WAITER)
public class CleanWaitOrderActivity extends BaseActivity implements View.OnClickListener{
    @Autowired(name = RoutePathConstant.ORDER_ID)
    long id = 0;
    @Autowired(name = RoutePathConstant.ORDER_STATUS)
    int orderStatus = 0;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.type_image)
    ImageView mOrderTypeImageView;
    @BindView(R.id.type_text)
    TextView mOrderTypeText;
    @BindView(R.id.head)
    ImageView mHead;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.code)
    TextView mCode;
    @BindView(R.id.phone)
    TextView mPhone;
    @BindView(R.id.clean_tonnage)
    TextView mCleanTonnage;
    @BindView(R.id.order_id)
    TextView mOrderNumber;
    @BindView(R.id.order_time)
    TextView mOrderCreateTime;
    @BindView(R.id.clean_type_text)
    TextView mCLeanTypeText;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.clean_recyclerview)
    RecyclerView mClearRecyclerView;
    @BindView(R.id.btn_type)
    Button mTypeButton;
    @BindView(R.id.btn_layout)
    LinearLayout mBtnLayout;
    @BindView(R.id.clean_layout)
    LinearLayout mCleanLayout;

    private List<CleanOrderDetailsBean.ResultDTO.RecycleListDTO> mList  = new ArrayList<>();
    private List<CleanOrderDetailsBean.ResultDTO.RecycleListDTO> mCleanList = new ArrayList<>();
    private CleanOrderAdapter cleanOrderAdapter;
    private CleanOrderDetailsBean.ResultDTO.MotormanRespDTO motormanRespDTO;
    private DoorAdapter mDoorAdapter;
    private View mHeadView;
    private View mFootView;
    private TextView mHeadName,mHeadWeight,mFootTotalWeight;


    @Override
    protected int initLayout() {
        return R.layout.activity_clean_wait_order;
    }

    @Override
    protected void initView() {
        mHeadView = View.inflate(this,R.layout.item_clean_recycle_list_head,null);
        mFootView = View.inflate(this,R.layout.item_clean_recycle_list_foot,null);
        mHeadName = mHeadView.findViewById(R.id.head_name);
        mHeadWeight = mHeadView.findViewById(R.id.head_weight);
        mFootTotalWeight = mFootView.findViewById(R.id.foot_total_weight);
        mToolbar.setOnToolbarOnClickListener(new Toolbar.ToolbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        initOrderView();
        initCleanView();



    }

    @Override
    protected void initData() {
        initAdapter();
        initOrderType();
        getOrderDetails();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_type:
                switch (orderStatus) {
                    case 1:
                    case 2:
                        showDialog();
                        break;
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                        goSubDriver();
                        break;
                    case 7:
                        goSubDriverList();
                        break;

                }
                break;
        }

    }



    private void initOrderView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTypeButton.setOnClickListener(this);

    }

    private void initCleanView() {
        mClearRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDoorAdapter = new DoorAdapter(mList);
        mClearRecyclerView.setAdapter(mDoorAdapter);

    }

    private void initAdapter() {
        cleanOrderAdapter = new CleanOrderAdapter(mCleanList);
        cleanOrderAdapter.addHeaderView(mHeadView);
        cleanOrderAdapter.addFooterView(mFootView);
        mRecyclerView.setAdapter(cleanOrderAdapter);
    }

    private void initOrderType() {
        switch (orderStatus) {
            case 1:
                setWaiterText();
                break;
            case 2:
                setReceiveText();
                break;
            case 5:
                setCompleteText();
                break;
            case 6:
            case 8:
            case 9:
            case 10:
                setCancelText();
                break;
            case 7:
                setRefuseText();
                break;
        }

    }

    private void setWaiterText() {
        mOrderTypeText.setText("未接单");
        Glide.with(this).load(R.drawable.site_orderone_icon).into(mOrderTypeImageView);
        mTypeButton.setText("取消预约");
    }

    private void setReceiveText() {
        mOrderTypeText.setText("已接单");
        Glide.with(this).load(R.drawable.site_type_success_icon).into(mOrderTypeImageView);
        mTypeButton.setText("取消预约");
    }

    private void setCompleteText() {
        mOrderTypeText.setText("已完成");
        Glide.with(this).load(R.drawable.site_type_success_icon).into(mOrderTypeImageView);
        mBtnLayout.setVisibility(View.GONE);
        mClearRecyclerView.setVisibility(View.VISIBLE);
        mCleanLayout.setVisibility(View.GONE);
    }

    private void setCancelText() {
        mOrderTypeText.setText("已取消");
        Glide.with(this).load(R.drawable.site_close_icon).into(mOrderTypeImageView);
        mTypeButton.setText("重新预约");

    }

    private void setRefuseText() {
        mOrderTypeText.setText("已拒绝");
        Glide.with(this).load(R.drawable.site_close_icon).into(mOrderTypeImageView);
        mTypeButton.setText("重新预约");

    }

    private void getOrderDetails() {
        DriverSubscribe.getOrderDetails(id,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                if(!result.isEmpty()) {
                    CleanOrderDetailsBean cleanOrderDetailsBean = GsonUtils.fromJson(result,CleanOrderDetailsBean.class);
                    if(cleanOrderDetailsBean.getErrorCode() == 0) {
                        if(cleanOrderDetailsBean.getResult() != null) {
                            loadDriverInfo(cleanOrderDetailsBean);
                            loadOrderInfo(cleanOrderDetailsBean);
                            motormanRespDTO = cleanOrderDetailsBean.getResult().getMotormanResp();
                            double weight = 0.00;
                            if (cleanOrderDetailsBean.getResult().getRecycleList() != null) {
                                for (CleanOrderDetailsBean.ResultDTO.RecycleListDTO recycleListDTO : cleanOrderDetailsBean.getResult().getRecycleList()) {
                                    weight += recycleListDTO.getRecyleWeightNumber();
                                }
                                mHeadName.setTextColor(Color.parseColor("#000000"));
                                setOrderBlackText(mHeadWeight,"重量(kg)");
                                setBlackText(mFootTotalWeight,"总重量" + weight);
                                mList.addAll(cleanOrderDetailsBean.getResult().getRecycleList());
                                mDoorAdapter.notifyDataSetChanged();
                                mCleanList.addAll(cleanOrderDetailsBean.getResult().getRecycleList());
                                cleanOrderAdapter.notifyDataSetChanged();
                            } else{
                                mRecyclerView.setVisibility(View.GONE);
                                mClearRecyclerView.setVisibility(View.GONE);
                            }
                        }
                    }
                    else {
                        showToast("网络错误");
                        finish();
                    }

                }
            }

            @Override
            public void onFailed(String errorMsg) {
                showToast("请求失败" + errorMsg);

            }
        }));
    }

    private void loadDriverInfo(CleanOrderDetailsBean cleanOrderDetailsBean) {
        ImageLoaderUtil.loadCircleImage(this, BaseConstant.URLPREFIX + cleanOrderDetailsBean.getResult().getMotormanResp().getHead(),mHead);
        mName.setText(cleanOrderDetailsBean.getResult().getMotormanResp().getNickName());
        mCode.setText(cleanOrderDetailsBean.getResult().getMotormanResp().getLicensePlate());
        mPhone.setText(cleanOrderDetailsBean.getResult().getMotormanResp().getMobile());
        mCleanTonnage.setText(cleanOrderDetailsBean.getResult().getMotormanResp().getCleanTonnage());
    }

    private void loadOrderInfo(CleanOrderDetailsBean cleanOrderDetailsBean) {
        setOrderBlackText2(mOrderNumber,"预约单号:" + cleanOrderDetailsBean.getResult().getId());
        setOrderBlackText2(mOrderCreateTime,"创建时间:" + cleanOrderDetailsBean.getResult().getCreateTime());

    }

    private void setOrderBlackText(TextView textView,String text) {
        TextUtils.getBuilder()
                .setSize(text,10,2,text.length())
                .setColor(Color.parseColor("#000000"),0,2)
                .into(textView);
    }

    private void setOrderBlackText2(TextView textView,String text) {
        TextUtils.getBuilder()
                .setSize(text,14,5,text.length())
                .setColor(Color.parseColor("#99000000"),0,4)
                .into(textView);
    }

    private void setBlackText(TextView textView,String text) {
        TextUtils.getBuilder()
                .setSize(text,12,0,7)
                .setColor(Color.parseColor("#000000"),8,text.length())
                .into(textView);
    }

    private void showDialog() {
        MaterialDialog dialog = new MaterialDialog(this,MaterialDialog.getDEFAULT_BEHAVIOR());
        dialog.title(null,"提示");
        dialog.message(null,"你确认取消该清运订单吗?",null);
        dialog.positiveButton(null, "确认", new Function1<MaterialDialog, Unit>() {
            @Override
            public Unit invoke(MaterialDialog materialDialog) {
                orderCancel();
                return null;
            }
        });
        dialog.negativeButton(null, "取消", new Function1<MaterialDialog, Unit>() {
            @Override
            public Unit invoke(MaterialDialog materialDialog) {
                dialog.dismiss();
                return null;
            }
        });
        dialog.show();

    }

    private void goSubDriver() {

    }

    private void goSubDriverList() {

    }
    private void orderCancel() {

    }

}