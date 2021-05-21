package com.xt.garbage.ui.activity.shop.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.xt.garbage.R
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.constant.RoutePathConstant
import kotlinx.android.synthetic.main.item_door.*

@Route(path = RoutePathConstant.APP_COMPLETE)
class CompletedOrderActivity : BaseActivity() {
    @Autowired(name = RoutePathConstant.ORDER_ID_STRING)
    var id:String = ""
    override fun initLayout(): Int {
        return R.layout.activity_compeleted_order
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

}