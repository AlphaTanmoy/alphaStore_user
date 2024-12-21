package com.alphaStore.user.entity.enumsEntity

import com.alphaStore.user.entity.superentity.SuperEntityWithIdCreatedLastModified
import com.alphaStore.user.enums.DataStatus
import jakarta.persistence.*

@Entity
@Table(name = "data_status")
data class DataStatusEntity(
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    var value: DataStatus = DataStatus.INACTIVE,

    @Column(nullable = false)
    val nameDescriptor: String = ""
) : SuperEntityWithIdCreatedLastModified()