package com.alphaStore.user.entity

import com.alphaStore.user.entity.superentity.SuperEntityWithIdCreatedLastModifiedDataStatus
import com.alphaStore.user.enums.UserType
import com.fasterxml.jackson.annotation.JsonFilter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.sun.tools.javac.code.Attribute.Class
import jakarta.persistence.*
import jakarta.validation.constraints.Pattern
import java.time.ZonedDateTime
import java.util.ArrayList

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
    var email: String = "",

    @OneToOne(targetEntity = UserCart::class, fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_cart",
        joinColumns = [JoinColumn(name= "user_id")],
        inverseJoinColumns = [JoinColumn(name = "user_cart_id")],
    )
    var userCart: UserCart,

    @OneToOne(targetEntity = UserWishList::class, fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_cart",
        joinColumns = [JoinColumn(name= "user_id")],
        inverseJoinColumns = [JoinColumn(name = "user_wish_list_id")],
    )
    var wishList: UserWishList,

    ): SuperEntityWithIdCreatedLastModifiedDataStatus()

@JsonFilter("UserFilter")
class UserMixIn