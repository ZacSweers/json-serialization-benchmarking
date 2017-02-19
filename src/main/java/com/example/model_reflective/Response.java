package com.example.model_reflective;

import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;

import java.util.List;

public class Response {

    public List<User> users;

    public String status;

    @SerializedName("is_real_json") // Annotation needed for GSON
    @Json(name = "is_real_json")
    public boolean isRealJson;
}
