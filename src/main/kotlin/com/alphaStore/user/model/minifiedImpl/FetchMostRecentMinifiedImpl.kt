package com.alphaStore.user.model.minifiedImpl


import com.alphaStore.user.model.minified.FetchMostRecentMinified
import java.time.Instant

data class FetchMostRecentMinifiedImpl(
    override var id: String,
    override var createdDate: Instant
) : FetchMostRecentMinified