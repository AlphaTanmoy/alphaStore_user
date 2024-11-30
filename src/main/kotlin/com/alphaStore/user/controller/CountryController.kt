package com.alphaStore.user.controller

import com.alphaStore.user.model.PaginationResponse
import com.alphaStore.user.model.minifiedImpl.CountryListMinifiedResponseImpl
import com.alphaStore.user.reqres.FilterOption
import com.alphaStore.user.service.CountryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URLDecoder


@RestController
@RequestMapping("/seeder/country")
class CountryController (
    private val countryService: CountryService
){

    @GetMapping("/getAll")
    fun getAllProducts(
        @RequestParam("queryString") queryString: String? = null,
        @RequestParam("isActive") isActive: Boolean? = null,
        @RequestParam("offsetToken") offsetToken: String? = null,
        @RequestParam("giveCount") giveCount: Boolean = false,
        @RequestParam("considerMaxDateRange", defaultValue = "false") considerMaxDateRange: Boolean = false,
        @RequestParam("considerServiceable", defaultValue = "false") considerServiceable: Boolean = false,
        @RequestParam("limit") limit: Int? = null,
        @RequestParam("dateRangeType") dateRangeType: String? = null,
        @RequestParam("giveData", defaultValue = "true") giveData: Boolean = true,
    ): PaginationResponse<CountryListMinifiedResponseImpl> {
        val toRetFilterOption: ArrayList<FilterOption> = ArrayList()

        var queryStringFinal = "%"
        queryString?.let { obj ->
            toRetFilterOption.add(FilterOption("queryString", obj, obj))
            queryStringFinal = obj.split(',').joinToString("|") { "%${URLDecoder.decode(it, "UTF-8")}%" }
        }

        isActive?.let { isActiveTrue ->
            toRetFilterOption.add(
                FilterOption(
                    "isActive",
                    isActiveTrue.toString(),
                    isActiveTrue.toString(),
                )
            )
        }

        var pageSizeFinal = 5
        limit?.let {
            pageSizeFinal =
                if (it > 20)
                    20
                else
                    it
        }

        val resultFromDb = countryService.getCountries(
            offsetToken = offsetToken,
            toRetFilterOption = toRetFilterOption,
            giveCount = giveCount,
            queryStringFinal = queryStringFinal,
            serviceable = considerServiceable,
            giveData = giveData,
            considerMaxDateRange = considerMaxDateRange,
            dateRangeType = dateRangeType,
            pageSizeFinal = pageSizeFinal
        )
        return resultFromDb
    }

}