package com.alphaStore.user.contract.repo

import com.alphaStore.user.entity.User
import com.alphaStore.user.entity.UserWishList
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.JpaRepository

@Suppress("SqlDialectInspection", "SqlNoDataSourceInspection")
@Primary
interface UserWishListRepo : JpaRepository<UserWishList, String> {
}