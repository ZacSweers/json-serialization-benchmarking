package com.example.model_dc

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDC(
    @SerialName("_id")
    @SerializedName("_id") // Annotation needed for GSON
    @Json(name = "_id")
    val id: String?,

    val index: Int,
    val guid: String?,

    @SerialName("is_active")
    @SerializedName("is_active") // Annotation needed for GSON
    @Json(name = "is_active")
    val isActive: Boolean,

    val balance: String?,

    @SerialName("picture")
    @SerializedName("picture") // Annotation needed for GSON
    @Json(name = "picture")
    val pictureUrl: String?,

    val age: Int,
    val name: NameDC?,
    val company: String?,
    val email: String?,
    val address: String?,
    val about: String?,
    val registered: String?,
    val latitude: Double,
    val longitude: Double?,
    val tags: List<String>?,
    val range: List<Int>?,
    val friends: List<FriendDC>?,
    val images: List<ImageDC>?,
    val greeting: String?,

    @SerialName("favorite_fruit")
    @SerializedName("favorite_fruit") // Annotation needed for GSON
    @Json(name = "favorite_fruit")
    val favoriteFruit: String?,

    @SerialName("eye_color")
    @SerializedName("eye_color") // Annotation needed for GSON
    @Json(name = "eye_color")
    val eyeColor: String?,

    val phone: String?
)
