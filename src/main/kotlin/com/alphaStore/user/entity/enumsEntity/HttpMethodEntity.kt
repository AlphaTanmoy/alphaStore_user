package com.alphaStore.user.entity.enumsEntity

import com.alphaStore.user.entity.superentity.SuperEntityWithIdCreatedLastModified
import com.alphaStore.user.enums.HttpMethod
import jakarta.persistence.*

@Entity
@Table(name = "http_method")
data class HttpMethodEntity (
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    var value: HttpMethod = HttpMethod.GET,

    @Column(nullable = false)
    val nameDescriptor: String = ""
) : SuperEntityWithIdCreatedLastModified()