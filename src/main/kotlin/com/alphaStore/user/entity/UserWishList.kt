package com.alphaStore.user.entity

import com.alphaStore.user.entity.superentity.SuperEntityWithIdCreatedLastModifiedDataStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity(name = "user_wish_list")
data class UserWishList (
    @Column
    var productList: List<ProductInfo> = ArrayList()
): SuperEntityWithIdCreatedLastModifiedDataStatus()