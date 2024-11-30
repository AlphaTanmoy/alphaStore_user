package com.alphaStore.user.contract.aggregator

import com.alphaStore.user.contract.repo.CountryRepo
import com.alphaStore.user.entity.Country
import com.alphaStore.user.enums.DataStatus
import com.alphaStore.user.model.minifiedImpl.CountryListMinifiedResponseImpl
import com.alphaStore.user.model.minifiedImpl.FetchMostRecentMinifiedImpl
import com.alphaStore.user.reqres.AggregatorListResponse
import com.alphaStore.user.reqres.AggregatorResponse
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.*
import kotlin.collections.ArrayList

@Suppress("UNREACHABLE_CODE")
@Component
class CountryRepoAggregator(
    private val countryRepo: CountryRepo
) {


    fun saveAll(entities: List<Country>) {
        countryRepo.saveAll(entities)
    }

    fun findByKnownNameAndDataStatus(
        knownName: String,
        dataStatus: DataStatus = DataStatus.ACTIVE,
        skipCache: Boolean = true
    ): AggregatorListResponse<Country> {
        val databaseAccessLogId = UUID.randomUUID().toString()
        val resultFromDb = countryRepo.findByKnownNameAndDataStatus(knownName, dataStatus)
        return AggregatorListResponse(data = ArrayList(resultFromDb), databaseAccessLogId)
    }

    fun findByOfficialNameAndDataStatus(
        officialName: String,
        dataStatus: DataStatus = DataStatus.ACTIVE,
        skipCache: Boolean = true
    ): AggregatorListResponse<Country> {
        val databaseAccessLogId = UUID.randomUUID().toString()
        val resultFromDb = countryRepo.findByOfficialNameAndDataStatus(officialName, dataStatus)
        return AggregatorListResponse(data = ArrayList(resultFromDb), databaseAccessLogId)
    }

    fun findByIdAndDataStatus(
        id: String,
        dataStatus: DataStatus = DataStatus.ACTIVE,
        skipCache: Boolean = true
    ): AggregatorListResponse<Country> {
        val databaseAccessLogId = UUID.randomUUID().toString()
        val resultFromDb = countryRepo.findByIdAndDataStatus(id, dataStatus)
        return AggregatorListResponse(data = ArrayList(resultFromDb), databaseAccessLogId)
    }

    fun findByIsdCodeAndDataStatus(
        isdCode: String,
        dataStatus: DataStatus = DataStatus.ACTIVE,
        skipCache: Boolean = true
    ): AggregatorListResponse<Country> {
        val databaseAccessLogId = UUID.randomUUID().toString()
        val resultFromDb = countryRepo.findByIsdCodeAndDataStatus(isdCode, dataStatus)
        return AggregatorListResponse(data = ArrayList(resultFromDb), databaseAccessLogId)
    }

    fun findTop1ByOrderByCreatedDateAsc(skipCache: Boolean = true): AggregatorListResponse<FetchMostRecentMinifiedImpl> {
        val databaseAccessLogId = UUID.randomUUID().toString()
        val resultFromDb =
            countryRepo.findTop1ByOrderByCreatedDateAsc()
                .map { toMap ->
                    FetchMostRecentMinifiedImpl(
                        id = toMap.id,
                        createdDate = toMap.createdDate
                    )
                }.toCollection(ArrayList())
        return AggregatorListResponse(data = ArrayList(resultFromDb), databaseAccessLogId)
    }

    fun findCountWithOutOffsetIdOffsetDateAndLimit(
        queryString: String,
        serviceable: Boolean?,
        skipCache: Boolean = true
    ): AggregatorResponse<Long> {
        val databaseAccessLogId = UUID.randomUUID().toString()
        val resultFromDb = countryRepo.findCountWithOutOffsetIdOffsetDateAndLimit(
            queryString = queryString,
            serviceable = serviceable,
            considerServiceable = serviceable != null
        )
        return AggregatorResponse(data = resultFromDb, databaseAccessLogId)
    }

    fun findAllDataWithOutOffsetIdOffsetDateAndLimit(
        queryString: String,
        serviceable: Boolean?,
        skipCache: Boolean = true
    ): AggregatorListResponse<CountryListMinifiedResponseImpl> {
        val databaseAccessLogId = UUID.randomUUID().toString()
        val resultFromDb = countryRepo.findAllDataWithOutOffsetIdOffsetDateAndLimit(
            queryString = queryString,
            serviceable = serviceable,
            considerServiceable = serviceable != null
        ).map { toMap ->
            CountryListMinifiedResponseImpl(
                id = toMap.id,
                name = toMap.name,
                officialName = toMap.officialName,
                isdCode = toMap.isdCode,
                alpha2 = toMap.alpha2,
                alpha3 = toMap.alpha3,
                createdDate = toMap.createdDate
            )
        }.toCollection(ArrayList())
        return AggregatorListResponse(data = ArrayList(resultFromDb), databaseAccessLogId)
    }

    fun findWithOutOffsetId(
        queryString: String,
        zonedDateTime: ZonedDateTime,
        serviceable: Boolean?,
        limit: Int,
        skipCache: Boolean = true
    ): AggregatorListResponse<CountryListMinifiedResponseImpl> {
        val databaseAccessLogId = UUID.randomUUID().toString()
        val resultFromDb = countryRepo.findWithOutOffsetId(
            queryString = queryString,
            zonedDateTime = zonedDateTime,
            limit = limit,
            serviceable = serviceable,
            considerServiceable = serviceable != null
        ).map { toMap ->
            CountryListMinifiedResponseImpl(
                id = toMap.id,
                name = toMap.name,
                officialName = toMap.officialName,
                isdCode = toMap.isdCode,
                alpha2 = toMap.alpha2,
                alpha3 = toMap.alpha3,
                createdDate = toMap.createdDate
            )
        }.toCollection(ArrayList())
        return AggregatorListResponse(data = ArrayList(resultFromDb), databaseAccessLogId)
    }

    fun findWithOffsetId(
        queryString: String,
        zonedDateTime: ZonedDateTime,
        offsetId: String,
        serviceable: Boolean?,
        limit: Int,
        skipCache: Boolean = true
    ): AggregatorListResponse<CountryListMinifiedResponseImpl> {
        val databaseAccessLogId = UUID.randomUUID().toString()
        val resultFromDb = countryRepo.findWithOffsetId(
            queryString = queryString,
            zonedDateTime = zonedDateTime,
            offsetId = offsetId,
            limit = limit,
            serviceable = serviceable,
            considerServiceable = serviceable != null
        ).map { toMap ->
            CountryListMinifiedResponseImpl(
                id = toMap.id,
                name = toMap.name,
                officialName = toMap.officialName,
                isdCode = toMap.isdCode,
                alpha2 = toMap.alpha2,
                alpha3 = toMap.alpha3,
                createdDate = toMap.createdDate
            )
        }.toCollection(ArrayList())
        return AggregatorListResponse(data = ArrayList(resultFromDb), databaseAccessLogId)
    }

    fun findByAlpha2AndDataStatus(
        alpha2Code: String,
        skipCache: Boolean = true
    ): AggregatorListResponse<Country> {
        val databaseAccessLogId = UUID.randomUUID().toString()
        val resultFromDb = countryRepo.findByAlpha2AndDataStatus(alpha2Code, DataStatus.ACTIVE)
        return AggregatorListResponse(data = ArrayList(resultFromDb), databaseAccessLogId)
    }

}