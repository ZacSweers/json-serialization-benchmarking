package dev.zacsweers.jsonserialization.models.jackson

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class JackResponse @JsonCreator constructor(
    @JsonProperty("users") val users: List<JackUser>,
    @JsonProperty("status") val status: String,
    @JsonProperty("is_real_json") val isRealJson: Boolean,
)
