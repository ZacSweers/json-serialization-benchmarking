package dev.zacsweers.jsonserialization.models.jackson

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class JackUser @JsonCreator constructor(
    @JsonProperty("_id") val id: String,
    @JsonProperty("index") val index: Int,
    @JsonProperty("guid") val guid: String,
    @JsonProperty("is_active") val isActive: Boolean,
    @JsonProperty("balance") val balance: String,
    @JsonProperty("picture") val pictureUrl: String,
    @JsonProperty("age") val age: Int,
    @JsonProperty("name") val name: JackName,
    @JsonProperty("company") val company: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("address") val address: String,
    @JsonProperty("about") val about: String,
    @JsonProperty("registered") val registered: String,
    @JsonProperty("latitude") val latitude: Double,
    @JsonProperty("longitude") val longitude: Double,
    @JsonProperty("tags") val tags: List<String>,
    @JsonProperty("range") val range: List<Int>,
    @JsonProperty("friends") val friends: List<JackFriend>,
    @JsonProperty("images") val images: List<JackImage>,
    @JsonProperty("greeting") val greeting: String,
    @JsonProperty("favorite_fruit") val favoriteFruit: String,
    @JsonProperty("eye_color") val eyeColor: String,
    @JsonProperty("phone") val phone: String,
)
