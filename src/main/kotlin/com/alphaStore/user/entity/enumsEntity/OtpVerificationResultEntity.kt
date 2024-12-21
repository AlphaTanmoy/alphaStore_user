package com.alphaStore.user.entity.enumsEntity

import com.alphaStore.user.entity.superentity.SuperEntityWithIdCreatedLastModified
import com.alphaStore.user.enums.OtpVerificationResult
import jakarta.persistence.*

@Entity
@Table(name = "otp_verification_result_entity")
data class  OtpVerificationResultEntity (
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    var value: OtpVerificationResult = OtpVerificationResult.VERIFIED,

    @Column(nullable = false)
    val nameDescriptor: String = ""
) : SuperEntityWithIdCreatedLastModified()