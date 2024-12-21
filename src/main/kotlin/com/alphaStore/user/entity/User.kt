package com.alphaStore.user.entity

import com.alphaStore.user.entity.superentity.SuperEntityWithIdCreatedLastModifiedDataStatus
import com.alphaStore.user.enums.AccessRole
import com.alphaStore.user.enums.UserType
import com.fasterxml.jackson.annotation.JsonFilter
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.Pattern
import java.time.ZonedDateTime

@Entity
@Table(name = "user_table")
data class User (

    var name: String = "",

    @Enumerated(EnumType.STRING)
    var userType: UserType = UserType.MERCHANT,

    @JsonIgnore
    var password: String = "",

    @JsonIgnore
    @OneToMany(targetEntity = PastPassword::class, fetch = FetchType.EAGER)
    @JoinTable(
        name = "past_passwords",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "past_password_id")],
    )
    var pastPasswords: List<PastPassword> = ArrayList(),

    @Column(nullable = true)
    var passwordCreatedOrLastUpdated: ZonedDateTime? = null,

    @field:Pattern(
        regexp = "\\d{10,15}",
        message = "Merchant phone number must be between 10 and 15 digits"
    )
    var phone: String = "",

    @Column(nullable = false)
    var country: String = "",

    @Column(nullable = false, unique = true)
    var emailId: String = "",

    @Column(nullable = false)
    var address: String = "",

    var userCartId: String = "",

    var wishListId: String = "",

    var complainRaised: Boolean = false,

    var complainId: String = "",

    var feedbackGiven: Boolean = false,

    var feedbackId: String = "",

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var accessRole: AccessRole = AccessRole.USER,

    ): SuperEntityWithIdCreatedLastModifiedDataStatus()

@JsonFilter("UserFilter")
class UserMixIn