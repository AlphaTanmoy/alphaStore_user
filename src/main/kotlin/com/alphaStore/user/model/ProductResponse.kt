package com.alphaStore.user.model

import com.alphaStore.user.enums.DataStatus
import java.time.Instant

data class ProductResponse(
    var id: String,
    var productName: String,
    var productPrice: Int,
    var numberOfProductsPresentAtStore: Int,
    var productMainCategory: String,
    var productSubCategory: String,
    var createdDate: Instant,
    var merchantId: String,
    var status: DataStatus
)
