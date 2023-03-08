package dev.zacsweers.jsonserialization.models.jackson

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class JackImage @JsonCreator constructor(
    @JsonProperty("id") val id: String,
    @JsonProperty("format") val format: String,
    @JsonProperty("url") val url: String,
    @JsonProperty("description") val description: String,
)
