package com.example;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Kryo.DefaultInstantiatorStrategy;
import com.esotericsoftware.kryo.factories.SerializerFactory;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import com.example.adapter.AutoValueSerializerFactory;
import com.example.adapter.GeneratedJsonAdapterFactory;
import com.example.adapter.GeneratedTypeAdapterFactory;
import com.example.model_av.FriendAV;
import com.example.model_av.ImageAV;
import com.example.model_av.NameAV;
import com.example.model_av.ResponseAV;
import com.example.model_av.UserAV;
import com.example.model_reflective.Response;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.moshi.Moshi;

import org.objenesis.strategy.StdInstantiatorStrategy;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SpeedTest {

    @State(Scope.Benchmark)
    public static class ReflectiveMoshi {

        @Setup
        public void doSetup() throws Exception {
            moshi = new Moshi.Builder().build();
            URL url = Resources.getResource("largesample.json");
            json = Resources.toString(url, Charsets.UTF_8);
            response = new Moshi.Builder().build().adapter(Response.class).fromJson(json);
        }

        public Moshi moshi;
        public String json;
        public Response response;
    }

    @State(Scope.Benchmark)
    public static class ReflectiveGson {

        @Setup
        public void doSetup() throws Exception {
            gson = new GsonBuilder().create();
            URL url = Resources.getResource("largesample.json");
            json = Resources.toString(url, Charsets.UTF_8);
            response = new GsonBuilder()
                    .create()
                    .fromJson(json, Response.class);
        }

        public Gson gson;
        public String json;
        public Response response;
    }

    @State(Scope.Benchmark)
    public static class AVMoshi {

        @Setup
        public void doSetup() throws Exception {
            moshi = new Moshi.Builder()
                    .add(GeneratedJsonAdapterFactory.create())
                    .build();
            URL url = Resources.getResource("largesample.json");
            json = Resources.toString(url, Charsets.UTF_8);
            response = new Moshi.Builder().add(GeneratedJsonAdapterFactory.create())
                    .build()
                    .adapter(ResponseAV.class)
                    .fromJson(json);
        }

        public Moshi moshi;
        public String json;
        public ResponseAV response;
    }

    // An optimized moshi instance where the cache is already hot
    @State(Scope.Benchmark)
    public static class AVMoshiOptimized {

        @Setup
        public void doSetup() throws Exception {
            moshi = new Moshi.Builder()
                    .add(GeneratedJsonAdapterFactory.create())
                    .build();
            URL url = Resources.getResource("largesample.json");
            json = Resources.toString(url, Charsets.UTF_8);
            response = moshi
                    .adapter(ResponseAV.class)
                    .fromJson(json);
        }

        public Moshi moshi;
        public String json;
        public ResponseAV response;
    }

    @State(Scope.Benchmark)
    public static class AVGson {

        @Setup
        public void doSetup() throws Exception {
            gson = new GsonBuilder()
                    .registerTypeAdapterFactory(GeneratedTypeAdapterFactory.create())
                    .create();
            URL url = Resources.getResource("largesample.json");
            json = Resources.toString(url, Charsets.UTF_8);
            response = new GsonBuilder()
                    .registerTypeAdapterFactory(GeneratedTypeAdapterFactory.create())
                    .create()
                    .fromJson(json, ResponseAV.class);
        }

        public Gson gson;
        public String json;
        public ResponseAV response;
    }

    @State(Scope.Benchmark)
    public static class KryoScope {

        @Setup
        public void doSetup() throws Exception {
            kryo = new Kryo();
            kryo.setDefaultSerializer(CompatibleFieldSerializer.class);
            DefaultInstantiatorStrategy instantiatorStrategy = new DefaultInstantiatorStrategy();
            instantiatorStrategy.setFallbackInstantiatorStrategy(new StdInstantiatorStrategy());
            kryo.setInstantiatorStrategy(instantiatorStrategy);
            SerializerFactory avSerializerFactory = new AutoValueSerializerFactory();
            kryo.addDefaultSerializer(ResponseAV.class, avSerializerFactory);
            kryo.addDefaultSerializer(UserAV.class, avSerializerFactory);
            kryo.addDefaultSerializer(NameAV.class, avSerializerFactory);
            kryo.addDefaultSerializer(ImageAV.class, avSerializerFactory);
            kryo.addDefaultSerializer(FriendAV.class, avSerializerFactory);
            URL url = Resources.getResource("largesample.json");
            String json = Resources.toString(url, Charsets.UTF_8);
            response = new GsonBuilder()
                    .registerTypeAdapterFactory(GeneratedTypeAdapterFactory.create())
                    .create()
                    .fromJson(json, ResponseAV.class);
            Kryo kryoLocal = new Kryo();
            kryoLocal.setDefaultSerializer(CompatibleFieldSerializer.class);
            DefaultInstantiatorStrategy instantiatorStrategyLocal = new DefaultInstantiatorStrategy();
            instantiatorStrategyLocal.setFallbackInstantiatorStrategy(new StdInstantiatorStrategy());
            kryoLocal.setInstantiatorStrategy(instantiatorStrategyLocal);
            SerializerFactory avSerializerFactoryLocal = new AutoValueSerializerFactory();
            kryoLocal.addDefaultSerializer(ResponseAV.class, avSerializerFactoryLocal);
            kryoLocal.addDefaultSerializer(UserAV.class, avSerializerFactoryLocal);
            kryoLocal.addDefaultSerializer(NameAV.class, avSerializerFactoryLocal);
            kryoLocal.addDefaultSerializer(ImageAV.class, avSerializerFactoryLocal);
            kryoLocal.addDefaultSerializer(FriendAV.class, avSerializerFactoryLocal);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Output output = new Output(stream);
            kryoLocal.writeObject(output, response);
            output.flush();
            bytes = stream.toByteArray();
        }

        public Kryo kryo;
        public byte[] bytes;
        public ResponseAV response;
    }


    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public String moshi_reflective_toJson(ReflectiveMoshi param) {
        return param.moshi.adapter(Response.class).toJson(param.response);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public String moshi_streaming_toJson(AVMoshi param) {
        return param.moshi.adapter(ResponseAV.class).toJson(param.response);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public String moshi_streaming_optimized_toJson(AVMoshiOptimized param) {
        return param.moshi.adapter(ResponseAV.class).toJson(param.response);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public String gson_reflective_toJson(ReflectiveGson param) {
        return param.gson.toJson(param.response);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public String gson_streaming_toJson(AVGson param) {
        return param.gson.toJson(param.response);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public byte[] kryo_toBytes(KryoScope param) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Output output = new Output(stream);
        param.kryo.writeObject(output, param.response);
        output.flush();
        return stream.toByteArray();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public Response moshi_reflective_fromJson(ReflectiveMoshi param) throws Exception {
        return param.moshi.adapter(Response.class).fromJson(param.json);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public ResponseAV moshi_streaming_fromJson(AVMoshi param) throws Exception {
        return param.moshi.adapter(ResponseAV.class).fromJson(param.json);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public ResponseAV moshi_streaming_optimized_fromJson(AVMoshiOptimized param) throws Exception {
        return param.moshi.adapter(ResponseAV.class).fromJson(param.json);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public Response gson_reflective_fromJson(ReflectiveGson param) {
        return param.gson.fromJson(param.json, Response.class);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public ResponseAV gson_streaming_fromJson(AVGson param) {
        return param.gson.fromJson(param.json, ResponseAV.class);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public ResponseAV kryo_fromBytes(KryoScope param) {
        return param.kryo.readObject(new Input(param.bytes), ResponseAV.class);
    }

}
