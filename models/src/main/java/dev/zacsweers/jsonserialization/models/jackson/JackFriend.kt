package dev.zacsweers.jsonserialization.models.jackson

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class JackFriend @JsonCreator constructor(
    @JsonProperty("id") val id: Int,
    @JsonProperty("name") val name: String,
)
