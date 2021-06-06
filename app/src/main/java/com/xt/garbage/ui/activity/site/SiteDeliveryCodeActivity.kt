package com.xt.garbage.ui.activity.site

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.util.ArrayMap
import androidx.annotation.RequiresApi
import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder
import com.alibaba.android.arouter.facade.annotation.Route
import com.xt.garbage.R
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.bean.login.LoginBean
import com.xt.garbage.constant.RoutePathConstant
import com.xt.garbage.utils.DesUtil
import com.xt.garbage.utils.GsonUtils
import com.xt.garbage.wigdt.Toolbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_site_delivery_code.*
import java.lang.Exception
import java.util.*

@Route(path = RoutePathConstant.SITE_CODE)
class SiteDeliveryCodeActivity : BaseActivity() {
    private var mCode:String = ""
    override fun initLayout(): Int {
        return R.layout.activity_site_delivery_code
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initData() {
        try{
            mCode = createCode()
        }
        catch (e:Exception) {
            e.printStackTrace()
        }
        createEnglishQRCode()
    }

    private fun createEnglishQRCode() {
        Observable.create<Bitmap> {
            it.onNext(QRCodeEncoder.syncEncodeQRCode(mCode,BGAQRCodeUtil.dp2px(this,150f), Color.parseColor("#000000")))
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    if(it != null) {
                        code.setImageBitmap(it)
                    }
                    else {
                        createEnglishQRCode()
                    }

                }

    }

    @Throws(Exception::class)
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createCode(): String {
        var loginBean:LoginBean = login
        var arrayMap: ArrayMap<String, String> = ArrayMap()
        arrayMap["code"] = "202"
        arrayMap["value"] = loginBean.result.userInfoRespDTO.id.toString()
        var str:String = GsonUtils.toJson(arrayMap)
        return DesUtil.encode(str)


    }

}