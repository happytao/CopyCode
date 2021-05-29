package com.xt.garbage.ui.activity.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.xt.garbage.R
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.bean.motormain.ReceiveOrderBean
import com.xt.garbage.constant.RoutePathConstant
import com.xt.garbage.netSubscribe.garbage.DriverSubscribe
import com.xt.garbage.netapi.OnSuccessAndFaultListener
import com.xt.garbage.netapi.OnSuccessAndFaultSub
import com.xt.garbage.utils.GsonUtils
import com.xt.garbage.wigdt.Toolbar
import kotlinx.android.synthetic.main.activity_feed_back.*
@Route(path = RoutePathConstant.APP_SETTING_FEEDBACK)
class FeedbackActivity : BaseActivity() {
    var length:Int = 0
    override fun initLayout(): Int {
        return R.layout.activity_feed_back
    }

    override fun initView() {
        toolbar.setOnToolbarOnClickListener(object : Toolbar.ToolbarClickListener{
            override fun leftClick() {
                finish()
            }

            override fun rightClick() {
            }
        })

        try {
            edit_input.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    length = s.toString().length
                    text_input_number.text = "$length/100"
                }
            })
        } catch (e: Exception) {
            Log.e("TAG", Log.getStackTraceString(e))
        }

        btn_send.setOnClickListener {
            if(length < 10) {
                showToast("反馈内容至少10个字")
                return@setOnClickListener
            }
            else if(length > 100) {
                showToast("反馈内容不能超过100字")
                return@setOnClickListener
            }
            feedBack()
        }
    }

    private fun feedBack() {
        DriverSubscribe.feedback(edit_input.text.toString(),OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                result?.let {
                    var deliverySuccessBean:ReceiveOrderBean = GsonUtils.fromJson(result,ReceiveOrderBean::class.java)
                    if(deliverySuccessBean.errorCode == 0) {
                        showToast("反馈成功")
                        finish()
                    }
                }
            }

            override fun onFailed(errorMsg: String?) {
                showToast("请求失败: $errorMsg")
            }
        },this))
    }

    override fun initData() {

    }

}