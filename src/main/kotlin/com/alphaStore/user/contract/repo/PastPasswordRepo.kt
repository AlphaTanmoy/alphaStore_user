package com.alphaStore.user.contract.repo

import com.alphaStore.user.entity.PastPassword
import com.alphaStore.user.enums.DataStatus
import org.springframework.data.jpa.repository.JpaRepository

interface PastPasswordRepo : JpaRepository<PastPassword, String> {

    fun findByDataStatus(dataStatus: DataStatus = DataStatus.ACTIVE): List<PastPassword>

    fun findTop1ByOrderByCreatedDateAsc(): List<PastPassword>

    fun findTop1ByOrderByCreatedDateDesc(): List<PastPassword>

    fun findByIdAndDataStatus(id: String, dataStatus: DataStatus = DataStatus.ACTIVE): List<PastPassword>

    fun countByDataStatus(dataStatus: DataStatus = DataStatus.ACTIVE): Long

}