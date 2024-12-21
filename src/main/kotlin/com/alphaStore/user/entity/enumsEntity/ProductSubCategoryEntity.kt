package com.alphaStore.user.entity.enumsEntity

import com.alphaStore.user.entity.superentity.SuperEntityWithIdCreatedLastModified
import com.alphaStore.user.enums.ProductSubCategory
import jakarta.persistence.*

@Entity
@Table(name = "product_sub_category")
data class ProductSubCategoryEntity (
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    var value: ProductSubCategory = ProductSubCategory.NOT_APPLICABLE,

    @Column(nullable = false)
    val nameDescriptor: String = ""
) : SuperEntityWithIdCreatedLastModified()