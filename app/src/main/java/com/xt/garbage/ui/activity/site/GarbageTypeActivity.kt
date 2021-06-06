package com.xt.garbage.ui.activity.site

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.LeadingMarginSpan
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.xt.garbage.R
import com.xt.garbage.adapter.garbage.GarbageTypeAdapter
import com.xt.garbage.adapter.garbage.GarbageTypeFirstAdapter
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.bean.garbage.GarbageTypeBean
import com.xt.garbage.bean.garbage.MySection
import com.xt.garbage.constant.RoutePathConstant
import com.xt.garbage.netSubscribe.garbage.GarbageSubscribe
import com.xt.garbage.netapi.OnSuccessAndFaultListener
import com.xt.garbage.netapi.OnSuccessAndFaultSub
import com.xt.garbage.utils.DimensionUtils
import com.xt.garbage.utils.GsonUtils
import com.xt.garbage.wigdt.Toolbar
import kotlinx.android.synthetic.main.activity_garbage_type.*
import kotlinx.android.synthetic.main.item_garbage_type_head.*
import kotlinx.android.synthetic.main.item_warehouse_list.*

@Route(path = RoutePathConstant.GARBAGE_TYPE)
class GarbageTypeActivity : BaseActivity() {
    private var mFirstList:MutableList<GarbageTypeBean.Result> = ArrayList()
    private var garbageTypeFirstAdapter:GarbageTypeFirstAdapter? = null
    private var garbageTypeAdapter:GarbageTypeAdapter? = null

    private var mCategoryList:MutableList<GarbageTypeBean.Result.CategoryList> = ArrayList()
    private var mySectionList:MutableList<MySection> = ArrayList()
    private var mGarbageTypeBean:GarbageTypeBean? = null
    private var headView: View? = null


    override fun initLayout(): Int {
        return R.layout.activity_garbage_type
    }

    override fun initView() {
        toolbar.setOnToolbarOnClickListener(object : Toolbar.ToolbarClickListener{
            override fun leftClick() {
                finish()
            }

            override fun rightClick() {
            }
        })
        headView = View.inflate(this,R.layout.item_garbage_type_head,null)
        var manager:LinearLayoutManager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        first_recyclerView.layoutManager = manager
        next_recyclerView.layoutManager = GridLayoutManager(this,4)
    }

    override fun initData() {
        initAdapter()
        getGarbageType()
    }

    private fun getGarbageType() {
        GarbageSubscribe.getManyList(OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
                result?.let {
                    mGarbageTypeBean = GsonUtils.fromJson(result, GarbageTypeBean::class.java)
                    mFirstList.addAll(mGarbageTypeBean?.result!!)
                    garbageTypeFirstAdapter?.notifyDataSetChanged()
                    for (index in mFirstList.indices) {
                        mFirstList[index].isTrue = index == 0
                    }
                    for (index in mFirstList[0].categoryList.indices) {
                        mySectionList.add(MySection(true, mFirstList[0].categoryList[index].categoryName))
                        dec.text = getSpannableString(37f, mFirstList[0].categoryDesc)

                        for (index_2 in mFirstList[0].categoryList.indices) {
                            mySectionList.add(MySection(false,mFirstList[0].categoryList[0].categoryList[index_2]))
                        }
                    }
                    garbageTypeAdapter?.notifyDataSetChanged()
                }
            }

            override fun onFailed(errorMsg: String?) {
                showToast("请求失败$errorMsg")
            }
        }))
    }

    private fun initAdapter() {
        garbageTypeFirstAdapter = GarbageTypeFirstAdapter(mFirstList)
        first_recyclerView.adapter = garbageTypeFirstAdapter
        garbageTypeFirstAdapter?.setOnItemClickListener { _, _, position ->
            for (index in mFirstList.indices) {
                mFirstList[index].isTrue = index == position
            }
            garbageTypeFirstAdapter?.notifyDataSetChanged()
            mySectionList.clear()
            for (index in mFirstList[position].categoryList.indices) {
                mySectionList.add(MySection(true,mFirstList[position].categoryList[index].categoryName))
                dec.text = getSpannableString(37f,mFirstList[position].categoryDesc)

                for (index_2 in mFirstList[position].categoryList[0].categoryList.indices) {
                    mySectionList.add(MySection(false,mFirstList[position].categoryList[0].categoryList[index_2]))
                }
            }
            garbageTypeAdapter?.notifyDataSetChanged()
        }

        garbageTypeAdapter = GarbageTypeAdapter(R.layout.item_garbage_type_title,R.layout.item_garbage_cate,mySectionList)
        headView?.let { garbageTypeAdapter?.addHeaderView(it) }
        next_recyclerView.adapter = garbageTypeAdapter


    }

    private fun getSpannableString(length:Float,description:String): CharSequence? {
        var spannableString:SpannableString = SpannableString(description)
        var marginSpanSize:Int = DimensionUtils.dip2px(this,length)
        //首行缩进
        var leadingMarginSpan:LeadingMarginSpan = LeadingMarginSpan.Standard(marginSpanSize,0)
        spannableString.setSpan(leadingMarginSpan,0,description.length,Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        return spannableString.toString()

    }

}