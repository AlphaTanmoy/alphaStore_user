package com.alphaStore.user.entity.enumsEntity

import com.alphaStore.user.entity.superentity.SuperEntityWithIdCreatedLastModified
import com.alphaStore.user.enums.AccessRole
import jakarta.persistence.*


@Entity
@Table(name = "access_role")
data class AccessRoleEntity(
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    var value: AccessRole = AccessRole.MERCHANT,
    @Column(nullable = false)
    val nameDescriptor: String = ""
) : SuperEntityWithIdCreatedLastModified()