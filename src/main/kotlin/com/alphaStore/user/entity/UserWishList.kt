package com.alphaStore.user.entity

import com.alphaStore.user.entity.superentity.SuperEntityWithIdCreatedLastModifiedDataStatus
import jakarta.persistence.*

@Entity(name = "user_wish_list")
data class UserWishList (
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(
        name = "product_info",
        joinColumns = [JoinColumn(name = "wish_list_id")],
        inverseJoinColumns = [JoinColumn(name = "product_id")]
    )
    var productInfo: List<ProductInfo> = ArrayList()
): SuperEntityWithIdCreatedLastModifiedDataStatus()