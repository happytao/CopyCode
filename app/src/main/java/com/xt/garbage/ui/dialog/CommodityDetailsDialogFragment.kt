package com.xt.garbage.ui.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.nex3z.flowlayout.FlowLayout
import com.xt.garbage.R
import com.xt.garbage.adapter.shop.AttrAdapter
import com.xt.garbage.base.BaseConstant
import com.xt.garbage.bean.shop.AddCarBean
import com.xt.garbage.bean.shop.BatchDetailsBean
import com.xt.garbage.bean.shop.CarCreateOrderResultBean
import com.xt.garbage.bean.shop.CommodityDetailsBean
import com.xt.garbage.netSubscribe.shop.ShopSubscribe
import com.xt.garbage.netapi.OnSuccessAndFaultListener
import com.xt.garbage.netapi.OnSuccessAndFaultSub
import com.xt.garbage.ui.activity.shop.ConfirmOrderDetailsActivity
import com.xt.garbage.utils.DimensionUtils
import com.xt.garbage.utils.GsonUtils
import com.xt.garbage.utils.ImageLoaderUtil
import com.xt.garbage.utils.TextUtils
import io.reactivex.rxjava3.observers.DisposableObserver
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.dialog_commodity_details.*

/**
 *@author:DIY
 *@date: 2021/6/6
 */
class CommodityDetailsDialogFragment : DialogFragment(), View.OnClickListener {

    private var commodityBean:CommodityDetailsBean.Result? = null
    private var mSpeList:MutableList<CommodityDetailsBean.Result.Spec.Attr> = ArrayList()
    private var mAttrList:MutableList<CommodityDetailsBean.Result.Spec> = ArrayList()
    private var attrAdapter:AttrAdapter? = null
    private var isCar:Boolean = true
    private var toSize:String = ""
    private var mGoodNum:Int = 0

    companion object {
        const val TAG = "CommodityDetailsDialogFragment"
        fun newInstance(commodityBean:CommodityDetailsBean.Result,isCar:Boolean): CommodityDetailsDialogFragment {
            val instance:CommodityDetailsDialogFragment by lazy {
                CommodityDetailsDialogFragment()
            }
            var bundle:Bundle = Bundle()
            bundle.putSerializable("commodity",commodityBean)
            bundle.putBoolean("isCar",isCar)
            instance.arguments = bundle
            return instance
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bundle: Bundle? = arguments
        bundle?.let {
            commodityBean = it.getSerializable("commodity") as CommodityDetailsBean.Result?
            isCar = it.getBoolean("isCar")
        }
        for (index in commodityBean?.specList?.indices!!) {
            for (index_2 in commodityBean?.specList!![index].attrList.indices) {
                if(index_2 == 0){
                    commodityBean?.specList!![index].attrList[index_2].isTrue = true
                    if(index == 0) {
                        toSize = "已选" + "'" + commodityBean?.specList!![index].attrList[index_2].attrName + "'"
                    }
                    else {
                        toSize = "$toSize;" + commodityBean?.specList!![index].attrList[index_2].attrName + "'"
                    }

                }
                else {
                    commodityBean?.specList!![index].attrList[index_2].isTrue = false
                }

            }
        }
        mAttrList.addAll(commodityBean?.specList!!)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        var window: Window = dialog?. window!!
        window.decorView.setPadding(0,0,0,0)
        var lp:WindowManager.LayoutParams = window?.attributes!!
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.BOTTOM
        lp.windowAnimations = R.style.BottomDialogAnimation
        window.attributes = lp
        window.setBackgroundDrawable(ColorDrawable())
        val view:View = inflater.inflate(R.layout.dialog_commodity_details,null)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {

        attr_recyclerView.layoutManager = LinearLayoutManager(context)
        btn_send.setOnClickListener(this)
        reduce.setOnClickListener(this)
        add.setOnClickListener(this)
        initData()

    }

    private fun initData() {
        ImageLoaderUtil.loadCustRoundCircleImage(context,BaseConstant.URLPREFIX + commodityBean?.goodsResourceUrl,logo,RoundedCornersTransformation.CornerType.ALL,8)
        when(commodityBean?.goodsSellType) {
            1 -> {
                setMoneyText(money,commodityBean?.goodsScorePrice.toString() + "积分")
            }

            2 -> {
                setMoneyText(money,commodityBean?.goodsCashPrice.toString() + "元")
            }

            3 -> {
                setMoneyText(money,commodityBean?.goodsCashPrice.toString() + "元+" + commodityBean?.goodsScorePrice.toString() + "积分")
            }
            else ->{}
        }
        type.text = toSize
        mGoodNum = 1
        commodity_number.text = mGoodNum.toString()
        if(isCar) btn_send.text = "加入购物车" else btn_send.text = "立即兑换"
        initAdapter()


    }

    private fun initAdapter() {
        attrAdapter = AttrAdapter(mAttrList)
        attr_recyclerView.adapter = attrAdapter
        attrAdapter?.setOnFlowItemClick(object : AttrAdapter.FlowItemClick {
            @SuppressLint("NewApi")
            override fun onItemClick(view: View, position: Int, holder: BaseViewHolder) {
                var flowLayout: FlowLayout = holder.getView(R.id.size_flow)
                for (index in 0 until flowLayout.childCount) {
                    var textView: TextView = flowLayout.getChildAt(index) as TextView
                    if (view == flowLayout.getChildAt(index)) {
                        commodityBean?.specList!![position].attrList[index].isTrue = true
                        textView.background = resources.getDrawable(R.drawable.bg_commodity_selector_text_true, null)
                        textView.setTextColor(resources.getColor(R.color.color_00BF60, null))
                    } else {
                        commodityBean?.specList!![position].attrList[index].isTrue = false
                        textView.background = resources.getDrawable(R.drawable.bg_commodity_selector_text, null)
                        textView.setTextColor(resources.getColor(R.color.color_000000, null))
                    }
                }
                var selectSize: String = "已选"
                commodityBean?.specList?.let {
                    for (index in commodityBean!!.specList.indices) {
                        for (index_2 in commodityBean!!.specList[index].attrList.indices) {
                            if(commodityBean!!.specList[index].attrList[index_2].isTrue) {
                                selectSize = selectSize + "'" + commodityBean!!.specList[index].attrList[index_2].attrName + "'"
                            }
                        }
                    }
                }

                type.text = selectSize
                when(commodityBean?.goodsSellType) {
                    1 -> {
                        setMoneyText(money, commodityBean?.specList!![0].attrList[position].goodsScorePrice.toString() + "积分")
                    }

                    2 -> {
                        setMoneyText(money, commodityBean?.specList!![0].attrList[position].goodsCashPrice.toString() + "元")
                    }
                    3 -> {
                        setMoneyText(money, commodityBean?.specList!![0].attrList[position].goodsCashPrice.toString() + "元+" +
                        commodityBean?.specList!![0].attrList[position].goodsScorePrice.toString() + "积分")
                    }
                }
            }
        })
    }

    private fun setMoneyText(textView: TextView, text: String) {
        TextUtils.getBuilder()
                .setSize(text,22,0,text.length-2)
                .setSize(14,text.length-2,text.length)
                .into(textView)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.add -> {
                mGoodNum++
                commodity_number.text = mGoodNum.toString()
            }
            R.id.reduce -> {
                mGoodNum--
                if(mGoodNum <= 0) mGoodNum = 1
                commodity_number.text = mGoodNum.toString()
            }
            R.id.btn_send -> {
                var spec:AddCarBean.Spec? = null
                for (index in commodityBean?.specList?.indices!!) {
                    for (index_2 in commodityBean?.specList!![index].attrList.indices) {
                        if(commodityBean?.specList!![index].attrList[index_2].isTrue) {
                            spec = AddCarBean.Spec(
                                    refSpecId = commodityBean?.specList!![index].id.toString(),
                                    refSpecName = commodityBean?.specList!![index].specName,
                                    refAttrName = commodityBean?.specList!![index].attrList[index_2].attrName,
                                    refSpecAttrId = commodityBean?.specList!![index].attrList[index_2].id.toString()
                            )
                        }
                    }
                }

                if(isCar) {
                    addCar(mGoodNum,spec,commodityBean?.id)
                }
                else {
                    createOrder(mGoodNum,spec,commodityBean?.id)
                }
            }
        }
    }

    private fun createOrder(buyNum: Int, spec: AddCarBean.Spec?, refGoodsId: Long?) {
        ShopSubscribe.createOrder(buyNum,spec,refGoodsId!!,OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                result?.let {
                    var carCreateOrderResultBean:CarCreateOrderResultBean = GsonUtils.fromJson(it,CarCreateOrderResultBean::class.java)
                    if(carCreateOrderResultBean != null) {
                        getParentOrderDetails(carCreateOrderResultBean.result.parentOrderId)
                    }

                }
            }

            override fun onFailed(errorMsg: String?) {
                Toast.makeText(context,"请求失败：$errorMsg",Toast.LENGTH_SHORT).show()
            }
        }))

    }

    private fun getParentOrderDetails(parentOrderId: String) {
        ShopSubscribe.getParentOrderDetails(parentOrderId,OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                result?.let {
                    var batchDetailsBean:BatchDetailsBean = GsonUtils.fromJson(result,BatchDetailsBean::class.java)
                    if(batchDetailsBean != null) {
                        try {
                            ConfirmOrderDetailsActivity.newInstance(activity!!,batchDetailsBean)
                        } catch (e: Exception) {
                            Log.e("TAG",Log.getStackTraceString(e))
                        }

                    }
                }
            }

            override fun onFailed(errorMsg: String?) {
                Toast.makeText(context,"请求失败：$errorMsg",Toast.LENGTH_SHORT).show()
            }
        }))

    }

    private fun addCar(buyNum: Int, spec: AddCarBean.Spec?, refGoodsId: Long?) {
        ShopSubscribe.addCar(buyNum,spec,refGoodsId!!,OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                Toast.makeText(context,"加入购物车成功",Toast.LENGTH_SHORT).show()
                dismiss()
            }

            override fun onFailed(errorMsg: String?) {
                Toast.makeText(context,"请求失败：$errorMsg",Toast.LENGTH_SHORT).show()
            }
        },context))

    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(getScreenWidth(context),DimensionUtils.dip2px(activity!!,680f))
    }

    private fun getScreenWidth(context: Context?): Int {
        var wm:WindowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var dm: DisplayMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        return dm.widthPixels


    }
}