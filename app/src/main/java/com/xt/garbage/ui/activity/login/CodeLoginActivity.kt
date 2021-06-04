package com.xt.garbage.ui.activity.login

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.xt.garbage.R
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.base.BaseConstant
import com.xt.garbage.bean.constant.SpConstant
import com.xt.garbage.bean.login.LoginBean
import com.xt.garbage.bean.login.SmsBean
import com.xt.garbage.constant.RoutePathConstant
import com.xt.garbage.netSubscribe.login.LoginSubscribe
import com.xt.garbage.netapi.OnSuccessAndFaultListener
import com.xt.garbage.netapi.OnSuccessAndFaultSub
import com.xt.garbage.utils.*
import com.xt.garbage.wigdt.Toolbar
import kotlinx.android.synthetic.main.activity_code_login.*

@Route(path = RoutePathConstant.APP_CODE_LOGIN)
class CodeLoginActivity : BaseActivity(), View.OnClickListener {
    @JvmField
    @Autowired(name = RoutePathConstant.LOGIN_PHONE)
    var phone:String = ""
    override fun initLayout(): Int {
        return R.layout.activity_code_login
    }

    override fun initView() {
        var mCountDownTimerUtils:CountDownTimerUtils = CountDownTimerUtils(btn_code,60000,1000)
        mCountDownTimerUtils.start()
        toolbar.setOnToolbarOnClickListener(object : Toolbar.ToolbarClickListener{
            override fun leftClick() {
                finish()
            }

            override fun rightClick() {

            }
        })
        btn_logon.setOnClickListener(this)
        btn_code.setOnClickListener(this)
    }

    override fun initData() {
        sendSms()
        btn_logon.isEnabled = false
        var textChageListener:EditStatusCheck.TextChangeListener = EditStatusCheck.TextChangeListener(btn_logon)
        textChageListener.addAllEditText(psd)
        EditStatusCheck.setChangeListener {
            if(it) {
                btn_logon.setBackgroundResource(R.drawable.bg_button_login_true)
                btn_logon.isEnabled = true
            }
            else {
                btn_logon.setBackgroundResource(R.drawable.bg_button_login_false)
            }
        }
        var encryptedPhone:String = phone.substring(0,3) + "****" + phone.substring(7,phone.length)
        TextUtils.Builder()
                .append("验证码已发送至$encryptedPhone")
                .setColor(Color.parseColor("#00BF60"),7,TextUtils.Builder.LENGTH)
                .into(code_sent_hint)


    }

    private fun sendSms() {
        LoginSubscribe.sendSms(phone,"1",OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                result?.let {
                    var smsBean:SmsBean = GsonUtils.fromJson(result,SmsBean::class.java)
                    if(smsBean.errorCode == 0) {
                        showToast("短信发送成功")
                    }
                    else{
                        showToast(smsBean.errorMsg)
                    }
                }
            }

            override fun onFailed(errorMsg: String?) {
                showToast("请求失败： $errorMsg")
            }
        },this))
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_logon -> {
                if(psd.text.toString().isEmpty()) {
                    showToast("验证码不能为空")
                    return
                }
                login()
            }

            R.id.btn_code -> {
                var mCountDownTimerUtils:CountDownTimerUtils = CountDownTimerUtils(btn_code,60000,1000)
                mCountDownTimerUtils.start()
                sendSms()
            }
        }
    }

    private fun login() {
        LoginSubscribe.login("","2",phone,"",psd.text.toString(),"2",OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                result?.let {
                    var loginBean:LoginBean = GsonUtils.fromJson(result,LoginBean::class.java)
                    if(loginBean.errorCode == 0){
                        SPUtils.put(context,SpConstant.APP_LOGINBEAN,result)
                        SPUtils.put(context,SpConstant.APP_TOKEN,loginBean.result.token)
                        SPUtils.put(context,SpConstant.IS_LOGIN,true)
                        BaseConstant.TOKEN = loginBean.result.token
                        BaseConstant.URLPREFIX = loginBean.result.urlPrefix
                        SPUtils.put(context,SpConstant.URLPREFIX,loginBean.result.urlPrefix)
                        checkoutLogin(loginBean.result.userInfoRespDTO.userType)
                    }
                    else {
                        showToast(loginBean.errorMsg)
                    }
                }
            }

            override fun onFailed(errorMsg: String?) {
                showToast("请求失败: $errorMsg")
            }
        },this))
    }

    private fun checkoutLogin(userType:Int) {
        when(userType) {
            1,2,3 -> {
                goMain()
            }

            4 -> {
                goWorkMain()
            }

            5 -> {
                goMotorMain()
            }

            else -> {}


        }

    }

    private fun goMotorMain() {
        TODO("Not yet implemented")
    }

    private fun goWorkMain() {
        ARouter.getInstance().build(RoutePathConstant.APP_WORKMAIN).navigation()
    }

    private fun goMain() {
        ARouter.getInstance().build(RoutePathConstant.APP_MAIN).navigation()

    }

}