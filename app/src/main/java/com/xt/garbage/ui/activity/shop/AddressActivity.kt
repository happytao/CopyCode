package com.xt.garbage.ui.activity.shop

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.view.OptionsPickerView
import com.xt.garbage.R
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.bean.JsonBean
import com.xt.garbage.bean.shop.BatchDetailsBean
import com.xt.garbage.bean.shop.GetAddressResultBean
import com.xt.garbage.constant.RoutePathConstant
import com.xt.garbage.netSubscribe.shop.ShopSubscribe
import com.xt.garbage.netapi.OnSuccessAndFaultListener
import com.xt.garbage.netapi.OnSuccessAndFaultSub
import com.xt.garbage.utils.GetJsonDataUtil
import com.xt.garbage.utils.GsonUtils
import com.xt.garbage.wigdt.Toolbar
import kotlinx.android.synthetic.main.activity_address.*
import org.json.JSONArray
import java.lang.Exception
import kotlin.concurrent.thread

@Route(path = RoutePathConstant.ADD_ADDRESS)
class AddressActivity : BaseActivity(), View.OnClickListener {
    private var addressCode:Int = 0
    private var options1Item:MutableList<JsonBean> = ArrayList()
    private var options2Item:MutableList<MutableList<String>> = ArrayList()
    private var options3Item:MutableList<MutableList<MutableList<String>>> = ArrayList()
    private var thread:Thread? = null
    private var name:String = ""
    private var province:String = ""
    private var city:String = ""
    private var area:String = ""
    private var detailAddress:String = ""
    private var mobile:String = ""
    private var id:String = ""
    private var isUse:Boolean = false
    private var addressRespBean:BatchDetailsBean.ResultDTO.AddressRespDTO? = null

    private var mHandler:Handler = Handler(Looper.getMainLooper()){
        when(it.what) {
            MSG_LOAD_DATA -> {
                if(thread == null) {
                    thread = Thread{
                        initJsonData()
                    }
                    thread!!.start()
                }
                return@Handler true
            }

            MSG_LOAD_SUCCESS -> {
                isLoaded = true
                return@Handler true
            }

            MSG_LOAD_FAILED -> {
                runOnUiThread {
                    showToast("数据加载失败")
                }
                return@Handler true
            }

            else -> {
                return@Handler true
            }

        }
    }

    private fun initJsonData() {
        var jsonData:String = GetJsonDataUtil.getJson(this,"province.json")
        var jsonBean:MutableList<JsonBean> = parseData(jsonData)

        options1Item = jsonBean

        for (index in jsonBean.indices) {
            var cityList:MutableList<String> = ArrayList()
            var provinceAreaList:MutableList<MutableList<String>> = ArrayList()

            for (index_2 in jsonBean[index].city.indices) {
                var cityName:String = jsonBean[index].city[index_2].name
                cityList.add(cityName)
                var cityAreaList:MutableList<String> = ArrayList()
                cityAreaList.addAll(jsonBean[index].city[index_2].area)
                provinceAreaList.add(cityAreaList)

            }
            options2Item.add(cityList)

            options3Item.add(provinceAreaList)
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS)



    }

    private fun parseData(result: String): MutableList<JsonBean> {
        var details:MutableList<JsonBean> = ArrayList()
        try {
            var data:JSONArray = JSONArray(result)
            for (index in 0 until data.length()) {
                var entity:JsonBean = GsonUtils.fromJson(data.optJSONObject(index).toString(),JsonBean::class.java)
                details.add(entity)
            }
        }
        catch (e:Exception) {
            Log.e("TAG",Log.getStackTraceString(e))
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED)
        }
        return details

    }


    companion object {

        private const val MSG_LOAD_DATA:Int = 0x0001
        private const val MSG_LOAD_SUCCESS:Int = 0X002
        private const val MSG_LOAD_FAILED:Int = 0x0003
        private var isLoaded:Boolean = false

        fun newInstance(activity: Activity?, addressRespBean:GetAddressResultBean.ResultDTO?,code:Int){
            var intent : Intent = Intent(activity,AddressActivity::class.java)
            intent.putExtra("bean",addressRespBean)
            intent.putExtra(RoutePathConstant.ADDRESS_CODE,code)
            activity?.startActivity(intent)

        }
    }
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.select_layout -> {
                showPickerView()
            }
            R.id.btn_send -> {
                if(addressCode == 1) {
                    addAddress()
                }
                else {
                    updateAddress()
                }
            }
        }

    }

    private fun updateAddress() {
        detailAddress = address.text.toString()
        mobile = edit_mobile.text.toString()
        name = edit_name.text.toString()
        if(name.isEmpty()) {
            showToast("请输入收货人名字")
            return
        }
        if(mobile.isEmpty()) {
            showToast("请输入收货人联系电话")
            return
        }
        if(detailAddress.isEmpty()) {
            showToast("请输入收货人收获地址")
            return
        }
        if(city.isEmpty()) {
            showToast("请选择地区")
        }
        ShopSubscribe.updateAddress(province,city,isUse,area,detailAddress,mobile,name,OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                showToast("修改收货地址成功")
                finish()
            }

            override fun onFailed(errorMsg: String?) {
                showToast("请求失败：$errorMsg")
            }
        },this))

    }

    private fun addAddress() {
        detailAddress = address.text.toString()
        mobile = edit_mobile.text.toString()
        name = edit_name.text.toString()
        if(name.isEmpty()) {
            showToast("请输入收货人名字")
            return
        }
        if(mobile.isEmpty()) {
            showToast("请输入收货人联系电话")
            return
        }
        if(detailAddress.isEmpty()) {
            showToast("请输入收货人收获地址")
            return
        }
        if(city.isEmpty()) {
            showToast("请选择地区")
        }
        ShopSubscribe.addAddress(province,city,isUse,area,detailAddress,mobile,name,OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
                result?.let {
                    showToast("新增收货地址成功")
                    finish()
                }
            }

            override fun onFailed(errorMsg: String?) {
                showToast("请求失败：$errorMsg")
            }
        },this))

    }

    private fun showPickerView() {

        var pvOptions:OptionsPickerView<Any> = OptionsPickerBuilder(this){ options1,options2,options3,_->
            var opt1tx:String = if(options1Item.size > 0) options1Item[options1].pickerViewText else ""
            var opt2tx:String = if(options2Item.size > 0 && options2Item[options1].size > 0) options2Item[options1][options2] else ""
            var opt3tx:String = if(options2Item.size > 0 && options3Item[options1].size > 0 && options3Item[options1][options2].size > 0) options3Item[options1][options2][options3] else ""

            var tx:String = opt1tx + opt2tx + opt3tx
            province = opt1tx
            city = opt2tx
            area = opt3tx
            tv_city.text = tx
            tv_city.setTextColor(Color.parseColor("#000000"))
        }
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK)
                .setContentTextSize(20)
                .build()

        pvOptions.setPicker(options1Item.toList(),options2Item.toList(),options3Item.toList())
        pvOptions.show()

    }

    override fun initLayout(): Int {
        return R.layout.activity_address
    }

    override fun initView() {
        toolbar.setOnToolbarOnClickListener(object : Toolbar.ToolbarClickListener{
            override fun leftClick() {
                finish()
            }

            override fun rightClick() {

            }
        })

        select_layout.setOnClickListener(this)
        check.setOnCheckedChangeListener { _, isChecked ->
            isUse = isChecked
        }
        btn_send.setOnClickListener(this)

    }


    override fun initData() {
        mHandler.sendEmptyMessage(MSG_LOAD_DATA)
        addressRespBean = intent.getSerializableExtra("bean") as BatchDetailsBean.ResultDTO.AddressRespDTO?
        addressCode = intent.getIntExtra(RoutePathConstant.ADDRESS_CODE,0)
        if(addressCode == 2) {
            edit_name.setText(addressRespBean?.name)
            edit_mobile.setText(addressRespBean?.mobile)
            address.setText(addressRespBean?.detailAddress)
            tv_city.text = addressRespBean?.province + addressRespBean?.city + addressRespBean?.area
            id = addressRespBean?.id.toString()
            province = addressRespBean?.province.toString()
            city = addressRespBean?.city.toString()
            area = addressRespBean?.area.toString()
            if (addressRespBean?.isUse == true) {
                check.isChecked = true
                isUse = true
            } else {
                check.isChecked = false
                isUse = false
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        if(mHandler != null) {
            mHandler.removeCallbacksAndMessages(null)
        }
    }


}