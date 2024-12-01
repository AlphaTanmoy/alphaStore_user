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
    var status: DataStatus,
    var createdDate: Instant
)
