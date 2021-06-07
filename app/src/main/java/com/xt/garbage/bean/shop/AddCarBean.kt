package com.xt.garbage.bean.shop

data class AddCarBean(
    var buyNum: Int,
    var refGoodsId: Long,
    var specList: List<Spec>
) {
    data class Spec(
        var refAttrName: String,
        var refSpecAttrId: Int,
        var refSpecId: Int,
        var refSpecName: String
    )
}