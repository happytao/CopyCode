package com.xt.garbage.ui.activity.message

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.alibaba.android.arouter.facade.annotation.Route
import com.xt.garbage.R
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.constant.RoutePathConstant
import com.xt.garbage.ui.fragment.workmain.MessageFragment
import com.xt.garbage.wigdt.Toolbar
import kotlinx.android.synthetic.main.fragment_home.*

@Route(path = RoutePathConstant.APP_USER_MESSAGE)
class MessageActivity : BaseActivity() {
    private var messageFragment:MessageFragment = MessageFragment()
    override fun initLayout(): Int {
        return R.layout.activity_message
    }

    override fun initView() {
        toolbar.setOnToolbarOnClickListener(object : Toolbar.ToolbarClickListener{
            override fun leftClick() {
                finish()
            }

            override fun rightClick() {
            }
        })
    }

    override fun initData() {
        var ft:FragmentTransaction = supportFragmentManager.beginTransaction()
        if(!messageFragment.isAdded) {
            if(messageFragment != null) {
                ft.add(R.id.content_layout,messageFragment).commit()
            }
        }
        else {
            if(messageFragment != null) {
                ft.show(messageFragment).commit()
            }
        }
    }

}