package com.alphaStore.user.contract.enumRepo

import com.alphaStore.user.entity.enumsEntity.ProductMainCategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductMainCategoryRepository : JpaRepository<ProductMainCategoryEntity, String> {

    @Query(
        value =
        "SELECT * from product_main_category "+
                "WHERE value = :value ",
        nativeQuery = true
    )
    fun findByValue(value: String) : List<ProductMainCategoryEntity>
}