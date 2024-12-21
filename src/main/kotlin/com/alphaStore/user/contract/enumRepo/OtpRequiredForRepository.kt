package com.alphaStore.user.contract.enumRepo

import com.alphaStore.user.entity.enumsEntity.OtpRequiredForEntity
import com.alphaStore.user.enums.DataStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface OtpRequiredForRepository : JpaRepository<OtpRequiredForEntity, DataStatus> {

    @Query(
        value =
        "SELECT * from otp_required_for_entity "+
                "WHERE value = :value ",
        nativeQuery = true
    )
    fun findByValue(value: String) : List<OtpRequiredForEntity>
}