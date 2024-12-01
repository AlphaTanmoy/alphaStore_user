package com.alphaStore.user.contract.aggregator

import com.alphaStore.user.contract.repo.UserCartRepo
import com.alphaStore.user.entity.UserCart
import org.springframework.stereotype.Component

@Suppress("UNREACHABLE_CODE")
@Component
class UserCartAggregator (
    private val userCartRepo: UserCartRepo
) {

    fun save(entity: UserCart): UserCart {
        return userCartRepo.save(entity)
    }

}