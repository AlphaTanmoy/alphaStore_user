package com.alphaStore.user.model

import com.alphaStore.user.enums.DataStatus

data class ProductResponse(
    var id: String,
    var productName: String,
    var productPrice: Int,
    var numberOfProductsPresentAtStore: Int,
    var productMainCategory: String,
    var productSubCategory: String,
    var status: DataStatus,
    var userId: String
)
