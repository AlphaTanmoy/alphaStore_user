package com.alphaStore.user.contract.enumRepo

import com.alphaStore.user.entity.enumsEntity.UserTypeEntity
import com.alphaStore.user.enums.DataStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserTypeRepository : JpaRepository<UserTypeEntity, DataStatus> {

    @Query(
        value =
        "SELECT * from user_type "+
                "WHERE value = :value ",
        nativeQuery = true
    )
    fun findByValue(value: String) : List<UserTypeEntity>
}