package dev.zacsweers.jsonserialization.models.java_serialization;

import java.util.Objects;

public class NameJ {
    public String first = null;
    public String last = null;

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NameJ nameJ = (NameJ) o;
    return Objects.equals(first, nameJ.first) && Objects.equals(last, nameJ.last);
  }

  @Override public int hashCode() {
    return Objects.hash(first, last);
  }
}
