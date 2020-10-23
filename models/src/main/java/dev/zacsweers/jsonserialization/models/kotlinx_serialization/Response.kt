package dev.zacsweers.jsonserialization.models.kotlinx_serialization

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Response {

    var users: List<User>? = null

    var status: String? = null

    @SerialName("is_real_json")
    @SerializedName("is_real_json") // Annotation needed for GSON
    @Json(name = "is_real_json")
    var isRealJson: Boolean = false

    fun stringify(serializer: KSerializer<Response>): String {
        return kotlinx.serialization.json.Json.encodeToString(serializer, this)
    }

    companion object {
        @JvmStatic
        fun parse(serializer: KSerializer<Response>, str: String): Response {
            return kotlinx.serialization.json.Json.decodeFromString(serializer, str)
        }

        @JvmStatic
        fun underlyingSerializer(): KSerializer<Response> {
            return serializer()
        }
    }
}
