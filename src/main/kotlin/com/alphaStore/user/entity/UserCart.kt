package com.alphaStore.user.entity

import com.alphaStore.user.entity.superentity.SuperEntityWithIdCreatedLastModifiedDataStatus
import com.fasterxml.jackson.annotation.JsonFilter
import jakarta.persistence.*

@Entity(name = "user_cart")
data class UserCart(
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(
        name = "product_info",
        joinColumns = [JoinColumn(name = "cart_id")],
        inverseJoinColumns = [JoinColumn(name = "product_id")]
    )
    var productInfo: List<ProductInfo> = ArrayList()
): SuperEntityWithIdCreatedLastModifiedDataStatus()

@JsonFilter("PUserCartFilter")
class UserCartMixIn