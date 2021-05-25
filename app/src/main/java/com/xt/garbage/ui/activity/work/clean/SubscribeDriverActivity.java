package com.xt.garbage.ui.activity.work.clean;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xt.garbage.R;
import com.xt.garbage.adapter.workmain.WareHouseListAdapter;
import com.xt.garbage.base.BaseActivity;
import com.xt.garbage.base.BaseConstant;
import com.xt.garbage.bean.workmain.DeliverySuccessBean;
import com.xt.garbage.bean.workmain.WareHouseListBean;
import com.xt.garbage.constant.RoutePathConstant;
import com.xt.garbage.netSubscribe.garbage.DriverSubscribe;
import com.xt.garbage.netapi.OnSuccessAndFaultListener;
import com.xt.garbage.netapi.OnSuccessAndFaultSub;
import com.xt.garbage.utils.CompressHelper;
import com.xt.garbage.utils.GsonUtils;
import com.xt.garbage.utils.ImageLoaderUtil;
import com.xt.garbage.wigdt.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

import butterknife.BindInt;
import butterknife.BindView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
@Route(path = RoutePathConstant.WORK_SUBSCRIBE_DRIVER)
public class SubscribeDriverActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @Autowired(name = RoutePathConstant.DRIVER_ID)
    long id = 0;
    @Autowired(name = RoutePathConstant.DRIVER_NAME)
    String name = "";
    @Autowired(name = RoutePathConstant.DRIVER_HEAD)
    String head = "";
    @Autowired(name = RoutePathConstant.DRIVER_CLEAN)
    String cleanTonnage = "";
    @Autowired(name = RoutePathConstant.DRIVER_CODE)
    String code = "";
    @Autowired(name = RoutePathConstant.DRIVER_PHONE)
    String phone = "";
    @BindView(R.id.head)
    ImageView mHeadImageView;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.phone)
    TextView mCode;
    @BindView(R.id.clean_tonnage)
    TextView mCleanTonnage;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private List<WareHouseListBean.ResultDTO> mList = new ArrayList<>();
    private WareHouseListAdapter wareHouseListAdapter;
    @BindView(R.id.btn_send)
    Button mSend;
    @BindView(R.id.phone_layout)
    LinearLayout mPhoneLayout;

    @Override
    protected int initLayout() {
        return R.layout.activity_subscribe_driver_acitvity;
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

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        mPhoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCallDialog();
            }
        });

    }

    @Override
    protected void initData() {
        initDriverData();
        wareHouseListAdapter = new WareHouseListAdapter(mList);
        mRecyclerView.setAdapter(wareHouseListAdapter);
        getWareHouseList();

    }

    private void showCallDialog() {
        MaterialDialog dialog = new MaterialDialog(this,MaterialDialog.getDEFAULT_BEHAVIOR());
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


    private void showDialog() {
        MaterialDialog dialog = new MaterialDialog(this,MaterialDialog.getDEFAULT_BEHAVIOR());
        dialog.title(null,"提示");
        dialog.message(null,"确认预约该司机上门来回收吗？",null);
        dialog.positiveButton(null, "确认", new Function1<MaterialDialog, Unit>() {
            @Override
            public Unit invoke(MaterialDialog materialDialog) {
                subscribeDriver();
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
        Uri uri = Uri.parse("tel:" + phone);
        if(ActivityCompat.checkSelfPermission(context,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            callPhoneFailed();
            return;
        }
        intent = new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        context.startActivity(intent);

    }

    protected void callPhoneFailed() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS);
        context.startActivity(intent);

    }

    private void initDriverData() {
        ImageLoaderUtil.loadCircleImage(this, BaseConstant.URLPREFIX + head,mHeadImageView);
        mName.setText(name);
        mCode.setText(code);
        mCleanTonnage.setText(cleanTonnage);
    }

    private void getWareHouseList() {
        DriverSubscribe.getWareHouseList(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                if(!result.isEmpty()) {
                    WareHouseListBean wareHouseListBean = GsonUtils.fromJson(result,WareHouseListBean.class);
                    if(wareHouseListBean.getErrorCode() == 0) {
                        if(wareHouseListBean.getResult() != null) {
                            mList.clear();
                            mList.addAll(wareHouseListBean.getResult());
                            wareHouseListAdapter.notifyDataSetChanged();
                        }
                        else {
                            mList.clear();
                            wareHouseListAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailed(String errorMsg) {
                showToast("请求失败" + errorMsg);

            }
        },this));

    }

    private void subscribeDriver() {
        DriverSubscribe.subscribeDriver(id,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                if(!result.isEmpty()) {
                    DeliverySuccessBean deliverySuccessBean = GsonUtils.fromJson(result,DeliverySuccessBean.class);
                    if(deliverySuccessBean.getErrorCode() == 0) {
                    }

                }
            }

            @Override
            public void onFailed(String errorMsg) {
                showToast("请求失败" + errorMsg);

            }
        },this));

    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SubscribeDriverActivityPermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }
}