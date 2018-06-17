package com.example.model_dc

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDC(
    val users: List<UserDC>?,
    val status: String?,
    @SerialName("is_real_json")
    @SerializedName("is_real_json")
    // Annotation needed for GSON @Json(name = "is_real_json")
    val isRealJson: Boolean
)
