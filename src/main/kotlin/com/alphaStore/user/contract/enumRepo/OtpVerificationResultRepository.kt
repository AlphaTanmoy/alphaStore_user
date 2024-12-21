package com.alphaStore.user.contract.enumRepo

import com.alphaStore.user.entity.enumsEntity.OtpVerificationResultEntity
import com.alphaStore.user.enums.DataStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface OtpVerificationResultRepository : JpaRepository<OtpVerificationResultEntity, DataStatus> {

    @Query(
        value =
        "SELECT * from otp_verification_result_entity "+
                "WHERE value = :value ",
        nativeQuery = true
    )
    fun findByValue(value: String) : List<OtpVerificationResultEntity>
}