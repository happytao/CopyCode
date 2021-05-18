package com.xt.garbage.ui.fragment.workmain;

import android.content.ReceiverCallNotAllowedException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.flyco.tablayout.SlidingTabLayout;
import com.xt.garbage.R;
import com.xt.garbage.adapter.garbage.DriverListAdapter;
import com.xt.garbage.base.BaseFragment;
import com.xt.garbage.bean.workmain.DriverBean;
import com.xt.garbage.constant.RoutePathConstant;
import com.xt.garbage.netSubscribe.garbage.DriverSubscribe;
import com.xt.garbage.netapi.OnSuccessAndFaultListener;
import com.xt.garbage.netapi.OnSuccessAndFaultSub;
import com.xt.garbage.utils.GsonUtils;
import com.xt.garbage.wigdt.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CleanFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private List<DriverBean.ResultDTO> mList = new ArrayList<>();
    private DriverListAdapter mDriverListAdapter;
    private TextView mMore;
    private SlidingTabLayout mTab;
    private ViewPager mViewpager;
    private String[] titles = {"进行中", "已完成", "已取消", "全部"};
    private MyAdapter myAdapter;
    private int tab = 0;
    private View mEmpty;

    @Override
    public int initLayout() {
        return R.layout.fragment_clean;
    }

    @Override
    public void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(12, 12));
        mMore = view.findViewById(R.id.more);
        mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RoutePathConstant.WORK_DRIVER_LIST).navigation();
            }
        });
        mTab = view.findViewById(R.id.myTab);
        mViewpager = view.findViewById(R.id.view_pager);
        myAdapter = new MyAdapter(getChildFragmentManager());
        mViewpager.setAdapter(myAdapter);
        mTab.setViewPager(mViewpager);
        mViewpager.setCurrentItem(tab);
        mEmpty = getLayoutInflater().inflate(R.layout.item_order_empty, mRecyclerView, false);

    }

    @Override
    public void initData() {
        initAdapter();
        getOrderList();

    }

    private void initAdapter() {
        mDriverListAdapter = new DriverListAdapter(mList);
        mDriverListAdapter.setEmptyView(mEmpty);
        mRecyclerView.setAdapter(mDriverListAdapter);
        mDriverListAdapter.setOnItemClickListener(new OnItemClickListener() {
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

    private void getOrderList() {
        DriverSubscribe.getDriverList(new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                if (!result.isEmpty()) {
                    DriverBean driverBean = GsonUtils.fromJson(result, DriverBean.class);
                    if (driverBean.getErrorCode() == 0) {
                        mList.clear();
                        mList.addAll(driverBean.getResult());
                        mDriverListAdapter.notifyDataSetChanged();
                    } else {
                        mList.clear();
                        mDriverListAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailed(String errorMsg) {
                showToast("请求失败" + errorMsg);
            }
        }, getActivity()));
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putInt("orderStatus", position);
            SubDriverOrderFragment subDriverOrderFragment = new SubDriverOrderFragment();
            subDriverOrderFragment.setArguments(bundle);
            return subDriverOrderFragment;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
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