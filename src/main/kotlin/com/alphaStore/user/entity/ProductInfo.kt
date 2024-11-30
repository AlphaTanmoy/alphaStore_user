package com.alphaStore.user.entity

import com.alphaStore.user.entity.superentity.SuperEntityWithIdCreatedLastModifiedDataStatus
import com.alphaStore.user.enums.ProductMainCategory
import com.alphaStore.user.enums.ProductSubCategory
import com.fasterxml.jackson.annotation.JsonFilter
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Entity(name = "product_info")
data class ProductInfo(
    @field:NotBlank(message = "Product name must not be blank")
    @Column(nullable = false)
    val productName: String = "",

    @field:NotNull(message = "Product price must not be null")
    @field:Min(value = 1, message = "Product price must be greater than or equal to 1")
    @Column(nullable = false)
    val productPrice: Long = 0,

    @field:NotNull(message = "Number of products at the store must not be null")
    @field:Min(value = 0, message = "Number of products at the store cannot be negative")
    @Column(nullable = false)
    val numberOfProductsPresentAtStore: Long = 0,

    @field:NotNull(message = "Product main category must not be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val productMainCategory: ProductMainCategory,

    @field:NotNull(message = "Product sub-category must not be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val productSubCategory: ProductSubCategory,

    @Column(nullable = false)
    val productLink: String = "",

    val isWishListed: Boolean = false,

    val isInCart: Boolean = false,

): SuperEntityWithIdCreatedLastModifiedDataStatus()
@JsonFilter("ProductInfoFilter")
class ProductInfoMixIn