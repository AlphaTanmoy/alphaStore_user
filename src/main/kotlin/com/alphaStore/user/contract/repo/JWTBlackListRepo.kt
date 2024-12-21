package com.alphaStore.user.contract.repo


import com.alphaStore.user.entity.JWTBlackList
import com.alphaStore.user.enums.DataStatus
import org.springframework.data.jpa.repository.JpaRepository

interface JWTBlackListRepo : JpaRepository<JWTBlackList, String> {

    fun findByUserIdAndDataStatus(
        userId: String,
        dataStatus: DataStatus = DataStatus.ACTIVE
    ): List<JWTBlackList>

    fun findByDataStatus(dataStatus: DataStatus = DataStatus.ACTIVE): List<JWTBlackList>

    fun findTop1ByOrderByCreatedDateAsc(): List<JWTBlackList>
    fun findTop1ByOrderByCreatedDateDesc(): List<JWTBlackList>
    fun countByDataStatus(dataStatus: DataStatus = DataStatus.ACTIVE): Long

}