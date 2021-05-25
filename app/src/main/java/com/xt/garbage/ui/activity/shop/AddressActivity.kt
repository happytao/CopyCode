package com.xt.garbage.ui.activity.shop

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.xt.garbage.R
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.bean.shop.GetAddressResultBean
import com.xt.garbage.constant.RoutePathConstant

@Route(path = RoutePathConstant.ADD_ADDRESS)
class AddressActivity : BaseActivity(), View.OnClickListener {
    companion object {
        fun newInstance(activity: Activity, addressRespBean:GetAddressResultBean.ResultDTO,code:Int){
            var intent : Intent = Intent(activity,AddressActivity::class.java)
            intent.putExtra("bean",addressRespBean)
            intent.putExtra(RoutePathConstant.ADD_ADDRESS,code)
            activity.startActivity(intent)

        }
    }
    override fun onClick(v: View?) {

    }

    override fun initLayout(): Int {
        return R.layout.activity_address
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

}