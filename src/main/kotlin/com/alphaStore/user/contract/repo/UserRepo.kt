package com.alphaStore.user.contract.repo

import com.alphaStore.user.entity.User
import com.alphaStore.user.enums.DataStatus
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.JpaRepository


@Suppress("SqlDialectInspection", "SqlNoDataSourceInspection")
@Primary
interface UserRepo : JpaRepository<User, String> {

    fun findByIdAndDataStatus(id: String, dataStatus: DataStatus = DataStatus.ACTIVE): List<User>

}