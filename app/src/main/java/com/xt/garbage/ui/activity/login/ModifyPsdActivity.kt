package com.xt.garbage.ui.activity.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.xt.garbage.R
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.bean.login.SmsBean
import com.xt.garbage.bean.motormain.ReceiveOrderBean
import com.xt.garbage.constant.RoutePathConstant
import com.xt.garbage.netSubscribe.login.LoginSubscribe
import com.xt.garbage.netapi.OnSuccessAndFaultListener
import com.xt.garbage.netapi.OnSuccessAndFaultSub
import com.xt.garbage.utils.CountDownTimerUtils
import com.xt.garbage.utils.GsonUtils
import com.xt.garbage.wigdt.Toolbar
import kotlinx.android.synthetic.main.activity_modify_psd.*
import kotlinx.android.synthetic.main.activity_psd_login.*

@Route(path = RoutePathConstant.APP_SETTING_MODIFY)
class ModifyPsdActivity : BaseActivity() {
    var phone:String = ""
    override fun initLayout(): Int {
        return R.layout.activity_modify_psd
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
        btn_code.setOnClickListener {
            var mCountDownTimeUtils:CountDownTimerUtils = CountDownTimerUtils(btn_send,60000,1000)
            mCountDownTimeUtils.start()
            sendSms()
        }
        btn_send.setOnClickListener {
            if(psd.text.toString().isEmpty()) {
                showToast("验证码不能为空")
                return@setOnClickListener
            }
            if(new_psd.text.toString().isEmpty()) {
                showToast("请填写新密码")
                return@setOnClickListener
            }
            if(confirm_psd.text.toString().isEmpty()) {
                showToast("请再次输入新密码")
                return@setOnClickListener
            }
            if(!confirm_psd.text.toString().equals(new_psd.text.toString())) {
                showToast("两次输入密码必须一致")
                return@setOnClickListener
            }
            upPassword()
        }
    }

    private fun upPassword() {
        LoginSubscribe.upPassword(phone,psd.text.toString(),OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                result?.let {
                    var receiveOrderBean:ReceiveOrderBean = GsonUtils.fromJson(result,ReceiveOrderBean::class.java)
                    if(receiveOrderBean.errorCode == 0) {
                        showToast("密码修改成功")
                        finish()
                    }
                    else {
                        showToast(receiveOrderBean.errorMsg)
                    }
                }
            }

            override fun onFailed(errorMsg: String?) {
                showToast("请求失败：$errorMsg")
            }
        },this))
    }

    private fun sendSms() {
        LoginSubscribe.sendSms(phone,"2",OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                result?.let {
                    var smsBean: SmsBean = GsonUtils.fromJson(result,SmsBean::class.java)
                    if(smsBean.errorCode == 0) {
                        showToast("短信发送成功")
                    }
                    else {
                        showToast(smsBean.errorMsg)
                    }
                }
            }

            override fun onFailed(errorMsg: String?) {
                showToast("请求失败: $errorMsg")
            }
        },this))
    }


    override fun initData() {
        phoneNumber.text = login.result.userInfoRespDTO.mobile
        phone = login.result.userInfoRespDTO.mobile

    }

}