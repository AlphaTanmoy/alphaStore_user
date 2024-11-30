package com.alphaStore.user.contract.aggregator

import com.alphaStore.user.contract.repo.JWTBlackListRepo
import com.alphaStore.user.entity.JWTBlackList
import com.alphaStore.user.enums.DataStatus
import org.springframework.stereotype.Component

@Component
class JWTBlackListRepoAggregator(
    private val jwtBlackListRepo: JWTBlackListRepo
) {

    fun save(entity: JWTBlackList): JWTBlackList {
        return jwtBlackListRepo.save(entity)
    }

    fun saveAll(entities: List<JWTBlackList>) {
        jwtBlackListRepo.saveAll(entities)
    }

    fun findByMerchantIdAndDataStatus(
        merchantId: String,
        dataStatus: DataStatus,
        forceMaster: Boolean
    ): List<JWTBlackList> {
        return jwtBlackListRepo.findByMerchantIdAndDataStatus(merchantId, dataStatus)
    }
}