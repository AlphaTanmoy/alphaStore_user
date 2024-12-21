package com.alphaStore.user.contract.enumRepo

import com.alphaStore.user.entity.enumsEntity.DataStatusEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DataStatusRepository : JpaRepository<DataStatusEntity, String>{

    @Query(
        value =
            "SELECT * from data_status "+
            "WHERE value = :value ",
        nativeQuery = true
    )
    fun findByValue(value: String) : List<DataStatusEntity>
}