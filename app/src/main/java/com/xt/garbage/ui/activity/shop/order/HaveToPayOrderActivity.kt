package com.xt.garbage.ui.activity.shop.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.xt.garbage.R
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.constant.RoutePathConstant

@Route(path = RoutePathConstant.APP_HAVE_TO_PAY)
class HaveToPayOrderActivity : BaseActivity() {
    @JvmField
    @Autowired(name = RoutePathConstant.ORDER_ID_STRING)
    var id:String = ""
    override fun initLayout(): Int {
        return R.layout.activity_have_to_pay_order
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

}