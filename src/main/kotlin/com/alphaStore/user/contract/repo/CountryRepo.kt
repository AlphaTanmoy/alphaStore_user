package com.alphaStore.user.contract.repo

import com.alphaStore.user.entity.Country
import com.alphaStore.user.enums.DataStatus
import com.alphaStore.user.model.minified.CountryListMinifiedResponse
import com.alphaStore.user.model.minified.FetchMostRecentMinified
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.ZonedDateTime

@Suppress("SqlDialectInspection", "SqlNoDataSourceInspection")
@Primary
interface CountryRepo : JpaRepository<Country, String> {

    fun findByIdAndDataStatus(id: String, dataStatus: DataStatus = DataStatus.ACTIVE): List<Country>
    fun findByIsdCodeAndDataStatus(isdCode: String, dataStatus: DataStatus = DataStatus.ACTIVE): List<Country>
    fun findByOfficialNameAndDataStatus(officialName: String, dataStatus: DataStatus = DataStatus.ACTIVE): List<Country>
    fun findByKnownNameAndDataStatus(knownName: String, dataStatus: DataStatus = DataStatus.ACTIVE): List<Country>

    @Query(
        value = "SELECT countries.id AS id, " +
                "countries.created_date AS createdDate " +
                "FROM countries " +
                "ORDER BY countries.created_date ASC " +
                "LIMIT 1",
        nativeQuery = true
    )
    fun findTop1ByOrderByCreatedDateAsc(): List<FetchMostRecentMinified>

    @Query(
        value = "SELECT COUNT(*) " +
                "FROM countries " +
                "WHERE CASE WHEN :considerServiceable " +
                "THEN serviceable = :serviceable ELSE true END " +
                "AND ( " +
                "known_name LIKE :queryString " +
                "OR official_name LIKE :queryString " +
                "OR alpha2 LIKE :queryString " +
                "OR alpha3 LIKE :queryString " +
                "OR isd_code LIKE :queryString " +
                //"OR METAPHONE(known_name,7) = METAPHONE(:queryString,7) " +
                //"OR METAPHONE(official_name,7) = METAPHONE(:queryString,7) " +
                //"OR LOWER(known_name) LIKE LOWER(:queryString) " +
                //"OR LOWER(official_name) LIKE LOWER(:queryString) " +
                ") ",
        nativeQuery = true
    )
    fun findCountWithOutOffsetIdOffsetDateAndLimit(
        @Param("queryString") queryString: String,
        @Param("serviceable") serviceable: Boolean? = null,
        @Param("considerServiceable") considerServiceable: Boolean
    ): Long

    @Query(
        value = "SELECT countries.id as id, " +
                "CAST(countries.known_name AS VARCHAR) as name, " +
                "CAST(countries.official_name AS VARCHAR) as officialName, " +
                "CAST(countries.isd_code AS VARCHAR) as isdCode, " +
                "CAST(countries.alpha2 AS VARCHAR) as alpha2, " +
                "CAST(countries.alpha3 AS VARCHAR) as alpha3, " +
                "countries.created_date as createdDate " +
                "FROM countries " +
                "WHERE CASE WHEN :considerServiceable " +
                "THEN serviceable = :serviceable ELSE true END " +
                "AND ( " +
                "known_name LIKE :queryString " +
                "OR official_name LIKE :queryString " +
                "OR alpha2 LIKE :queryString " +
                "OR alpha3 LIKE :queryString " +
                "OR isd_code LIKE :queryString " +
                //"OR METAPHONE(known_name,7) = METAPHONE(:queryString,7) " +
                //"OR METAPHONE(official_name,7) = METAPHONE(:queryString,7) " +
                //"OR LOWER(known_name) LIKE LOWER(:queryString) " +
                //"OR LOWER(official_name) LIKE LOWER(:queryString) " +
                ") " +
                "ORDER BY created_date ASC,id ASC " +
                "LIMIT :limit ",
        nativeQuery = true
    )
    fun findAllDataWithOutOffsetIdOffsetDateAndLimit(
        @Param("queryString") queryString: String,
        @Param("serviceable") serviceable: Boolean? = null,
        @Param("considerServiceable") considerServiceable: Boolean
    ): List<CountryListMinifiedResponse>

    @Query(
        value = "SELECT countries.id as id, " +
                "CAST(countries.known_name AS VARCHAR) as name, " +
                "CAST(countries.official_name AS VARCHAR) as officialName, " +
                "CAST(countries.isd_code AS VARCHAR) as isdCode, " +
                "CAST(countries.alpha2 AS VARCHAR) as alpha2, " +
                "CAST(countries.alpha3 AS VARCHAR) as alpha3, " +
                "countries.created_date as createdDate " +
                "FROM countries " +
                "WHERE created_date > :zonedDateTime " +
                "AND CASE WHEN :considerServiceable THEN serviceable = :serviceable ELSE true END " +
                "AND ( " +
                "known_name LIKE :queryString " +
                "OR official_name LIKE :queryString " +
                "OR alpha2 LIKE :queryString " +
                "OR alpha3 LIKE :queryString " +
                "OR isd_code LIKE :queryString " +
                //"OR METAPHONE(known_name,7) = METAPHONE(:queryString,7) " +
                //"OR METAPHONE(official_name,7) = METAPHONE(:queryString,7) " +
                //"OR LOWER(known_name) LIKE LOWER(:queryString) " +
                //"OR LOWER(official_name) LIKE LOWER(:queryString) " +
                ") " +
                "ORDER BY created_date ASC,id ASC " +
                "LIMIT :limit ",
        nativeQuery = true
    )
    fun findWithOutOffsetId(
        @Param("queryString") queryString: String,
        @Param("zonedDateTime") zonedDateTime: ZonedDateTime,
        @Param("serviceable") serviceable: Boolean? = null,
        @Param("considerServiceable") considerServiceable: Boolean,
        @Param("limit") limit: Int
    ): List<CountryListMinifiedResponse>

    @Query(
        value = "SELECT countries.id as id, " +
                "CAST(countries.known_name as VARCHAR) as name, " +
                "CAST(countries.official_name AS VARCHAR) as officialName, " +
                "CAST(countries.isd_code AS VARCHAR) as isdCode, " +
                "CAST(countries.alpha2 AS VARCHAR) as alpha2, " +
                "CAST(countries.alpha3 AS VARCHAR) as alpha3, " +
                "countries.created_date as createdDate " +
                "FROM countries " +
                "WHERE created_date = :zonedDateTime " +
                "AND id > :offsetId " +
                "AND CASE WHEN :considerServiceable THEN serviceable = :serviceable ELSE true END " +
                "AND ( " +
                "known_name LIKE :queryString " +
                "OR official_name LIKE :queryString " +
                "OR alpha2 LIKE :queryString " +
                "OR alpha3 LIKE :queryString " +
                "OR isd_code LIKE :queryString " +
                //"OR METAPHONE(known_name,7) = METAPHONE(:queryString,7) " +
                //"OR METAPHONE(official_name,7) = METAPHONE(:queryString,7) " +
                //"OR LOWER(known_name) LIKE LOWER(:queryString) " +
                //"OR LOWER(official_name) LIKE LOWER(:queryString) " +
                ") " +
                "ORDER BY created_date ASC,id ASC " +
                "LIMIT :limit ",
        nativeQuery = true
    )
    fun findWithOffsetId(
        @Param("queryString") queryString: String,
        @Param("zonedDateTime") zonedDateTime: ZonedDateTime,
        @Param("offsetId") offsetId: String,
        @Param("serviceable") serviceable: Boolean? = null,
        @Param("considerServiceable") considerServiceable: Boolean,
        @Param("limit") limit: Int
    ): List<CountryListMinifiedResponse>

    fun findByAlpha2AndDataStatus(
        alpha2Code: String,
        dataStatus: DataStatus = DataStatus.ACTIVE
    ): List<Country>
}