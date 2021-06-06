package com.xt.garbage.ui.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.xt.garbage.adapter.shop.AttrAdapter
import com.xt.garbage.bean.shop.CommodityDetailsBean

/**
 *@author:DIY
 *@date: 2021/6/6
 */
class CommodityDetailsDialogFragment : DialogFragment(), View.OnClickListener {
    private var commodityBean:CommodityDetailsBean.Result? = null
    private var mSpeList:MutableList<CommodityDetailsBean.Result.Spec.Attr> = ArrayList()
    private var mAttrList:MutableList<CommodityDetailsBean.Result.Spec> = ArrayList()
    private var attrAdapter:AttrAdapter? = null
    private var isCar:Boolean? = null
    private var toSize:String = ""

    companion object {
        fun newInstance(commodityBean:CommodityDetailsBean.Result,isCar:Boolean): CommodityDetailsDialogFragment {
            val instance:CommodityDetailsDialogFragment by lazy {
                CommodityDetailsDialogFragment()
            }
            var bundle:Bundle = Bundle()
            bundle.putParcelable("commodity",commodityBean)
            bundle.putBoolean("isCar",isCar)
            instance.arguments = bundle
            return instance
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bundle: Bundle? = arguments
        bundle?.let {
            commodityBean = it.getParcelable("commodity")
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

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}