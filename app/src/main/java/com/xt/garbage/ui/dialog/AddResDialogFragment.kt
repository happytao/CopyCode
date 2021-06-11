package com.xt.garbage.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.View.inflate
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.xt.garbage.R
import com.xt.garbage.adapter.shop.AddressDialogAdapter
import com.xt.garbage.bean.shop.GetAddressResultBean
import com.xt.garbage.bean.workmain.GetAppointmentOrderStatusBean
import com.xt.garbage.constant.EventCode
import com.xt.garbage.netSubscribe.shop.ShopSubscribe
import com.xt.garbage.netapi.OnSuccessAndFaultListener
import com.xt.garbage.netapi.OnSuccessAndFaultSub
import com.xt.garbage.ui.activity.shop.AddressActivity
import com.xt.garbage.utils.EventBusUtils
import com.xt.garbage.utils.GsonUtils
import com.xt.garbage.workmain.EventMessage
import com.yanzhenjie.recyclerview.SwipeMenuItem
import kotlinx.android.synthetic.main.dialog_address.*
import kotlinx.android.synthetic.main.item_address_dialog_foot.*
import java.util.zip.Inflater

/**
 *@author:DIY
 *@date: 2021/5/25
 */
class AddResDialogFragment : DialogFragment(), View.OnClickListener {
    companion object {
        const val TAG:String = "AddResDialogFragment"
        @JvmStatic
        val instance:AddResDialogFragment by lazy {
            AddResDialogFragment()
        }
    }
    private var mList:MutableList<GetAddressResultBean.ResultDTO> = ArrayList()
    private var addressDialogAdapter:AddressDialogAdapter? = null
    private var footView:View? = null
    private var btnSend:RelativeLayout? = null
    private var imgClose:ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        var window: Window? = dialog?.window
        window?.decorView?.setPadding(0,0,0,0)
        var lp: WindowManager.LayoutParams? = window?.attributes
        lp?.width = WindowManager.LayoutParams.MATCH_PARENT
        lp?.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp?.gravity = Gravity.BOTTOM
        lp?.windowAnimations = R.style.BottomDialogAnimation
        window?.attributes = lp
        window?.setBackgroundDrawable(ColorDrawable())
        val view:View = inflater.inflate(R.layout.dialog_address,null)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        footView = layoutInflater.inflate(R.layout.item_address_dialog_foot,null)
        addressDialogAdapter = AddressDialogAdapter(mList)
        footView?.let { addressDialogAdapter?.addFooterView(it) }
        var manager:LinearLayoutManager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.VERTICAL
        recyclerview.layoutManager = manager
        btnSend = footView!!.findViewById(R.id.btn_send)
        btnSend?.setOnClickListener(this)
        close.setOnClickListener(this)
        initData()

    }

    private fun initData() {
        recyclerview.setSwipeMenuCreator{ _,rightMenu,_ ->
            var deleteItem:SwipeMenuItem = SwipeMenuItem(context)
            deleteItem.setBackgroundColor(Color.parseColor("#FF3D39"))
                    .setText("删除")
                    .setTextColor(Color.WHITE)
                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)
                    .setWidth(170)

            rightMenu.addMenuItem(deleteItem)

        }

        recyclerview.setOnItemMenuClickListener{ menuBridge,adapterPosition ->
            menuBridge.closeMenu()
            deleteAddress(mList[adapterPosition].id.toInt())
            mList.removeAt(adapterPosition)
            addressDialogAdapter?.notifyDataSetChanged()




        }
        recyclerview.addItemDecoration(DividerItemDecoration(activity,DividerItemDecoration.VERTICAL))
        recyclerview.adapter = addressDialogAdapter
        addressDialogAdapter?.setOnItemClickListener { _, _, position ->
            EventBusUtils.post(EventMessage(EventCode.CONFIRM_ADDRESS,mList[position]))
            dismiss()
        }

        addressDialogAdapter?.addChildClickViewIds(R.id.up)
        addressDialogAdapter?.setOnItemChildClickListener { adapter, view, position ->
            if(view.id == R.id.up) {
                AddressActivity.newInstance(activity,mList[position],2)

            }
        }



    }

    private fun getAddress() {
        ShopSubscribe.getAddress(OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                result?.let {
                    var getAddressResultBean:GetAddressResultBean = GsonUtils.fromJson(result,GetAddressResultBean::class.java)
                    getAddressResultBean?.let {
                        mList.addAll(getAddressResultBean.result)
                        addressDialogAdapter?.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailed(errorMsg: String?) {
                TODO("Not yet implemented")
            }
        }))
    }

    private fun deleteAddress(id: Int) {
        ShopSubscribe.getAddress(OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                result?.let {
                    var getAddressResultBean:GetAddressResultBean = GsonUtils.fromJson(result,GetAddressResultBean::class.java)
                    getAddressResultBean?.let {
                        mList.addAll(getAddressResultBean.result)
                        addressDialogAdapter?.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailed(errorMsg: String?) {
            }
        }))

    }


    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_send -> {
                try {
                    AddressActivity.newInstance(activity,null,1)
                    dismiss()
                } catch (e: Exception) {
                    Log.e("TAG",Log.getStackTraceString(e))
                }
            }
            R.id.close -> {
                dismiss()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        mList.clear()
    }

    override fun onResume() {
        super.onResume()
        getAddress()
    }
}