package com.alphaStore.user.service


import com.alphaStore.user.contract.aggregator.CountryRepoAggregator
import com.alphaStore.user.contract.repo.EncodingUtilContract
import com.alphaStore.user.entity.Country
import com.alphaStore.user.enums.DateRangeType
import com.alphaStore.user.error.BadRequestException
import com.alphaStore.user.model.PaginationResponse
import com.alphaStore.user.model.minifiedImpl.CountryListMinifiedResponseImpl
import com.alphaStore.user.reqres.FilterOption
import com.alphaStore.user.utils.ConverterStringToObjectList
import com.alphaStore.user.utils.DateUtil
import com.alphaStore.user.utils.EncryptionMaster
import org.springframework.stereotype.Component
import java.time.ZoneId
import java.time.ZonedDateTime

@Component
class CountryService(
    private val countryRepoAggregator: CountryRepoAggregator,
    private val encodingUtilContract: EncodingUtilContract,
    private val encryptionMaster: EncryptionMaster,
    private val dateUtilContract: DateUtil,
) {


    fun getCountries(
        offsetToken: String?,
        toRetFilterOption: ArrayList<FilterOption>,
        giveCount: Boolean,
        queryStringFinal: String,
        serviceable: Boolean?,
        giveData: Boolean,
        considerMaxDateRange: Boolean,
        dateRangeType: String?,
        pageSizeFinal: Int,
    ): PaginationResponse<CountryListMinifiedResponseImpl> {
        var offsetDateFinal: ZonedDateTime? = null
        var offsetId = ""
        offsetToken?.let {
            val decrypted = encryptionMaster.decrypt(
                encodingUtilContract.decode(
                    it
                )
            )
            val splits = decrypted.split("::")
            val decryptedOffsetDate = dateUtilContract.getZonedDateTimeFromStringUsingIsoFormatServerTimeZone(splits[0])
            if (decryptedOffsetDate.isEmpty)
                throw BadRequestException("Please provide valid offset token")
            offsetDateFinal = decryptedOffsetDate.get()
            offsetId = splits[1]
        } ?: run {
            val firstCreated =
                countryRepoAggregator.findTop1ByOrderByCreatedDateAsc()
            offsetDateFinal = if (firstCreated.data.isEmpty())
                null
            else {
                val instant = firstCreated.data[0]
                instant.let {
                    ZonedDateTime.ofInstant(it.createdDate.minusNanos(1000), ZoneId.of("UTC"))
                }
            }
        }
        offsetDateFinal ?: run {
            return PaginationResponse(
                arrayListOf(),
                filterUsed = toRetFilterOption,
            )
        }
        val countriesToReturn: ArrayList<CountryListMinifiedResponseImpl> = ArrayList()
        var dataCount = 0L
        if (giveCount) {
            val countFromDb =
                countryRepoAggregator.findCountWithOutOffsetIdOffsetDateAndLimit(
                    queryString = queryStringFinal,
                    serviceable = serviceable
                )
            dataCount = countFromDb.data
        }
        if (giveData) {
            if (considerMaxDateRange && dateRangeType != null && dateRangeType == DateRangeType.MAX.name) {
                val allData = countryRepoAggregator.findAllDataWithOutOffsetIdOffsetDateAndLimit(
                    queryString = queryStringFinal,
                    serviceable = serviceable
                )
                countriesToReturn.addAll(allData.data)
            } else {
                if (offsetId == "") {
                    val countriesResultForFirstPage = countryRepoAggregator.findWithOutOffsetId(
                        queryString = queryStringFinal,
                        zonedDateTime = offsetDateFinal!!,
                        serviceable = serviceable,
                        limit = pageSizeFinal
                    )
                    if (countriesResultForFirstPage.data.isEmpty()) {
                        return PaginationResponse(
                            arrayListOf(),
                            filterUsed = toRetFilterOption,
                        )
                    }
                    countriesToReturn.addAll(countriesResultForFirstPage.data)
                } else {
                    val countriesResultForNextPageWithSameDate = countryRepoAggregator.findWithOffsetId(
                        queryString = queryStringFinal,
                        zonedDateTime = offsetDateFinal!!,
                        offsetId = offsetId,
                        serviceable = serviceable,
                        limit = pageSizeFinal,
                    )
                    val nextPageSize = pageSizeFinal - countriesResultForNextPageWithSameDate.data.size
                    val countriesResultForNextPage = countryRepoAggregator.findWithOutOffsetId(
                        queryString = queryStringFinal,
                        zonedDateTime = offsetDateFinal!!,
                        serviceable = serviceable,
                        limit = nextPageSize,
                    )
                    countriesToReturn.addAll(countriesResultForNextPageWithSameDate.data)
                    countriesToReturn.addAll(countriesResultForNextPage.data)

                }
            }
            if (countriesToReturn.isEmpty()) {
                return PaginationResponse(
                    arrayListOf(),
                    filterUsed = toRetFilterOption,
                )
            }else{
                return PaginationResponse(
                    ConverterStringToObjectList.sanitizeForOutput(ArrayList(countriesToReturn)),
                    encodingUtilContract.encode(
                        encryptionMaster.encrypt(
                            "${
                                countriesToReturn.last().createdDate
                            }::${countriesToReturn.last().id}",
                        )
                    ),
                    filterUsed = toRetFilterOption,
                    recordCount = dataCount.toInt()
                )
            }
        } else {
            return PaginationResponse(
                filterUsed = toRetFilterOption,
                recordCount = dataCount.toInt()
            )
        }
    }

    fun getCountryById(
        countryId: String,
        ): Country {
        val countries = countryRepoAggregator.findByIdAndDataStatus(
            countryId,
        )
        if (countries.data.isEmpty()) {
            throw BadRequestException("Country is not found")
        }
        val country = countries.data[0]
        return country
    }

    fun getCountryByKnownName(
        countryKnownName: String
    ): Country? {
        val countries = countryRepoAggregator.findByKnownNameAndDataStatus(
            knownName = countryKnownName,
        )
        if (countries.data.isEmpty()) {
            throw BadRequestException("Country is not found")
        }
        val country = countries.data[0]
        return country
    }


    fun getCountryByIsdCode(
        countryIsdCode: String,
        apisAccessLogId: String
    ): Country {
        val countries = countryRepoAggregator.findByIsdCodeAndDataStatus(
            countryIsdCode,
        )
        if (countries.data.isEmpty()) {
            throw BadRequestException("Country is not found")
        }
        val country = countries.data[0]
        return  country
    }

    fun updateCountry(
        country: Country,
        valueBeforeChange: Any,
        valueAfterChange: Any,
        fieldValue: String
    ) {
        val data = country::class.java.getDeclaredField(fieldValue)
        if (data.trySetAccessible()) {
            data.set(country, valueAfterChange)
            countryRepoAggregator.saveAll(arrayListOf(country))
        }
    }

    fun getCountryByAlpha2(alpha2Code: String, apisAccessLogId: String): Country? {
        val countryResponse = countryRepoAggregator.findByAlpha2AndDataStatus(
            alpha2Code
        )

        if (countryResponse.data.isEmpty()) {
            return null
        }
        return countryResponse.data[0]
    }
}