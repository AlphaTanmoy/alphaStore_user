package com.alphaStore.user.entity.enumsEntity

import com.alphaStore.user.entity.superentity.SuperEntityWithIdCreatedLastModified
import com.alphaStore.user.enums.ProductMainCategory
import jakarta.persistence.*

@Entity
@Table(name = "product_main_category")
data class ProductMainCategoryEntity (
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    var value: ProductMainCategory = ProductMainCategory.OTHERS,

    @Column(nullable = false)
    val nameDescriptor: String = ""
) : SuperEntityWithIdCreatedLastModified()