package com.xt.garbage.ui.fragment.workmain;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.SlidingTabLayout;
import com.xt.garbage.R;
import com.xt.garbage.base.BaseFragment;
import com.xt.garbage.constant.RoutePathConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkHomeFragment extends BaseFragment {
    private int tab = 0;
    private SlidingTabLayout myTab;
    private ViewPager viewPager;
    private String[] titles = {"送货上门","预约上门","清运"};
    private MyAdapter myAdapter;
    private List<Fragment> mList = new ArrayList<>();
    private RelativeLayout mCodeLayout;


    @Override
    public int initLayout() {
        return R.layout.fragment_work_home;
    }

    @Override
    public void initView(View view) {
        viewPager = view.findViewById(R.id.vp_viewPager);
        myTab = view.findViewById(R.id.tl_myTab);
        mCodeLayout = view.findViewById(R.id.rl_code_layout);
        myAdapter = new MyAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(myAdapter);
        myTab.setViewPager(viewPager);
        viewPager.setCurrentItem(tab);
        mList.add(new ProvideHomeDeliveryServiceFragment());
        mList.add(new MakeAnAppointmentFragment());
        mList.add(new CleanFragment());
        mCodeLayout = view.findViewById(R.id.rl_code_layout);
        mCodeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RoutePathConstant.WORK_SCAN_CODE).navigation();
            }
        });

    }

    @Override
    public void initData() {

    }

    class MyAdapter extends FragmentPagerAdapter {


        public MyAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }
}