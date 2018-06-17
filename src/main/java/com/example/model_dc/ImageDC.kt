package com.example.model_dc

import kotlinx.serialization.Serializable

@Serializable
data class ImageDC(val id: String?, val format: String?, val url: String?, val description: String?)
