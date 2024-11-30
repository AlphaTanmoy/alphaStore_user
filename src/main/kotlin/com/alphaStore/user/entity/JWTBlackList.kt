package com.alphaStore.user.entity

import com.alphaStore.user.entity.superentity.SuperEntityWithIdCreatedLastModifiedDataStatus
import com.fasterxml.jackson.annotation.JsonFilter
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity(name = "jwt_black_lists")
data class JWTBlackList(
    @Column(nullable = false)
    var merchantId: String = "",
) : SuperEntityWithIdCreatedLastModifiedDataStatus()

@JsonFilter("JwtBlackListFilter")
class JWTBlackListMixIn