package dev.zacsweers.jsonserialization.models.java_serialization;

import java.util.Objects;

public class FriendJ {
    public int id = 0;
    public String name = null;

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FriendJ friendJ = (FriendJ) o;
    return id == friendJ.id && Objects.equals(name, friendJ.name);
  }

  @Override public int hashCode() {
    return Objects.hash(id, name);
  }
}
