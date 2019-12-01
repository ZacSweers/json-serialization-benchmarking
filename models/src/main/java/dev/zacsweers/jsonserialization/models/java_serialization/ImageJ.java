package dev.zacsweers.jsonserialization.models.java_serialization;

import java.util.Objects;

public class ImageJ {
    public String id = null;
    public String format = null;
    public String url = null;
    public String description = null;

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageJ imageJ = (ImageJ) o;
        return Objects.equals(id, imageJ.id)
            && Objects.equals(format, imageJ.format)
            && Objects.equals(url, imageJ.url)
            && Objects.equals(description, imageJ.description);
    }

    @Override public int hashCode() {
        return Objects.hash(id, format, url, description);
    }
}
