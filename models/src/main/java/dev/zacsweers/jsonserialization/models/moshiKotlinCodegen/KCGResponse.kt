package dev.zacsweers.jsonserialization.models.moshiKotlinCodegen

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KCGResponse(
    val users: List<KCGUser>,
    val status: String,
    @Json(name = "is_real_json")
    val isRealJson: Boolean
) : KCGAbstractResponse()
