package com.alphaStore.user.contract.aggregator

import com.alphaStore.user.contract.repo.UserWishListRepo
import com.alphaStore.user.entity.UserWishList
import org.springframework.stereotype.Component

@Suppress("UNREACHABLE_CODE")
@Component
class UserWishListAggregator(
    private val userWishListRepo: UserWishListRepo
) {

    fun save(entity: UserWishList): UserWishList {
        return userWishListRepo.save(entity)
    }

}