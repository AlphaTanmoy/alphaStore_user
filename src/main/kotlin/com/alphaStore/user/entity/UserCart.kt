package com.alphaStore.user.entity

import com.alphaStore.user.entity.superentity.SuperEntityWithIdCreatedLastModifiedDataStatus
import com.fasterxml.jackson.annotation.JsonFilter
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity(name = "user_cart")
data class UserCart(
    @Column
    var productList: List<ProductInfo> = ArrayList()
): SuperEntityWithIdCreatedLastModifiedDataStatus()

@JsonFilter("PUserCartFilter")
class UserCartMixIn