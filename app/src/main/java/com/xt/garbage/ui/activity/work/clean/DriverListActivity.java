package com.xt.garbage.ui.activity.work.clean;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xt.garbage.R;
import com.xt.garbage.adapter.garbage.DriverListVerticalAdapter;
import com.xt.garbage.base.BaseActivity;
import com.xt.garbage.bean.workmain.DriverBean;
import com.xt.garbage.constant.RoutePathConstant;
import com.xt.garbage.netSubscribe.garbage.DriverSubscribe;
import com.xt.garbage.netapi.OnSuccessAndFaultListener;
import com.xt.garbage.netapi.OnSuccessAndFaultSub;
import com.xt.garbage.utils.GsonUtils;
import com.xt.garbage.wigdt.SpaceItemDecoration;
import com.xt.garbage.wigdt.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

@Route(path = RoutePathConstant.WORK_DRIVER_LIST)
public class DriverListActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private List<DriverBean.ResultDTO> mList = new ArrayList<>();
    private DriverListVerticalAdapter mDriverListVerticalAdapter;
    private Toolbar mToolbar;
    


    @Override
    protected int initLayout() {
        return R.layout.activity_driver_list;
    }

    @Override
    protected void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setOnToolbarOnClickListener(new Toolbar.ToolbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        
        mRecyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(12,12));

    }

    @Override
    protected void initData() {
        initAdapter();
        getDriverList();

    }

    private void getDriverList() {
        DriverSubscribe.getDriverList(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                if(!result.isEmpty()) {
                    DriverBean driverBean = GsonUtils.fromJson(result,DriverBean.class);
                    if(driverBean.getErrorCode() == 0) {
                        if(driverBean.getResult() != null) {
                            mList.clear();
                            mList.addAll(driverBean.getResult());
                            mDriverListVerticalAdapter.notifyDataSetChanged();
                        }
                        else {
                            mList.clear();
                            mDriverListVerticalAdapter.notifyDataSetChanged();
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

    private void initAdapter() {
        mDriverListVerticalAdapter = new DriverListVerticalAdapter(mList);
        mRecyclerView.setAdapter(mDriverListVerticalAdapter);
        mDriverListVerticalAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                ARouter.getInstance().build(RoutePathConstant.WORK_SUBSCRIBE_DRIVER)
                        .withLong(RoutePathConstant.DRIVER_ID,mList.get(position).getId())
                        .withString(RoutePathConstant.DRIVER_CLEAN,mList.get(position).getCleanTonnage())
                        .withString(RoutePathConstant.DRIVER_CODE,mList.get(position).getLicensePlate())
                        .withString(RoutePathConstant.DRIVER_HEAD,mList.get(position).getHead())
                        .withString(RoutePathConstant.DRIVER_PHONE,mList.get(position).getMobile())
                        .withString(RoutePathConstant.DRIVER_NAME,mList.get(position).getNickName())
                        .navigation();
            }
        });

    }
}