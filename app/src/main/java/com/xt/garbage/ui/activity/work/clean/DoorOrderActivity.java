package com.xt.garbage.ui.activity.work.clean;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xt.garbage.R;
import com.xt.garbage.adapter.workmain.DoorAdapter;
import com.xt.garbage.base.BaseActivity;
import com.xt.garbage.base.BaseConstant;
import com.xt.garbage.bean.workmain.BargainPostBean;
import com.xt.garbage.bean.workmain.BargainRequestBean;
import com.xt.garbage.bean.workmain.BargainUpBean;
import com.xt.garbage.bean.workmain.CleanOrderDetailsBean;
import com.xt.garbage.constant.EventCode;
import com.xt.garbage.constant.RoutePathConstant;
import com.xt.garbage.netSubscribe.garbage.DriverSubscribe;
import com.xt.garbage.netapi.OnSuccessAndFaultListener;
import com.xt.garbage.netapi.OnSuccessAndFaultSub;
import com.xt.garbage.utils.GsonUtils;
import com.xt.garbage.utils.ImageLoaderUtil;
import com.xt.garbage.utils.TextUtils;
import com.xt.garbage.wigdt.Toolbar;
import com.xt.garbage.workmain.EventMessage;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
@Route(path = RoutePathConstant.WORK_CLEAN_DOOR)
public class DoorOrderActivity extends BaseActivity {
    @Autowired(name = RoutePathConstant.ORDER_ID)
    long id = 0;
    @Autowired(name = RoutePathConstant.ORDER_STATUS)
    int orderStatus = 0;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
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
    @BindView(R.id.recyclerview)
    SwipeRecyclerView mSwipeRecyclerView;
    @BindView(R.id.btn_send)
    Button mConfirm;
    @BindView(R.id.refuse)
    TextView refuse;
    @BindView(R.id.phone_layout)
    LinearLayout mPhoneLayout;
    private TextView mTotalMoney;
    private List<CleanOrderDetailsBean.ResultDTO.RecycleListDTO> mList = new ArrayList<>();
    private DoorAdapter mDoorAdapter;
    private View headView;
    private View footView;
    private String callPhone;



    @Override
    protected int initLayout() {
        return R.layout.activity_door_order;
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

            }
        });
        initDriverView();
        initRecyclerView();
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTotalMoney = footView.findViewById(R.id.tol_money);

    }

    @Override
    protected void initData() {
        initAdapter();
        getOrderDetails();


    }

    private void initDriverView() {
        mPhoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCallDialog();
            }
        });


    }

    private void initRecyclerView() {
        headView = View.inflate(context,R.layout.head_door_order,null);
        footView = View.inflate(context,R.layout.foot_door_order,null);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mSwipeRecyclerView.setLayoutManager(manager);
        mSwipeRecyclerView.setSwipeMenuCreator(new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int position) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(context);
                deleteItem.setBackgroundColor(Color.parseColor("#FF3D39"))
                        .setText("删除")
                        .setTextColor(Color.WHITE)
                        .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)
                        .setWidth(170);
                rightMenu.addMenuItem(deleteItem);
            }
        });

        mSwipeRecyclerView.setOnItemMenuClickListener(new OnItemMenuClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge, int adapterPosition) {
                menuBridge.closeMenu();
                mList.remove(adapterPosition);
                mDoorAdapter.notifyDataSetChanged();
            }
        });
    }

    private void showDialog() {
        MaterialDialog dialog = new MaterialDialog(this,MaterialDialog.getDEFAULT_BEHAVIOR());
        dialog.title(null,"提示");
        dialog.message(null,"确认订单便不可修改，清运师傅确认订单将完成，您确认该订单无误吗？",null);
        dialog.positiveButton(null, "确认", new Function1<MaterialDialog, Unit>() {
            @Override
            public Unit invoke(MaterialDialog materialDialog) {
                confirmOrder();
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
    private void showCallDialog() {
        MaterialDialog dialog = new MaterialDialog(context,MaterialDialog.getDEFAULT_BEHAVIOR());
        dialog.title(null,"提示");
        dialog.message(null,"您确定要拨打电话联系该师傅吗？",null);
        dialog.positiveButton(null, "确认", new Function1<MaterialDialog, Unit>() {
            @Override
            public Unit invoke(MaterialDialog materialDialog) {
                callPhone();
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

    @NeedsPermission(Manifest.permission.CALL_PHONE)
    public void callPhone() {
        Intent intent = null;
        Uri uri = Uri.parse("tel:" + callPhone);
//        if(ActivityCompat.checkSelfPermission(context,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            callPhoneFailed();
//            return;
//        }
        intent = new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        context.startActivity(intent);

    }

    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    protected void callPhoneFailed() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS);
        context.startActivity(intent);

    }

    private void initAdapter() {
        mDoorAdapter = new DoorAdapter(mList);
        mSwipeRecyclerView.setAdapter(mDoorAdapter);
        mSwipeRecyclerView.addFooterView(footView);
        mSwipeRecyclerView.addHeaderView(headView);
        mDoorAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if(orderStatus == 0) {
                    showToast("数据正在加载中，请等待加载完毕");
                    return;
                }
                else if(orderStatus == 3) {

                }
            }
        });
    }

    private void getOrderDetails() {
        DriverSubscribe.getOrderDetails(id,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                if(!result.isEmpty()) {
                    CleanOrderDetailsBean cleanOrderDetailsBean = GsonUtils.fromJson(result,CleanOrderDetailsBean.class);
                    if(cleanOrderDetailsBean.getErrorCode() == 0) {
                        if(cleanOrderDetailsBean.getResult() != null) {
                            orderStatus = cleanOrderDetailsBean.getResult().getOrderStatus();
                            if(orderStatus == 4) {
                                mConfirm.setVisibility(View.GONE);
                                mPhoneLayout.setVisibility(View.GONE);
                            }
                            callPhone = cleanOrderDetailsBean.getResult().getMotormanResp().getMobile();
                            loadDriverInfo(cleanOrderDetailsBean);
                            if(cleanOrderDetailsBean.getResult().getRecycleList() != null) {
                                mList.addAll(cleanOrderDetailsBean.getResult().getRecycleList());
                                mDoorAdapter.notifyDataSetChanged();
                                double totalMoney = 0.00;
                                for (CleanOrderDetailsBean.ResultDTO.RecycleListDTO recycleListDTO : mList) {
                                    totalMoney += recycleListDTO.getRecylePrice();
                                }
                                setMoneyText(mTotalMoney,"总计(元):" + totalMoney + "元");
                            }
                        }
                        else {
                            showToast("网络错误");
                            finish();
                        }
                    }
                }
            }

            @Override
            public void onFailed(String errorMsg) {
                showToast("请求失败：" + errorMsg);

            }
        },this));
    }

    private void confirmOrder() {
        List<BargainUpBean> bargainUpBeanList = new ArrayList<>();
        for (CleanOrderDetailsBean.ResultDTO.RecycleListDTO recycleListDTO : mList) {
            BargainUpBean bargainUpBean = new BargainUpBean();
            bargainUpBean.setAll(recycleListDTO.isAll());
            bargainUpBean.setBargain(recycleListDTO.isBargain());
            bargainUpBean.setBargainCashPrice(recycleListDTO.getBargainCashPrice());
            bargainUpBean.setBargainWeightNumber(recycleListDTO.getBargainWeightNumber());
            bargainUpBean.setRefGarbageCategoryId(recycleListDTO.getRefGarbageCategoryId());
            bargainUpBeanList.add(bargainUpBean);
        }
        BargainPostBean bargainPostBean = new BargainPostBean();
        bargainPostBean.setBargainReqList(bargainUpBeanList);
        bargainPostBean.setRefCleanOrderId(id);
        DriverSubscribe.cleanOrderConfirm(bargainPostBean,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                if(!result.isEmpty()) {
                    orderStatus = 4;
                    mConfirm.setVisibility(View.GONE);
                    mPhoneLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailed(String errorMsg) {
                showToast("请求失败：" + errorMsg);

            }
        },this));

    }

    private void loadDriverInfo(CleanOrderDetailsBean cleanOrderDetailsBean) {
        ImageLoaderUtil.loadCustRoundCircleImage(this, BaseConstant.URLPREFIX+cleanOrderDetailsBean.getResult().getMotormanResp().getHead(),mHead,
                RoundedCornersTransformation.CornerType.ALL,8);
        mName.setText(cleanOrderDetailsBean.getResult().getMotormanResp().getNickName());
        mCode.setText(cleanOrderDetailsBean.getResult().getMotormanResp().getLicensePlate());
        mPhone.setText(cleanOrderDetailsBean.getResult().getMotormanResp().getMobile());
        mCleanTonnage.setText(cleanOrderDetailsBean.getResult().getMotormanResp().getCleanTonnage());

    }

    private void setMoneyText(TextView textView,String text) {
        TextUtils.getBuilder()
                .setSize(text,14,0,5)
                .setSize(22,6,text.length() - 1)
                .setSize(14,text.length() -1,text.length())
                .setColor(Color.parseColor("#FF0E3A"),6,text.length())
                .into(textView);

    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if(event.getCode() == EventCode.DOOR_ORDER_UP) {
            BargainRequestBean bargainRequestBean = (BargainRequestBean) event.getData();
            mList.get(bargainRequestBean.getIndex()).setBargainWeightNumber(bargainRequestBean.getBargainWeightNumber());
            mList.get(bargainRequestBean.getIndex()).setBargainCashPrice(bargainRequestBean.getBargainCashPrice());
            mList.get(bargainRequestBean.getIndex()).setAll(bargainRequestBean.isAll());
            mList.get(bargainRequestBean.getIndex()).setRecylePrice(bargainRequestBean.getRecyclePrice());
            mDoorAdapter.notifyDataSetChanged();
            double totalMoney = 0.00;
            for (CleanOrderDetailsBean.ResultDTO.RecycleListDTO recycleListDTO : mList) {
                totalMoney += recycleListDTO.getRecylePrice();
            }
            setMoneyText(mTotalMoney,"总计(元)" + totalMoney + "元");

        }
        else if(event.getCode() == EventCode.ORDER_CANCEL) {
            showToast("订单取消成功");
            finish();
        }
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        DoorOrderActivityPermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }
}