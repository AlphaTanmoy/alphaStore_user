package com.alphaStore.user.contract.aggregator

import com.alphaStore.user.contract.repo.UserRepo
import com.alphaStore.user.entity.User
import com.alphaStore.user.enums.DataStatus

class UserRepoAggregator (
    private val userRepo: UserRepo
){

    fun findByIdAndDataStatus(
        id: String,
        dataStatus: DataStatus = DataStatus.ACTIVE,
    ): List<User> {
        val resultFromDb = userRepo.findByIdAndDataStatus(id, dataStatus)
        return resultFromDb
    }

}