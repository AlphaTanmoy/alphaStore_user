package com.alphaStore.user.entity.enumsEntity


import com.alphaStore.user.entity.superentity.SuperEntityWithIdCreatedLastModified
import com.alphaStore.user.enums.OtpRequiredFor
import jakarta.persistence.*

@Entity
@Table(name = "otp_required_for_entity")
data class OtpRequiredForEntity (
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    var value: OtpRequiredFor = OtpRequiredFor.LOGIN,

    @Column(nullable = false)
    val nameDescriptor: String = ""
) : SuperEntityWithIdCreatedLastModified()