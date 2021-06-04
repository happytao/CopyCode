package com.xt.garbage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.base.BaseConstant
import com.xt.garbage.base.BaseFragment
import com.xt.garbage.bean.constant.SpConstant
import com.xt.garbage.constant.RoutePathConstant
import com.xt.garbage.utils.SPUtils

@Route(path = RoutePathConstant.APP_MAIN,extras = SpConstant.LOGIN_NEEDED)
class HomeActivity : BaseActivity() {
    private var mBaseFragment:MutableList<BaseFragment>? = null
    private var position:Int = 0
    private var mContent:Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        setShowStatusBar(false)
        super.onCreate(savedInstanceState, persistentState)
        BaseConstant.TOKEN = SPUtils.get(this,SpConstant.APP_TOKEN,"").toString()
        BaseConstant.URLPREFIX = SPUtils.get(this,SpConstant.URLPREFIX,"").toString()
        initFragment()
    }

    private fun initFragment() {
        mBaseFragment = ArrayList()
        mBaseFragment.add()
    }

    override fun initLayout(): Int {
        return R.layout.activity_home
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

}