package com.xt.garbage.ui.activity.shop

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.xt.garbage.R
import com.xt.garbage.adapter.shop.OrderDetailsShopAdapter
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.bean.shop.BatchDetailsBean
import com.xt.garbage.bean.shop.GetAddressResultBean
import com.xt.garbage.bean.shop.OrderListBean
import com.xt.garbage.constant.EventCode
import com.xt.garbage.constant.RoutePathConstant
import com.xt.garbage.netSubscribe.shop.ShopSubscribe
import com.xt.garbage.netapi.OnSuccessAndFaultListener
import com.xt.garbage.netapi.OnSuccessAndFaultSub
import com.xt.garbage.ui.dialog.AddResDialogFragment
import com.xt.garbage.utils.GsonUtils
import com.xt.garbage.wigdt.Toolbar
import com.xt.garbage.workmain.EventMessage
import kotlinx.android.synthetic.main.activity_confirm_order_details.*
import kotlinx.android.synthetic.main.item_warehouse_list.*
import java.text.DecimalFormat

@Route(path = RoutePathConstant.APP_ORDER_DETAILS)
class ConfirmOrderDetailsActivity : BaseActivity(), View.OnClickListener {
    private var batchDetailsBean:BatchDetailsBean?= null
    private var list:MutableList<BatchDetailsBean.ResultDTO.ChildOrderRespListDTO> = ArrayList()
    private var addressList:MutableList<GetAddressResultBean.ResultDTO> = ArrayList()
    private var distributionWay:Boolean = false
    private var orderRemake:String = ""
    private var shopMoney:String = ""
    private var addressId:String = ""
    private var freightChargeWay:Boolean = true



    companion object{
        fun newInstance(activity: Activity, batchDetailsBean: BatchDetailsBean) {
            var intent:Intent = Intent(activity,ConfirmOrderDetailsActivity::class.java)
            intent.putExtra("bean",batchDetailsBean)
            activity.startActivity(intent)
        }
    }

    override fun initLayout(): Int {
        return R.layout.activity_confirm_order_details
    }

    override fun initView() {
        address_text.setOnClickListener(this)
        address_layout.setOnClickListener(this)
        confirm_btn.setOnClickListener(this)
        check_box.isChecked = true
        money_check_box.isChecked = true
        var manager:LinearLayoutManager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        recyclerview.layoutManager = manager
        toolbar.setOnToolbarOnClickListener(object : Toolbar.ToolbarClickListener{
            override fun leftClick() {
                finish()
            }

            override fun rightClick() {

            }
        })

        check_box.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                check.isChecked = false
                distributionWay = false
                fil_layout.visibility = View.VISIBLE
                fil_line.visibility = View.VISIBLE
                freight_text.visibility = View.VISIBLE
                if(addressList.size == 0) {
                    address_layout.visibility = View.GONE
                    address_text.visibility = View.VISIBLE
                }
                else {
                    address_layout.visibility = View.VISIBLE
                    address_text.visibility = View.GONE
                }

                if(freightChargeWay) {
                    setAllMoneyData()
                }
                else {
                    setAllSellData()
                }
            }
        }

        check.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                check_box.isChecked = false
                distributionWay = true
                fil_layout.visibility = View.GONE
                fil_line.visibility = View.GONE
                freight_text.visibility = View.GONE
                address_layout.visibility = View.GONE
                address_text.visibility = View.GONE
                setFreightChargeData()
            }
        }

        money_check_box.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                sell_check.isChecked = false
                freightChargeWay = true
                freight_text.text = "运费${batchDetailsBean?.result?.systemFreightCharge?.freightChargeValCash}元"
                setAllMoneyData()
            }
        }

        sell_check.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                money_check_box.isChecked = false
                freightChargeWay = false
                freight_text.text = "运费${batchDetailsBean?.result?.systemFreightCharge?.freightChargeValScore}积分"
                setAllSellData()
            }
        }


    }

    private fun setFreightChargeData() {
        var sell:Int = batchDetailsBean!!.result.totalConsumeScore
        if(batchDetailsBean?.result?.totalConsumeCash == 0.0) {
            order_money.text = "总计${sell}积分"
        }
        if(batchDetailsBean?.result?.totalConsumeScore == 0) {
            order_money.text = "总计${batchDetailsBean?.result?.totalConsumeCash}元${sell}积分"
        }
        if(batchDetailsBean?.result?.totalConsumeScore != 0 && batchDetailsBean?.result?.totalConsumeCash != 0.0) {
            order_money.text = "总计${batchDetailsBean?.result?.totalConsumeCash}元${sell}积分"
        }
    }

    private fun setAllSellData() {
        var rmb:Double = batchDetailsBean!!.result.systemFreightCharge.freightChargeValCash + batchDetailsBean!!.result.totalConsumeCash
        var df:DecimalFormat = DecimalFormat("######0.00")
        if(batchDetailsBean?.result?.totalConsumeCash == 0.0) {
            order_money.text = "总计: ${df.format(rmb)}元+${batchDetailsBean?.result?.totalConsumeScore}积分"
        }
        if(batchDetailsBean?.result?.totalConsumeScore == 0) {
            order_money.text = "总计：${df.format(rmb)}元"
        }
        if(batchDetailsBean?.result?.totalConsumeScore != 0 && batchDetailsBean?.result?.totalConsumeCash != 0.0) {
            order_money.text =  "总计: ${df.format(rmb)}元+${batchDetailsBean?.result?.totalConsumeScore}积分"
        }
    }

    private fun setAllMoneyData() {
        var sell:Int = batchDetailsBean!!.result.systemFreightCharge.freightChargeValScore + batchDetailsBean!!.result.totalConsumeScore
        if(batchDetailsBean?.result?.totalConsumeCash == 0.0) {
            order_money.text = "总计${sell}积分"
        }
        if(batchDetailsBean?.result?.totalConsumeScore == 0) {
            order_money.text = "总计${batchDetailsBean?.result?.totalConsumeCash}元${sell}积分"
        }
        if(batchDetailsBean?.result?.totalConsumeScore != 0 && batchDetailsBean?.result?.totalConsumeCash != 0.0) {
            order_money.text = "总计${batchDetailsBean?.result?.totalConsumeCash}元${sell}积分"
        }
    }

    override fun initData() {
        batchDetailsBean = intent.getSerializableExtra("bean") as BatchDetailsBean?
        batchDetailsBean?.result?.childOrderRespList?.let { list.addAll(it) }
        var orderDetailsShopAdapter:OrderDetailsShopAdapter = OrderDetailsShopAdapter(list)
        recyclerview.adapter = orderDetailsShopAdapter
        getAddress()
        setAllMoneyData()
        freight_text.text = "运费${batchDetailsBean?.result?.systemFreightCharge?.freightChargeValCash}元"
        if(batchDetailsBean?.result?.totalConsumeCash ==0.0) {
            top_money.text = "商品总计${batchDetailsBean?.result?.totalConsumeScore}积分"
        }
        else if(batchDetailsBean?.result?.totalConsumeScore == 0) {
            top_money.text = "商品总计${batchDetailsBean?.result?.totalConsumeCash}元"
        }
        else if(batchDetailsBean?.result?.totalConsumeScore != 0 && batchDetailsBean?.result?.totalConsumeCash != 0.0) {
            top_money.text = "商品总计${batchDetailsBean?.result?.totalConsumeCash}元+${batchDetailsBean?.result?.totalConsumeScore}积分"
        }
    }

    private fun getAddress() {
        ShopSubscribe.getAddress(OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
                try {
                    result?.let {
                        var getAddressResultBean: GetAddressResultBean = GsonUtils.fromJson(result, GetAddressResultBean::class.java)
                        getAddressResultBean?.result?.let {
                            if (getAddressResultBean.result.size != 0) {
                                address_layout.visibility = View.VISIBLE
                                address_text.visibility = View.GONE
                                addressList.addAll(getAddressResultBean.result)
                                for (index in addressList.indices) {
                                    if(addressList[index].isUse) {
                                        nick_name.text = addressList[index].name
                                        phone.text = addressList[index].mobile
                                        address.text = addressList[index].province + addressList[index].area + addressList[index].city + addressList[index].detailAddress
                                        addressId = addressList[index].id.toString()
                                    }
                                }
                            }
                            else {
                                address_layout.visibility = View.GONE
                                address_text.visibility = View.VISIBLE
                            }
                        }
                    }
                } catch (e: Exception) {
                    Log.e("TAG",Log.getStackTraceString(e))
                }
            }

            override fun onFailed(errorMsg: String?) {
                showToast("请求失败:$errorMsg")
            }
        }))
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.confirm_btn -> {
                if(!distributionWay) {
                    if(addressId.isEmpty()) {
                        showToast("请选择收货地址!")
                        return
                    }
                }
                batchConfirmOrder(batchDetailsBean?.result?.id!!)
            }
            R.id.address_layout,
            R.id.address_text-> {
                openDialogFragment()
            }
        }
    }

    private fun openDialogFragment() {
        var addressDialogFragment:AddResDialogFragment = AddResDialogFragment.instance
        addressDialogFragment.show(supportFragmentManager,AddResDialogFragment.TAG)
    }

    private fun batchConfirmOrder(id:String) {
        orderRemake = order_remark.text.toString()
        ShopSubscribe.confirmOrder(addressId,orderRemake,distributionWay,id,freightChargeWay,OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                ARouter.getInstance().build(RoutePathConstant.APP_WAIT_PAY)
                        .withString(RoutePathConstant.ORDER_ID_STRING,id)
                        .navigation()
            }

            override fun onFailed(errorMsg: String?) {
                showToast("请求失败$errorMsg")
            }
        }))

    }

    override fun onReceiveEvent(event: EventMessage<*>?) {
        super.onReceiveEvent(event)
        if(event?.code == EventCode.CONFIRM_ADDRESS) {
            var addressRespDTO:OrderListBean.ResultDTO.AddressRespDTO = event.data as OrderListBean.ResultDTO.AddressRespDTO
            nick_name.text = addressRespDTO.name
            phone.text = addressRespDTO.mobile
            address.text = addressRespDTO.province + addressRespDTO.area + addressRespDTO.city + addressRespDTO.detailAddress
            addressId = addressRespDTO.id.toString()
            address_text.visibility = View.GONE
            address_layout.visibility = View.VISIBLE
        }
    }

    override fun isRegisteredEventBus(): Boolean {
        return true
    }
}