package com.alphaStore.user.contract.aggregator

import com.alphaStore.user.contract.repo.OtpRepo
import com.alphaStore.user.entity.Otp
import com.alphaStore.user.enums.DataStatus
import com.alphaStore.user.enums.OtpRequiredFor
import org.springframework.stereotype.Component

@Component
class OtpRepoAggregator(
    private val otpRepo: OtpRepo
) {
    fun save(entity: Otp): Otp {
        return otpRepo.save(entity)
    }

    fun saveAll(entities: List<Otp>) {
        otpRepo.saveAll(entities)
    }


    fun findByUserIdAndOtpPurposeAndOtpAndDataStatus(
        userId: String,
        otpRequiredFor: OtpRequiredFor,
        otp: String,
        dataStatus: DataStatus = DataStatus.ACTIVE,
    ): List<Otp> {
        return otpRepo.findByUserIdAndOtpRequiredForAndOtpAndDataStatus(
            userId,
            otpRequiredFor,
            otp,
            dataStatus
        )
    }

    fun findByUserIdAndDataStatus(
        userId: String,
        dataStatus: DataStatus = DataStatus.ACTIVE,
    ): List<Otp> {
        return otpRepo.findByIdAndDataStatus(userId, dataStatus)
    }

    fun findLastOtpsSentToUser(
        userId: String,
        limit: Int,
    ): List<Otp> {
        return otpRepo.findLastOtpsSentToUser(userId, limit)
    }
}