package com.example.adapter;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.factories.SerializerFactory;

public final class AutoValueSerializerFactory implements SerializerFactory {

    @Override
    public Serializer makeSerializer(Kryo kryo, Class<?> type) {
        if (!type.isAnnotationPresent(AutoKryo.class)) {
            return null;
        }

        String packageName = type.getPackage().getName();
        String className = type.getName().substring(packageName.length() + 1).replace('$', '_');
        String autoValueName = packageName + ".AutoValue_" + className;

        try {
            Class<?> autoValueType = Class.forName(autoValueName);
            return (Serializer<?>) kryo.getSerializer(autoValueType);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not load AutoValue type " + autoValueName, e);
        }
    }
}
