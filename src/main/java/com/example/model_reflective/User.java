package com.example.model_reflective;

import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;

import java.util.List;

public class User {

    @SerializedName("_id") // Annotation needed for GSON
    @Json(name = "_id")
    public String id;

    public int index;

    public String guid;

    @SerializedName("is_active") // Annotation needed for GSON
    @Json(name = "is_active")
    public boolean isActive;

    public String balance;

    @SerializedName("picture") // Annotation needed for GSON
    @Json(name = "picture")
    public String pictureUrl;

    public int age;

    public Name name;

    public String company;

    public String email;

    public String address;

    public String about;

    public String registered;

    public double latitude;

    public double longitude;

    public List<String> tags;

    public List<Integer> range;

    public List<Friend> friends;

    public List<Image> images;

    public String greeting;

    @SerializedName("favorite_fruit") // Annotation needed for GSON
    @Json(name = "favorite_fruit")
    public String favoriteFruit;

    @SerializedName("eye_color") // Annotation needed for GSON
    @Json(name = "eye_color")
    public String eyeColor;

    public String phone;
}
