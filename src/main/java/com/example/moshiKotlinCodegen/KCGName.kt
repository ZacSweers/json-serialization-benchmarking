package com.example.moshiKotlinCodegen

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KCGName(
    val first: String,
    val last: String
)
