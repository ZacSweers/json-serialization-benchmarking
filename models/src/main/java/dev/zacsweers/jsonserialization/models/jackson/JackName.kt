package dev.zacsweers.jsonserialization.models.jackson

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class JackName @JsonCreator constructor(
    @JsonProperty("first") val first: String,
    @JsonProperty("last") val last: String,
)
