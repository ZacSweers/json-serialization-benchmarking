package dev.zacsweers.jsonserialization.models.moshiKotlinCodegen

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KCGFriend(
    val id: Int,
    val name: String
)
