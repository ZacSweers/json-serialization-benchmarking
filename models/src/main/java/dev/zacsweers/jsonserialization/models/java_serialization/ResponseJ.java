package dev.zacsweers.jsonserialization.models.java_serialization;

import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;
import java.util.List;
import java.util.Objects;

public class ResponseJ {
    public List<UserJ> users = null;
    public String status = null;

    @SerializedName("is_real_json") // Annotation needed for GSON
    @Json(name = "is_real_json")
    public boolean isRealJson = false;

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseJ responseJ = (ResponseJ) o;
        return isRealJson == responseJ.isRealJson
            && Objects.equals(users, responseJ.users)
            && Objects.equals(status, responseJ.status);
    }

    @Override public int hashCode() {
        return Objects.hash(users, status, isRealJson);
    }
}
