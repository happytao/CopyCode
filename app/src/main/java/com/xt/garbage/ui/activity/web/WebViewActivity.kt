package com.xt.garbage.ui.activity.web

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.xt.garbage.R
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.constant.RoutePathConstant
import com.xt.garbage.wigdt.Toolbar
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.activity_about.toolbar
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.item_door.*

@Route(path = RoutePathConstant.APP_SETTING_WEB_VIEW)
class WebViewActivity : BaseActivity() {
    @JvmField
    @Autowired(name = RoutePathConstant.URL)
    var url:String = ""
    @JvmField
    @Autowired(name = RoutePathConstant.TITLE)
    var title:String = ""
    override fun initLayout(): Int {
        return R.layout.activity_web_view
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
        initWebSetting()
    }

    private fun initWebSetting() {
        //声明WebSetting子类
        var webSettings:WebSettings = web_view.settings

        //如果访问的页面要与JS交互，则webView必须设置支持JS
        webSettings.javaScriptEnabled = true
        //设置自适应屏幕，两者适用
        //将图片调整到合适webView的大小
        webSettings.useWideViewPort = true
        //缩放至屏幕大小
        webSettings.loadWithOverviewMode
        //支持缩放,默认为true
        webSettings.supportZoom()
        //设置内置的缩放组件，默认为false，必须supportZoom和builtZoomControls两者为true，webView才能缩放
        webSettings.builtInZoomControls = true
        //隐藏原生的缩放控件
        webSettings.displayZoomControls = false
        //关闭webView中缓存
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        //设置可以访问文件
        webSettings.allowFileAccess = true
        //支持JS打开新窗口
        webSettings.javaScriptCanOpenWindowsAutomatically = false
        //支持自动加载图片
        webSettings.loadsImagesAutomatically = true
        //设置编码格式
        webSettings.defaultTextEncodingName = "utf-8"

        web_view.webViewClient = WebViewClient()

    }

    override fun initData() {
        toolbar.title = title
        web_view.loadUrl(url)
    }

    override fun onDestroy() {
        web_view?.let {
            it.loadDataWithBaseURL(null,"","text/html","utf-8",null)
            it.clearHistory()
            var viewGroup =  it.parent as ViewGroup
            viewGroup.removeView(web_view)
            web_view.destroy()
        }
        super.onDestroy()
    }
}