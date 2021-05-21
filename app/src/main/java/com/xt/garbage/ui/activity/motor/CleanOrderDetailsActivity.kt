package com.xt.garbage.ui.activity.motor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.xt.garbage.R
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.constant.RoutePathConstant
import kotlinx.android.synthetic.main.item_door.*

@Route(path = RoutePathConstant.MOTOR_ORDER_DETAILS)
class CleanOrderDetailsActivity : BaseActivity() {
    @JvmField
    @Autowired(name = RoutePathConstant.ORDER_ID)
    var id:Long = 0L
    @JvmField
    @Autowired(name = RoutePathConstant.ORDER_STATUS)
    var orderStatus:Int = 0
    override fun initLayout(): Int {
        return R.layout.activity_clean_order_details
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

}