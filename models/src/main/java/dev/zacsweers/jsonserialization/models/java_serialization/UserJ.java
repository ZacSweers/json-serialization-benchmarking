package dev.zacsweers.jsonserialization.models.java_serialization;

import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;
import java.util.List;
import java.util.Objects;

public class UserJ {

  @SerializedName("_id") // Annotation needed for GSON
  @Json(name = "_id") public String id = null;

  public int index = 0;

  public String guid = null;

  @SerializedName("is_active") // Annotation needed for GSON
  @Json(name = "is_active") public boolean isActive = false;

  public String balance = null;

  @SerializedName("picture") // Annotation needed for GSON
  @Json(name = "picture") public String pictureUrl = null;

  public int age = 0;

  public NameJ name = null;

  public String company = null;

  public String email = null;

  public String address = null;

  public String about = null;

  public String registered = null;

  public double latitude = 0.0;

  public double longitude = 0.0;

  public List<String> tags = null;

  public List<Integer> range = null;

  public List<FriendJ> friends = null;

  public List<ImageJ> images = null;

  public String greeting = null;

  @SerializedName("favorite_fruit") // Annotation needed for GSON
  @Json(name = "favorite_fruit") public String favoriteFruit = null;

  @SerializedName("eye_color") // Annotation needed for GSON
  @Json(name = "eye_color") public String eyeColor = null;

  public String phone = null;

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserJ userJ = (UserJ) o;
    return index == userJ.index
        && isActive == userJ.isActive
        && age == userJ.age
        && Double.compare(userJ.latitude, latitude) == 0
        && Double.compare(userJ.longitude, longitude) == 0
        && Objects.equals(id, userJ.id)
        && Objects.equals(guid, userJ.guid)
        && Objects.equals(balance, userJ.balance)
        && Objects.equals(pictureUrl, userJ.pictureUrl)
        && Objects.equals(name, userJ.name)
        && Objects.equals(company, userJ.company)
        && Objects.equals(email, userJ.email)
        && Objects.equals(address, userJ.address)
        && Objects.equals(about, userJ.about)
        && Objects.equals(registered, userJ.registered)
        && Objects.equals(tags, userJ.tags)
        && Objects.equals(range, userJ.range)
        && Objects.equals(friends, userJ.friends)
        && Objects.equals(images, userJ.images)
        && Objects.equals(greeting, userJ.greeting)
        && Objects.equals(favoriteFruit, userJ.favoriteFruit)
        && Objects.equals(eyeColor, userJ.eyeColor)
        && Objects.equals(phone, userJ.phone);
  }

  @Override public int hashCode() {
    return Objects.hash(id,
        index,
        guid,
        isActive,
        balance,
        pictureUrl,
        age,
        name,
        company,
        email,
        address,
        about,
        registered,
        latitude,
        longitude,
        tags,
        range,
        friends,
        images,
        greeting,
        favoriteFruit,
        eyeColor,
        phone);
  }
}
