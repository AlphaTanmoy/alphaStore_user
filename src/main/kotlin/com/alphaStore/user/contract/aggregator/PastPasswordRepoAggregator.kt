package com.alphaStore.user.contract.aggregator

import com.alphaStore.user.contract.repo.PastPasswordRepo
import com.alphaStore.user.entity.PastPassword
import org.springframework.stereotype.Component

@Component
class PastPasswordRepoAggregator(
    private val pastPasswordRepo: PastPasswordRepo
) {
    fun save(entity: PastPassword): PastPassword {
        return pastPasswordRepo.save(entity)
    }

    fun saveAll(entities: List<PastPassword>) {
        pastPasswordRepo.saveAll(entities)
    }

}