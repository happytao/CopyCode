package com.xt.garbage.ui.activity.site

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.xt.garbage.R
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.constant.RoutePathConstant

@Route(path = RoutePathConstant.SUBSCRIBE_ORDER_RECEIVE)
class OrderSubscribeReceivedActivity : BaseActivity() {
    @JvmField
    @Autowired(name = RoutePathConstant.ORDER_ID)
    var id:Long = 0
    override fun initLayout(): Int {
        return R.layout.activity_order_subscribe_received
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

}