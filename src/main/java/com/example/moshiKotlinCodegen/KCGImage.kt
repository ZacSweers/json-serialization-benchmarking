package com.example.moshiKotlinCodegen

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KCGImage(
    val id: String,
    val format: String,
    val url: String,
    val description: String
)
