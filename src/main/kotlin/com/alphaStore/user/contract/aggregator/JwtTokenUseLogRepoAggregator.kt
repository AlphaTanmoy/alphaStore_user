package com.alphaStore.user.contract.aggregator

import com.alphaStore.user.contract.JwtTokenUseLogRepoAggregatorContract
import com.alphaStore.user.contract.repo.JwtTokenUseLogRepo
import com.alphaStore.user.entity.JwtTokenUseLog
import com.alphaStore.user.enums.DataStatus
import org.springframework.stereotype.Component

@Component
class JwtTokenUseLogRepoAggregator(
    private val jwtTokenUseLogRepo: JwtTokenUseLogRepo
) : JwtTokenUseLogRepoAggregatorContract {

    override fun save(entity: JwtTokenUseLog): JwtTokenUseLog {
        return jwtTokenUseLogRepo.save(entity)
    }

    override fun saveAll(entities: List<JwtTokenUseLog>) {
        jwtTokenUseLogRepo.saveAll(entities)
    }

    override fun dropTable() {
        jwtTokenUseLogRepo.dropTable()
    }

    override fun findByTokenHashAndDataStatus(
        tokenHash: String,
        dataStatus: DataStatus,
    ): List<JwtTokenUseLog> {
        return jwtTokenUseLogRepo.findByTokenHashAndDataStatus(tokenHash, dataStatus)
    }

    override fun executeFunction(queryToExecute: String): List<JwtTokenUseLog> {
        return jwtTokenUseLogRepo.executeFunction(queryToExecute)
    }
}