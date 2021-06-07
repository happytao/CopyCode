package com.xt.garbage.ui.activity.shop

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.xt.garbage.R
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.bean.shop.BatchDetailsBean

class ConfirmOrderDetailsActivity : BaseActivity(), View.OnClickListener {

    companion object{
        fun newInstance(activity: Activity, batchDetailsBean: BatchDetailsBean) {
            var intent:Intent = Intent(activity,this::class.java)
            intent.putExtra("bean",batchDetailsBean)
            activity.startActivity(intent)
        }
    }

    override fun initLayout(): Int {
        return R.layout.activity_confirm_order_details
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}