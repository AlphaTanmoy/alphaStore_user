package com.alphaStore.user.entity.enumsEntity

import com.alphaStore.user.entity.superentity.SuperEntityWithIdCreatedLastModified
import com.alphaStore.user.enums.UserType
import jakarta.persistence.*

@Entity
@Table(name = "user_type")
data class UserTypeEntity (
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    var value: UserType = UserType.USER,

    @Column(nullable = false)
    val nameDescriptor: String = ""
) : SuperEntityWithIdCreatedLastModified()