package com.xt.garbage.ui.activity.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.xt.garbage.R
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.bean.constant.SpConstant
import com.xt.garbage.bean.login.LoginBean
import com.xt.garbage.bean.motormain.ReceiveOrderBean
import com.xt.garbage.constant.RoutePathConstant
import com.xt.garbage.netSubscribe.garbage.GarbageSubscribe
import com.xt.garbage.netapi.OnSuccessAndFaultListener
import com.xt.garbage.netapi.OnSuccessAndFaultSub
import com.xt.garbage.utils.GsonUtils
import com.xt.garbage.utils.SPUtils
import com.xt.garbage.wigdt.Toolbar
import kotlinx.android.synthetic.main.activity_nick_name.*
import kotlin.math.log
@Route(path = RoutePathConstant.APP_SETTING_NICK)
class NickNameActivity : BaseActivity(), View.OnClickListener {
    override fun initLayout(): Int {
        return R.layout.activity_nick_name
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
        btn_send.setOnClickListener(this)
    }

    override fun initData() {
        edit_input.setText(login.result.userInfoRespDTO.nickName)
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.btn_send) {
            upUserInfo()
        }
    }

    private fun upUserInfo() {
        if(edit_input.text.toString().isEmpty()) {
            showToast("昵称不能为空")
            return
        }
        GarbageSubscribe.userInfoUp(edit_input.text.toString(),null,null,OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                result?.let{
                    var deliverySuccessBean:ReceiveOrderBean = GsonUtils.fromJson(it,ReceiveOrderBean::class.java)
                    if(deliverySuccessBean.errorCode == 0) {
                        var loginBean:LoginBean = login
                        loginBean.result.userInfoRespDTO.nickName = edit_input.text.toString()
                        var loginString:String = GsonUtils.toJson(loginBean)
                        SPUtils.put(context,SpConstant.APP_LOGINBEAN,loginString)
                        showToast("昵称修改成功")
                        finish()
                    }
                }
            }

            override fun onFailed(errorMsg: String?) {
                showToast("请求失败: $errorMsg")
            }
        },this))
    }

}