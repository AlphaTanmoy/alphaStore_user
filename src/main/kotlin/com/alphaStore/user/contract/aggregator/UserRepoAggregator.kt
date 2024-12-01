package com.alphaStore.user.contract.aggregator

import com.alphaStore.user.contract.repo.UserRepo
import com.alphaStore.user.entity.User
import com.alphaStore.user.enums.DataStatus
import org.springframework.stereotype.Component

@Suppress("UNREACHABLE_CODE")
@Component
class UserRepoAggregator (
    private val userRepo: UserRepo
){
    fun save(entity: User): User {
        return userRepo.save(entity)
    }

    fun findByEmailIdAndDataStatus(
        emailId: String,
        dataStatus: DataStatus = DataStatus.ACTIVE
    ) : List<User>{
        val resultFromDb = userRepo.findByEmailIdAndDataStatus(emailId, dataStatus)
        return resultFromDb
    }

    fun findByIdAndDataStatus(
        id: String,
        dataStatus: DataStatus = DataStatus.ACTIVE,
    ): List<User> {
        val resultFromDb = userRepo.findByIdAndDataStatus(id, dataStatus)
        return resultFromDb
    }

    fun findUserById(id: String, dataStatus: DataStatus = DataStatus.ACTIVE): List<User> {
        val resultFromDb = userRepo.findByIdAndDataStatus(id, dataStatus)
        return resultFromDb
    }



}