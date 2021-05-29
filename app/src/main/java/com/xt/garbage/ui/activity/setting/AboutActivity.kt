package com.xt.garbage.ui.activity.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.xt.garbage.R
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.constant.RoutePathConstant
import com.xt.garbage.utils.PackageUtils
import com.xt.garbage.wigdt.Toolbar
import kotlinx.android.synthetic.main.activity_about.*

@Route(path = RoutePathConstant.APP_SETTING_ABOUT)
class AboutActivity : BaseActivity() {
    override fun initLayout(): Int {
        return R.layout.activity_about
    }

    override fun initView() {
        toolbar.setOnToolbarOnClickListener(object : Toolbar.ToolbarClickListener{
            override fun leftClick() {
                finish()
            }

            override fun rightClick() {
                TODO("Not yet implemented")
            }
        })

    }

    override fun initData() {
        version.text = "V" + PackageUtils.getVersionCode(context)
    }

}