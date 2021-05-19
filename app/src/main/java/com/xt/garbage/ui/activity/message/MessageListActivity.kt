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
import com.xt.garbage.bean.workmain.OrderSiteDetailsBean
import com.xt.garbage.constant.RoutePathConstant
import com.xt.garbage.netSubscribe.garbage.DriverSubscribe
import com.xt.garbage.netSubscribe.garbage.GarbageSubscribe
import com.xt.garbage.netSubscribe.user.UserSubscribe
import com.xt.garbage.netapi.OnSuccessAndFaultSub
import kotlinx.android.synthetic.main.activity_message_list.*
import com.xt.garbage.netapi.OnSuccessAndFaultListener
import com.xt.garbage.utils.GsonUtils

@Route(path = RoutePathConstant.APP_MESSAGE_LIST)
class MessageListActivity : BaseActivity() {
    @Autowired(name = RoutePathConstant.MESSAGE_TYPE)
    var message_type:Int = 0
    @Autowired(name = RoutePathConstant.MESSAGE)
    var messageListBean: MessageListBean? = null
    var mList:MutableList<MessageListBean.ResultDTO.CleanMessageListDTO> = ArrayList()
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
        messageAdapter?.setOnItemClickListener { adapter, view, position ->
            mList[position].isRead = true
            messageAdapter?.notifyDataSetChanged()
            setMessage(mList[position].id)
            when(mList[position].msgType) {
                1 -> {
                    goSub(mList[position].msgBussId)
                }
                2 -> {
                    goClean()
                }
            }


        }
    }

    private fun goClean() {
        var loginBean:LoginBean = login
        if(loginBean.result.userInfoRespDTO.userType == 4) {
            getSiteCleanOrderInfo()
        }
    }

    private fun getSiteCleanOrderInfo(msgBussId: Long) {
        DriverSubscribe.getOrderDetails(msgBussId,OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {

            }

            override fun onFailed(errorMsg: String?) {
                TODO("Not yet implemented")
            }
        }))

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