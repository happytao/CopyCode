package com.xt.garbage

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.alibaba.android.arouter.facade.annotation.Route
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.base.BaseConstant
import com.xt.garbage.base.BaseFragment
import com.xt.garbage.bean.constant.SpConstant
import com.xt.garbage.constant.RoutePathConstant
import com.xt.garbage.ui.fragment.main.HomeFragment
import com.xt.garbage.ui.fragment.main.ShopFragment
import com.xt.garbage.ui.fragment.main.ShoppingCartFragment
import com.xt.garbage.ui.fragment.main.UserFragment
import com.xt.garbage.utils.SPUtils
import kotlinx.android.synthetic.main.activity_home.*

@Route(path = RoutePathConstant.APP_MAIN,extras = SpConstant.LOGIN_NEEDED)
class HomeActivity : BaseActivity() {
    private var mBaseFragment:MutableList<BaseFragment>? = null
    private var position:Int = 0
    private var mContent:Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setShowStatusBar(false)
        BaseConstant.TOKEN = SPUtils.get(this,SpConstant.APP_TOKEN,"").toString()
        BaseConstant.URLPREFIX = SPUtils.get(this,SpConstant.URLPREFIX,"").toString()
        initFragment()
        setListener()
    }

    private fun setListener() {
        rg_main.setOnCheckedChangeListener(MyOnCheckedChangeListener())
        rg_main.check(R.id.rb_home_page)
    }

    private fun initFragment() {
        mBaseFragment = ArrayList<BaseFragment>()
        (mBaseFragment as ArrayList<BaseFragment>).add(HomeFragment())
        (mBaseFragment as ArrayList<BaseFragment>).add(ShopFragment())
        (mBaseFragment as ArrayList<BaseFragment>).add(ShoppingCartFragment())
        (mBaseFragment as ArrayList<BaseFragment>).add(UserFragment())

    }

    override fun initLayout(): Int {
        return R.layout.activity_home
    }

    override fun initView() {

    }

    override fun initData() {

    }

    inner class MyOnCheckedChangeListener:RadioGroup.OnCheckedChangeListener {
        override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
            when(checkedId) {
                R.id.rb_home_page -> {
                    position = 0
                }
                R.id.rb_market -> {
                    position = 1
                }
                R.id.rb_shopping_cart -> {
                    position = 2
                }
                R.id.rb_user -> {
                    position = 3
                }
                else -> {}
            }
            var to:BaseFragment = getFragment()
            switchFragment(mContent,to)
        }

    }

    private fun switchFragment(from: Fragment?, to: BaseFragment) {
        if(from != to) {
            mContent = to
            var ft : FragmentTransaction = supportFragmentManager.beginTransaction()
            if(!to.isAdded){
                if(from != null) {
                    ft.hide(from)
                }
                if(to != null) {
                    ft.add(R.id.fl_content,to).commit()
                }
            }
            else {
                if(from != null) {
                    ft.hide(from)
                }
                if(to != null) {
                    ft.show(to).commit()
                }
            }
        }

    }

    private fun getFragment(): BaseFragment {
        return mBaseFragment!![position]

    }

}