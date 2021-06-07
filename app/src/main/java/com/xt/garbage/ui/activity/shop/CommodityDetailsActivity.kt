package com.xt.garbage.ui.activity.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.xt.garbage.R
import com.xt.garbage.adapter.shop.GoodsAdapter
import com.xt.garbage.adapter.workmain.ShopBannerAdapter
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.bean.shop.CommodityDetailsBean
import com.xt.garbage.constant.RoutePathConstant
import com.xt.garbage.netSubscribe.shop.ShopSubscribe
import com.xt.garbage.netapi.OnSuccessAndFaultListener
import com.xt.garbage.netapi.OnSuccessAndFaultSub
import com.xt.garbage.ui.dialog.CommodityDetailsDialogFragment
import com.xt.garbage.utils.GsonUtils
import com.xt.garbage.utils.TextUtils
import com.xt.garbage.wigdt.Toolbar
import com.youth.banner.indicator.CircleIndicator
import com.zzhoujay.richtext.RichText
import kotlinx.android.synthetic.main.activity_commodity_details.*

@Route(path = RoutePathConstant.APP_COMMODITY_DETAILS)
class CommodityDetailsActivity : BaseActivity(), View.OnClickListener {
    @JvmField
    @Autowired(name = RoutePathConstant.COMMODITY_ID)
    var goodsId:Long = 0L
    private var commodityBean:CommodityDetailsBean.Result? = null
    private var mGoodList:MutableList<CommodityDetailsBean.Result.GoodsDetail> = ArrayList()
    private var mGoodsAdapter:GoodsAdapter? = null
    private var mBannerList:MutableList<CommodityDetailsBean.Result.Resource> = ArrayList()

    override fun initLayout(): Int {
        return R.layout.activity_commodity_details
    }

    override fun initView() {
        toolbar.setOnToolbarOnClickListener(object : Toolbar.ToolbarClickListener{
            override fun leftClick() {
                finish()
            }

            override fun rightClick() {

            }
        })
        var manager:LinearLayoutManager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        spe_recycler_view.layoutManager = manager
        spe_recycler_view.setHasFixedSize(true)
        spe_recycler_view.isNestedScrollingEnabled = false
        RichText.initCacheDir(this)
    }

    override fun initData() {
        add_car.setOnClickListener(this)
        exchange.setOnClickListener(this)
        mGoodsAdapter = GoodsAdapter(mGoodList)
        spe_recycler_view.adapter = mGoodsAdapter
        getCommodityDetails(goodsId)
    }

    private fun getCommodityDetails(goodsId: Long) {
        ShopSubscribe.getCommodityDetails(goodsId,OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                result?.let {
                    var commodityDetailsBean:CommodityDetailsBean = GsonUtils.fromJson(result,CommodityDetailsBean::class.java)
                    if(commodityDetailsBean != null) {
                        mGoodsAdapter?.notifyDataSetChanged()
                        commodityBean = commodityDetailsBean.result
                        name.text = commodityDetailsBean.result.goodsName
                        mGoodList.addAll(commodityDetailsBean.result.goodsDetailList)
                        RichText.from(commodityDetailsBean.result.goodsHtmlText)
                                .into(logo)
                        if(commodityDetailsBean.result.goodsSellType == 1) {
                            setMoneyText(money,commodityDetailsBean.result.goodsScorePrice.toString() + "积分")
                        }
                        else if(commodityDetailsBean.result.goodsSellType == 2) {
                            setMoneyText(money,commodityDetailsBean.result.goodsCashPrice.toString() + "元")
                        }
                        else {
                            setMoneyText(money,commodityDetailsBean.result.goodsCashPrice.toString() + "元" + commodityDetailsBean.result.goodsScorePrice.toString() + "积分")
                        }

                        mBannerList.addAll(commodityDetailsBean.result.resourceList)
                        useBanner(mBannerList)

                    }
                }
            }

            override fun onFailed(errorMsg: String?) {
                showToast("请求失败: $errorMsg")
            }
        }))
    }

    private fun useBanner(list: MutableList<CommodityDetailsBean.Result.Resource>) {
        banner.addBannerLifecycleObserver(this)
                .setAdapter(ShopBannerAdapter(list,context))
                .setIndicator(CircleIndicator(context))
                .addBannerLifecycleObserver(this)


    }

    private fun setMoneyText(textView: TextView?,text:String) {
        TextUtils.getBuilder()
                .setSize(text,22,0,text.length-2)
                .setSize(14,text.length-2,text.length)
                .into(textView)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.add_car -> {
                var commodityDetailsDialogFragment:CommodityDetailsDialogFragment = CommodityDetailsDialogFragment.newInstance(commodityBean!!,true)
                commodityDetailsDialogFragment.show(supportFragmentManager,CommodityDetailsDialogFragment.TAG)
            }
            R.id.exchange -> {
                var commodityDetailsDialogFragment:CommodityDetailsDialogFragment = CommodityDetailsDialogFragment.newInstance(commodityBean!!,false)
                commodityDetailsDialogFragment.show(supportFragmentManager,CommodityDetailsDialogFragment.TAG)
            }
            else -> {}
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        RichText.clear(this)
    }
}

