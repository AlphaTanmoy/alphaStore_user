package com.alphaStore.user.contract.enumRepo

import com.alphaStore.user.entity.enumsEntity.HttpMethodEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface HttpMethodRepository : JpaRepository<HttpMethodEntity, String>{
    @Query(
        value =
        "SELECT * from http_method "+
                "WHERE value = :value ",
        nativeQuery = true
    )
    fun findByValue(value: String) : List<HttpMethodEntity>
}