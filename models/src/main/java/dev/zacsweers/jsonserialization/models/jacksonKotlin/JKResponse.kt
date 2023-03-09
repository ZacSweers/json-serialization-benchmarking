package dev.zacsweers.jsonserialization.models.jacksonKotlin

import com.fasterxml.jackson.annotation.JsonProperty

class JKResponse(
    val users: List<JKUser>,
    val status: String,
    @JsonProperty("is_real_json")
    val isRealJson: Boolean
)
