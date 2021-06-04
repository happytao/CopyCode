package com.xt.garbage.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Space
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.xt.garbage.R
import com.xt.garbage.adapter.ImageAdapter
import com.xt.garbage.adapter.index.NewsAdapter
import com.xt.garbage.adapter.shop.IndexShopAdapter
import com.xt.garbage.base.BaseFragment
import com.xt.garbage.bean.constant.SpConstant
import com.xt.garbage.bean.garbage.OrderSubscribeDetailsBean
import com.xt.garbage.bean.login.LoginBean
import com.xt.garbage.bean.shop.HomeShopBean
import com.xt.garbage.bean.workmain.IndexBean
import com.xt.garbage.constant.OrderSubscribeStatusConstant
import com.xt.garbage.constant.RoutePathConstant
import com.xt.garbage.netSubscribe.garbage.GarbageSubscribe
import com.xt.garbage.netapi.OnSuccessAndFaultListener
import com.xt.garbage.netapi.OnSuccessAndFaultSub
import com.xt.garbage.utils.DimensionUtils
import com.xt.garbage.utils.GsonUtils
import com.xt.garbage.utils.SPUtils
import com.xt.garbage.wigdt.SpaceItemDecoration
import com.xt.garbage.wigdt.Toolbar
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(),View.OnClickListener {
    private var mOrderSubscribeDetailsBean:OrderSubscribeDetailsBean? = null
    private var indexShopAdapter:IndexShopAdapter? = null
    private var mShopList:MutableList<IndexBean.ResultDTO.GoodsListDTO> = ArrayList()
    private var mNewList:MutableList<IndexBean.ResultDTO.NewsListDTO> = ArrayList()
    private var newsAdapter:NewsAdapter? = null



    override fun initLayout(): Int {
        return R.layout.fragment_home
    }

    override fun initView(view: View?) {
        recyclerview.layoutManager = GridLayoutManager(context,3)
        newRecycler_view.layoutManager = LinearLayoutManager(context)
        make_item2.setOnClickListener(this)
        make_item1.setOnClickListener(this)
        make_item3.setOnClickListener(this)
        more.setOnClickListener(this)
        toolbar.setOnToolbarOnClickListener(object : Toolbar.ToolbarClickListener{
            override fun leftClick() {
            }

            override fun rightClick() {
                ARouter.getInstance().build(RoutePathConstant.APP_USER_MESSAGE).navigation()
            }
        })
    }

    override fun initData() {
        initAdapter()
        getIndex()
    }

    private fun getIndex() {
        GarbageSubscribe.getIndex(OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                result?.let {
                    var indexBean:IndexBean = GsonUtils.fromJson(result,IndexBean::class.java)
                    indexBean?.result?.bannerList?.let {
                        useBanner(indexBean.result.bannerList)
                    }
                    indexBean?.result?.goodsList?.let {
                        mShopList.addAll(indexBean.result.goodsList)
                        indexShopAdapter?.notifyDataSetChanged()
                    }
                    indexBean?.result?.newsList?.let {
                        mNewList.addAll(indexBean.result.newsList)
                        indexShopAdapter?.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailed(errorMsg: String?) {
                showToast("请求失败:$errorMsg")
            }
        },context))
    }

    private fun useBanner(bannerList: MutableList<IndexBean.ResultDTO.BannerListDTO>) {
        banner.addBannerLifecycleObserver(this)
                .setAdapter(ImageAdapter(bannerList,context))
                .setIndicator(CircleIndicator(context))
                .addBannerLifecycleObserver(this)

    }

    private fun initAdapter() {
        indexShopAdapter = IndexShopAdapter(mShopList)
        recyclerview.addItemDecoration(SpaceItemDecoration(DimensionUtils.dip2px(context,16f),DimensionUtils.dip2px(context,16f)))
        recyclerview.adapter = indexShopAdapter
        indexShopAdapter?.setOnItemClickListener { _, _, position ->
            ARouter.getInstance().build(RoutePathConstant.APP_COMMODITY_DETAILS)
                    .withLong(RoutePathConstant.COMMODITY_ID,mShopList[position].id)
                    .navigation()
        }
        newsAdapter = NewsAdapter(mNewList)
        newRecycler_view.adapter = newsAdapter
        newsAdapter?.setOnItemClickListener { adapter, view, position ->
            ARouter.getInstance().build(RoutePathConstant.INDEX_NEWS_INFO)
                    .withString(RoutePathConstant.TITLE,mNewList[position].newsTitle)
                    .withString(RoutePathConstant.LOGO,mNewList[position].newsResourceUrl)
                    .withString(RoutePathConstant.CONTENT,mNewList[position].newsContent)
                    .withString(RoutePathConstant.TIME,mNewList[position].createTime)
                    .navigation()
        }

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.make_item2 -> {
                var loginBean:LoginBean = GsonUtils.fromJson(SPUtils.get(context,SpConstant.APP_LOGINBEAN,"").toString(),LoginBean::class.java)
                if(loginBean.result.userInfoRespDTO.userType == 1) {
                    showToast("当前用户无法绑定驿站，请联系客服绑定驿站，再尝试该操作")
                    return
                }
                getDoingOrder()
            }
        }
    }

    private fun getDoingOrder() {
        GarbageSubscribe.doingOrder(OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                result?.let {
                    mOrderSubscribeDetailsBean = GsonUtils.fromJson(result,OrderSubscribeDetailsBean::class.java)
                    if(mOrderSubscribeDetailsBean?.errorCode == 0) {
                        if(mOrderSubscribeDetailsBean?.result != null) {
                            when(mOrderSubscribeDetailsBean?.result?.orderStatus) {
                                0 -> {
                                    ARouter.getInstance().build(RoutePathConstant.SITE_GOTO).navigation()
                                }

                                OrderSubscribeStatusConstant.ORDER_STATUS_ONE,
                                OrderSubscribeStatusConstant.ORDER_STATUS_SIX,
                                OrderSubscribeStatusConstant.ORDER_STATUS_SEVEN
                                -> {
                                    ARouter.getInstance().build(RoutePathConstant.SUBSCRIBE_ORDER)
                                            .withLong(RoutePathConstant.ORDER_ID, mOrderSubscribeDetailsBean?.result?.id!!)
                                            .navigation()

                                }

                                OrderSubscribeStatusConstant.ORDER_STATUS_TWO,
                                OrderSubscribeStatusConstant.ORDER_STATUS_THREE -> {
                                    ARouter.getInstance().build(RoutePathConstant.SUBSCRIBE_ORDER_RECEIVE)
                                            .withLong(RoutePathConstant.ORDER_ID,mOrderSubscribeDetailsBean?.result?.id!!)
                                }

                                else -> {}


                            }
                        }
                        else {
                            ARouter.getInstance().build(RoutePathConstant.SITE_GOTO).navigation()
                        }
                    }
                    else {
                        ARouter.getInstance().build(RoutePathConstant.SITE_GOTO).navigation()
                    }
                }
            }

            override fun onFailed(errorMsg: String?) {
                showToast("请求失败$errorMsg")
            }
        },context))
    }

}