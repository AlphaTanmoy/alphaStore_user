package com.alphaStore.user.contract.enumRepo

import com.alphaStore.user.entity.enumsEntity.AccessRoleEntity
import com.alphaStore.user.enums.DataStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AccessRoleRepository : JpaRepository<AccessRoleEntity, DataStatus> {
    @Query(
        value =
        "SELECT * from access_role "+
                "WHERE value = :value ",
        nativeQuery = true
    )
    fun findByValue(value: String) : List<AccessRoleEntity>
}