package com.xt.garbage.ui.activity.message

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.xt.garbage.R
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.bean.message.MessageListBean
import com.xt.garbage.constant.RoutePathConstant

@Route(path = RoutePathConstant.APP_MESSAGE_LIST_DETAILS)
class MessageDetailsActivity : BaseActivity() {
    @JvmField
    @Autowired(name = RoutePathConstant.MESSAGE)
    var baseMessageListDTO :MessageListBean.ResultDTO.BaseMessageListDTO? = null
    override fun initLayout(): Int {
        return R.layout.activity_message_details
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

}