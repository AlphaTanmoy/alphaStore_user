package com.alphaStore.user.contract.repo

import com.alphaStore.user.entity.Otp
import com.alphaStore.user.enums.DataStatus
import com.alphaStore.user.enums.OtpRequiredFor
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface OtpRepo : JpaRepository<Otp, String> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(
        value = "DROP TABLE otps CASCADE",
        nativeQuery = true
    )
    fun dropTable()

    fun findByDataStatus(dataStatus: DataStatus = DataStatus.ACTIVE): List<Otp>
    fun findByIdAndDataStatus(id: String, dataStatus: DataStatus = DataStatus.ACTIVE): List<Otp>

    fun findTop1ByOrderByCreatedDateAsc(): List<Otp>
    fun findTop1ByOrderByCreatedDateDesc(): List<Otp>
    fun countByDataStatus(dataStatus: DataStatus = DataStatus.ACTIVE): Long

    fun findByUserIdAndOtpRequiredForAndOtpAndDataStatus(
        userId: String,
        otpRequiredFor: OtpRequiredFor,
        otp: String,
        dataStatus: DataStatus = DataStatus.ACTIVE
    ): List<Otp>

    fun findByUserIdAndDataStatus(userId: String, dataStatus: DataStatus = DataStatus.ACTIVE): List<Otp>

    @Query(
        value = "select * " +
                "from otps " +
                "where user_id = :userId " +
                "order by created_date desc " +
                "limit :limit",
        nativeQuery = true
    )
    fun findLastOtpsSentToUser(userId: String, limit: Int): List<Otp>
}