package com.alphaStore.user.contract.enumRepo

import com.alphaStore.user.entity.enumsEntity.ProductSubCategoryEntity
import com.alphaStore.user.enums.DataStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductSubCategoryRepository : JpaRepository<ProductSubCategoryEntity, DataStatus> {

    @Query(
        value =
        "SELECT * from product_sub_category "+
                "WHERE value = :value ",
        nativeQuery = true
    )
    fun findByValue(value: String) : List<ProductSubCategoryEntity>
}