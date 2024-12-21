package com.alphaStore.user.contract

import com.alphaStore.user.entity.JwtTokenUseLog
import com.alphaStore.user.enums.DataStatus

interface JwtTokenUseLogRepoAggregatorContract {

    fun save(entity: JwtTokenUseLog): JwtTokenUseLog

    fun saveAll(entities: List<JwtTokenUseLog>)

    fun dropTable()

    fun findByTokenHashAndDataStatus(
        tokenHash: String,
        dataStatus: DataStatus = DataStatus.ACTIVE,
    ): List<JwtTokenUseLog>

    fun executeFunction(queryToExecute: String): List<JwtTokenUseLog>
}