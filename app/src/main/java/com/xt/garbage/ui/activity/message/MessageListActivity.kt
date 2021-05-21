package com.xt.garbage.ui.activity.message

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.xt.garbage.R
import com.xt.garbage.adapter.message.MessageAdapter
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.bean.garbage.OrderSubscribeDetailsBean
import com.xt.garbage.bean.login.LoginBean
import com.xt.garbage.bean.message.MessageListBean
import com.xt.garbage.bean.motormain.OrderDetailsBean
import com.xt.garbage.bean.shop.BatchDetailsBean
import com.xt.garbage.bean.workmain.CleanOrderDetailsBean
import com.xt.garbage.bean.workmain.OrderSiteDetailsBean
import com.xt.garbage.constant.RoutePathConstant
import com.xt.garbage.netSubscribe.garbage.DriverSubscribe
import com.xt.garbage.netSubscribe.garbage.GarbageSubscribe
import com.xt.garbage.netSubscribe.shop.ShopSubscribe
import com.xt.garbage.netSubscribe.user.UserSubscribe
import com.xt.garbage.netapi.OnSuccessAndFaultSub
import kotlinx.android.synthetic.main.activity_message_list.*
import com.xt.garbage.netapi.OnSuccessAndFaultListener
import com.xt.garbage.utils.GsonUtils
import com.xt.garbage.wigdt.Toolbar

@Route(path = RoutePathConstant.APP_MESSAGE_LIST)
class MessageListActivity : BaseActivity() {
    @Autowired(name = RoutePathConstant.MESSAGE_TYPE)
    var message_type:Int = 0
    @Autowired(name = RoutePathConstant.MESSAGE)
    var messageListBean: MessageListBean? = null
    var mList:MutableList<MessageListBean.ResultDTO.BaseMessageListDTO> = ArrayList()
    var messageAdapter:MessageAdapter? = null



    override fun initLayout(): Int {
        return R.layout.activity_message_list
    }

    override fun initView() {
        recyclerview.layoutManager = LinearLayoutManager(this)
    }

    override fun initData() {
        messageAdapter = MessageAdapter(mList)
        recyclerview.adapter = messageAdapter
        messageAdapter?.setOnItemClickListener { _, _, position ->
            mList[position].isRead = true
            messageAdapter?.notifyDataSetChanged()
            setMessage(mList[position].id)
            when(mList[position].msgType) {
                1 -> {
                    goSub(mList[position].msgBussId)
                }
                2 -> {
                    goClean(mList[position].msgBussId)
                }
                3 -> {
                    goProvideHome(mList[position].msgBussId)
                }
                else -> {
                    ARouter.getInstance().build(RoutePathConstant.APP_MESSAGE_LIST_DETAILS)
                        .withObject(RoutePathConstant.MESSAGE,mList[position])
                        .navigation()
                }
            }


        }
        initToolbar()
    }

    private fun initToolbar() {
        toolbar.setOnToolbarOnClickListener(object : Toolbar.ToolbarClickListener{
            override fun leftClick() {
                finish()
            }

            override fun rightClick() {
                TODO("Not yet implemented")
            }
        })
        when(message_type) {
            1 -> {
                toolbar.title = "系统消息"
                messageListBean?.result?.let { mList.addAll(it.sysMessageList) }
            }

            2 -> {
                toolbar.title = "清运消息"
                messageListBean?.result?.let{ mList.addAll(it.cleanMessageList)}
            }

            3 -> {
                toolbar.title = "预约上门"
                messageListBean?.result?.let { mList.addAll(it.subscribeMessageList) }
            }

            4 -> {
                toolbar.title = "送货上门"
                messageListBean?.result?.let { mList.addAll(it.deliveryMessageList) }
            }
            else -> {}
        }
        messageAdapter?.notifyDataSetChanged()
    }

    private fun goProvideHome(msgBussId: Long) {
        var loginBean:LoginBean = login
        if(loginBean.result.userInfoRespDTO.userType == 4) {
            getSiteShopOrderInfo(msgBussId)
        }
        else {
            getUserShopOrderInfo(msgBussId)
        }
    }

    private fun getUserShopOrderInfo(msgBussId: Long) {
        ShopSubscribe.getParentOrderDetails(msgBussId,OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                var batchDetailsBean:BatchDetailsBean? = GsonUtils.fromJson(result,BatchDetailsBean::class.java)
                when(batchDetailsBean?.result?.orderStatus) {
                    2 -> {
                        ARouter.getInstance().build(RoutePathConstant.APP_WAIT_PAY)
                            .withString(RoutePathConstant.ORDER_ID_STRING,batchDetailsBean.result.id)
                            .navigation()
                    }
                    3 -> {
                        ARouter.getInstance().build(RoutePathConstant.APP_HAVE_TO_PAY)
                            .withString(RoutePathConstant.ORDER_ID_STRING,batchDetailsBean.result.id)
                            .navigation()
                    }

                    4 -> {
                        ARouter.getInstance().build(RoutePathConstant.APP_CANCEL)
                            .withString(RoutePathConstant.ORDER_ID_STRING,batchDetailsBean.result.id)
                            .navigation()
                    }
                    5 -> {
                        ARouter.getInstance().build(RoutePathConstant.APP_COMPLETE)
                            .withString(RoutePathConstant.ORDER_ID_STRING,batchDetailsBean.result.id)
                            .navigation()
                    }
                    else -> {}
                }
            }

            override fun onFailed(errorMsg: String?) {
                TODO("Not yet implemented")
            }
        }))

    }

    private fun getSiteShopOrderInfo(msgBussId: Long) {
        ShopSubscribe.getParentOrderDetails(msgBussId,OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                var batchDetailsBean:BatchDetailsBean = GsonUtils.fromJson(result,BatchDetailsBean::class.java)
                if(batchDetailsBean.result.orderStatus == 3) {
                    if(batchDetailsBean.result.isDistributionWay) {
                        ARouter.getInstance().build(RoutePathConstant.SITE_SHSMORDERDETAILS)
                            .withString(RoutePathConstant.ORDER_ID_STRING,batchDetailsBean.result.id)
                            .withInt(RoutePathConstant.ORDER_TYPE,2)
                            .navigation()

                    }
                    else{
                        ARouter.getInstance().build(RoutePathConstant.SITE_SHSMORDERDETAILS)
                            .withString(RoutePathConstant.ORDER_ID_STRING, batchDetailsBean.result.id)
                            .withInt(RoutePathConstant.ORDER_TYPE,1)
                            .navigation()
                    }
                }

                else if(batchDetailsBean.result.orderStatus == 5) {
                    ARouter.getInstance().build(RoutePathConstant.SITE_SHSMORDERCOMPLETE)
                        .withString(RoutePathConstant.ORDER_ID_STRING,batchDetailsBean.result.id)
                        .navigation()
                }
                else {
                    ARouter.getInstance().build(RoutePathConstant.SITE_SHSMORDERCANCELDETAILS)
                        .withString(RoutePathConstant.ORDER_ID_STRING,batchDetailsBean.result.id)
                        .navigation()
                }
            }

            override fun onFailed(errorMsg: String?) {
                TODO("Not yet implemented")
            }
        }))
    }

    private fun goClean(msgBussId: Long) {
        var loginBean:LoginBean = login
        if(loginBean.result.userInfoRespDTO.userType == 4) {
            getSiteCleanOrderInfo(msgBussId)
        }
        else {
            getCleanOrderInfo(msgBussId)
        }
    }

    private fun getCleanOrderInfo(msgBussId: Long) {
        DriverSubscribe.getMotorOrderDetails(msgBussId,OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                var orderDetailsBean:OrderDetailsBean = GsonUtils.fromJson(result,OrderDetailsBean::class.java)
                if(orderDetailsBean.errorCode == 0) {
                ARouter.getInstance().build(RoutePathConstant.MOTOR_ORDER_DETAILS)
                    .withLong(RoutePathConstant.ORDER_ID,orderDetailsBean.result.id)
                    .withInt(RoutePathConstant.ORDER_STATUS,orderDetailsBean.result.orderStatus)
                    .navigation()
                }
            }

            override fun onFailed(errorMsg: String?) {
                showToast("请求失败： $errorMsg")
            }
        }))

    }

    private fun getSiteCleanOrderInfo(msgBussId: Long) {
        DriverSubscribe.getOrderDetails(msgBussId,OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                var cleanOrderDetailsBean:CleanOrderDetailsBean = GsonUtils.fromJson(result,CleanOrderDetailsBean::class.java)
                if(cleanOrderDetailsBean.errorCode == 0) {
                    if(cleanOrderDetailsBean.result.orderStatus == 3 || cleanOrderDetailsBean.result.orderStatus == 4) {
                        ARouter.getInstance().build(RoutePathConstant.WORK_CLEAN_DOOR)
                                .withLong(RoutePathConstant.ORDER_ID,cleanOrderDetailsBean.result.id)
                                .withInt(RoutePathConstant.ORDER_STATUS,cleanOrderDetailsBean.result.orderStatus)
                                .navigation()

                    }
                    else {
                        ARouter.getInstance().build(RoutePathConstant.WORK_CLEAN_WAITER)
                                .withLong(RoutePathConstant.ORDER_ID,cleanOrderDetailsBean.result.id)
                                .withInt(RoutePathConstant.ORDER_STATUS,cleanOrderDetailsBean.result.orderStatus)
                                .navigation()
                    }

                }

            }

            override fun onFailed(errorMsg: String?) {
                showToast("请求失败: $errorMsg")
            }
        },this))

    }

    private fun goSub(msgBussId: Long) {
        var loginBean = login
        if(loginBean.result.userInfoRespDTO.userType == 4) {
            getSiteSubOrderInfo(msgBussId)
        }
        else {
            getUserSubOrderInfo(msgBussId)
        }
    }

    private fun getUserSubOrderInfo(id:Long) {
        GarbageSubscribe.orderDetails(id,OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                var orderSubscribeDetailsBean: OrderSubscribeDetailsBean = GsonUtils.fromJson(result,OrderSubscribeDetailsBean::class.java)
                if(orderSubscribeDetailsBean.errorCode == 0) {
                    when(orderSubscribeDetailsBean.result.orderStatus) {
                        1 -> {
                            ARouter.getInstance().build(RoutePathConstant.SUBSCRIBE_ORDER)
                                    .withLong(RoutePathConstant.ORDER_ID,orderSubscribeDetailsBean.result.id)
                                    .navigation()
                        }
                        2,3 -> {
                            ARouter.getInstance().build(RoutePathConstant.SUBSCRIBE_ORDER_RECEIVE)
                                    .withLong(RoutePathConstant.ORDER_ID,orderSubscribeDetailsBean.result.id)
                                    .navigation()
                        }

                        4 -> {
                            ARouter.getInstance().build(RoutePathConstant.SUBSCRIBE_ORDER_COMPLETE)
                                    .withLong(RoutePathConstant.ORDER_ID,orderSubscribeDetailsBean.result.id)
                                    .navigation()
                        }
                        else -> {
                            ARouter.getInstance().build(RoutePathConstant.SUBSCRIBE_ORDER_CANCEL)
                                    .withLong(RoutePathConstant.ORDER_ID,orderSubscribeDetailsBean.result.id)
                                    .navigation()
                        }


                    }

                }
            }

            override fun onFailed(errorMsg: String?) {
                showToast("请求失败: $errorMsg")
            }
        },this))

    }

    private fun getSiteSubOrderInfo(msgBussId:Long) {
        GarbageSubscribe.orderSiteDetails(msgBussId,OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                 var orderSiteDetailsBean:OrderSiteDetailsBean = GsonUtils.fromJson(result,OrderSiteDetailsBean::class.java)
                if(orderSiteDetailsBean.errorCode == 0) {
                    when(orderSiteDetailsBean.result.orderStatus) {
                        1,2 -> {
                            ARouter.getInstance().build(RoutePathConstant.WORK_APPOINTMENT_WAITER)
                                    .withLong(RoutePathConstant.ORDER_ID,orderSiteDetailsBean.result.id)
                                    .navigation()
                        }
                        3 -> {
                            ARouter.getInstance().build(RoutePathConstant.WORK_APPOINTMENT_ORDER_UP)
                                    .withLong(RoutePathConstant.ORDER_ID,orderSiteDetailsBean.result.id)
                                    .withString(RoutePathConstant.USER_NAME,orderSiteDetailsBean.result.contactName)
                                    .withString(RoutePathConstant.USER_PHONE,orderSiteDetailsBean.result.contactMobile)
                                    .navigation()
                        }
                        4 -> {
                            ARouter.getInstance().build(RoutePathConstant.WORK_APPOINTMENT_COMPLETE)
                                    .withLong(RoutePathConstant.ORDER_ID,orderSiteDetailsBean.result.id)
                                    .navigation()
                        }
                        else ->{}
                    }
                }

            }

            override fun onFailed(errorMsg: String?) {
                showToast("请求失败：$errorMsg")

            }
        },this))
    }

    private fun setMessage(messageId:Long) {
        UserSubscribe.setMessage(messageId,OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {

            }
            override fun onFailed(errorMsg: String?) {
                showToast("请求失败：$errorMsg")
            }

        },this))
    }
}