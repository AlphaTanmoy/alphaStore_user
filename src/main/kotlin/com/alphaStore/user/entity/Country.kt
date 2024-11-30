package com.alphaStore.user.entity

import com.alphaStore.user.entity.superentity.SuperEntityWithIdCreatedLastModifiedDataStatus
import com.fasterxml.jackson.annotation.JsonFilter
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.validation.constraints.*

@Entity(name = "countries")
data class Country(
    @field:NotBlank(message = "Known name must not be blank")
    @Column(columnDefinition = "citext")
    var knownName: String = "",

    @field:NotBlank(message = "Official name must not be blank")
    @Column(columnDefinition = "citext")
    var officialName: String = "",

    @Column(columnDefinition = "citext")
    var isdCode: String = "",

    @field:NotBlank(message = "Alpha-2 code must not be blank")
    @Column(columnDefinition = "citext")
    var alpha2: String = "",

    @field:NotBlank(message = "Alpha-3 code must not be blank")
    @Column(columnDefinition = "citext")
    var alpha3: String = "",

    var serviceable: Boolean = false,

    @field:NotBlank(message = "Mobile number validation regex must not be blank")
    @Column
    var mobileNumberValidationRegex: String = ""
) : SuperEntityWithIdCreatedLastModifiedDataStatus()

@JsonFilter("CountryFilter")
class CountryMixIn