package com.alphaStore.user.entity.superentity

import com.alphaStore.user.enums.DataStatus
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.io.Serializable
import java.time.ZonedDateTime

@MappedSuperclass
abstract class SuperEntityWithCreatedLastModifiedDataStatus(
    @LastModifiedDate
    @UpdateTimestamp
    var lastUpdated: ZonedDateTime = ZonedDateTime.now(),
    @CreatedDate
    @CreationTimestamp
    var createdDate: ZonedDateTime = ZonedDateTime.now(),
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var dataStatus: DataStatus = DataStatus.ACTIVE,
) : Serializable {

    fun <T : SuperEntityWithCreatedLastModifiedDataStatus> createDeepCopy(
        instanceToCopyTo: SuperEntityWithCreatedLastModifiedDataStatus
    ): T {
        @Suppress("UNCHECKED_CAST")
        return instanceToCopyTo.apply {
            lastUpdated = this@SuperEntityWithCreatedLastModifiedDataStatus.lastUpdated
            createdDate = this@SuperEntityWithCreatedLastModifiedDataStatus.createdDate
            dataStatus = this@SuperEntityWithCreatedLastModifiedDataStatus.dataStatus
        } as T
    }
}