package com.example;

import com.example.adapter.GeneratedJsonAdapterFactory;
import com.example.adapter.GeneratedTypeAdapterFactory;
import com.example.model_av.ResponseAV;
import com.example.model_reflective.Response;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.moshi.Moshi;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

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
    public Response gson_reflective_fromJson(ReflectiveGson param) {
        return param.gson.fromJson(param.json, Response.class);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public ResponseAV gson_streaming_fromJson(AVGson param) {
        return param.gson.fromJson(param.json, ResponseAV.class);
    }

}
