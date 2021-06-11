package com.xt.garbage.bean

import com.contrarywind.interfaces.IPickerViewData

data class JsonBean(
    var city: List<City>,
    var name: String
) : IPickerViewData{
    data class City(
        var area: List<String>,
        var name: String
    )

    override fun getPickerViewText(): String {
        return this.name
    }
}
