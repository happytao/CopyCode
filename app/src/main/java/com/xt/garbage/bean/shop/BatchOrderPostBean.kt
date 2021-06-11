package com.xt.garbage.bean.shop

data class BatchOrderPostBean(
    var addressId: String,
    var distributionWay: Boolean,
    var freightChargeWay: Boolean,
    var orderRemark: String,
    var parentOrderId: String
)