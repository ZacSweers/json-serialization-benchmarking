package dev.zacsweers.jsonserialization.models.jacksonKotlin

import com.fasterxml.jackson.annotation.JsonProperty

data class JKUser(
    @JsonProperty("_id")
    val id: String,
    val index: Int,
    val guid: String,
    @JsonProperty("is_active")
    val isActive: Boolean,
    val balance: String,
    @JsonProperty("picture")
    val pictureUrl: String,
    val age: Int,
    val name: JKName,
    val company: String,
    val email: String,
    val address: String,
    val about: String,
    val registered: String,
    val latitude: Double,
    val longitude: Double,
    val tags: List<String>,
    val range: List<Int>,
    val friends: List<JKFriend>,
    val images: List<JKImage>,
    val greeting: String,
    @JsonProperty("favorite_fruit")
    val favoriteFruit: String,
    @JsonProperty("eye_color")
    val eyeColor: String,
    val phone: String
)
