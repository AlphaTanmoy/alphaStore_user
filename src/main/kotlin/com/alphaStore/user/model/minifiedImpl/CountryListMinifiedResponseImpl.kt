package com.alphaStore.user.model.minifiedImpl

import com.alphaStore.user.model.minified.CountryListMinifiedResponse
import java.time.Instant

data class CountryListMinifiedResponseImpl(
    override var id: String,
    override var name: String,
    override var officialName: String,
    override var isdCode: String,
    override var alpha2: String,
    override var alpha3: String,
    override var createdDate: Instant
): CountryListMinifiedResponse