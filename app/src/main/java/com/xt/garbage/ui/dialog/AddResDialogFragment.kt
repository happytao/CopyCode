package com.xt.garbage.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
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
        initView(view)
        return view
    }

    private fun initView(view: View) {
        footView = View.inflate(activity,R.layout.item_address_dialog_foot,null)
        var manager:LinearLayoutManager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.VERTICAL
        recyclerview.layoutManager = manager
        btn_send.setOnClickListener(this)
        close.setOnClickListener(this)
        initData()

    }

    private fun initData() {
        addressDialogAdapter = AddressDialogAdapter(mList)
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
            deleteAddress(mList[adapterPosition].id)
            mList.removeAt(adapterPosition)
            addressDialogAdapter?.notifyDataSetChanged()




        }
        footView?.let { addressDialogAdapter?.addFooterView(it) }
        recyclerview.addItemDecoration(DividerItemDecoration(activity,DividerItemDecoration.VERTICAL))
        recyclerview.adapter = addressDialogAdapter
        getAddress()
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
                AddressActivity.newInstance(activity,null,1)
                dismiss()
            }
            R.id.close -> {
                dismiss()
            }
        }
    }
}