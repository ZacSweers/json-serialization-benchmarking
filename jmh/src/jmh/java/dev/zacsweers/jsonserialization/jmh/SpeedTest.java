package dev.zacsweers.jsonserialization.jmh;

import dev.zacsweers.jsonserialization.models.adapter.GeneratedJsonAdapterFactory;
import dev.zacsweers.jsonserialization.models.adapter.GeneratedTypeAdapterFactory;
import dev.zacsweers.jsonserialization.models.kotlinx_serialization.Response;
import dev.zacsweers.jsonserialization.models.java_serialization.ResponseJ;
import dev.zacsweers.jsonserialization.models.model_av.ResponseAV;
import dev.zacsweers.jsonserialization.models.moshiKotlinCodegen.KCGResponse;
import dev.zacsweers.jsonserialization.models.moshiKotlinReflective.KRResponse;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import kotlinx.serialization.KSerializer;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@SuppressWarnings("ALL")
public class SpeedTest {

    @State(Scope.Benchmark)
    public static class KotlinxSerialization {

        @Param({"true", "false"})
        public boolean minified;

        @Setup()
        public void doSetup() throws Exception {
            URL url = Resources.getResource("largesample" + (minified ? "_minified" : "") + ".json");
            json = Resources.toString(url, Charsets.UTF_8);
            kSerializer = Response.underlyingSerializer();
            response = Response.parse(kSerializer, json);
        }

        public String json;
        public Response response;
        public KSerializer<Response> kSerializer;
    }

    @State(Scope.Benchmark)
    public static class ReflectiveMoshi {

        @Param({"true", "false"})
        public boolean minified;

        @Setup
        public void setupTrial() throws Exception {
            moshi = new Moshi.Builder().build();
            URL url = Resources.getResource("largesample" + (minified ? "_minified" : "") + ".json");
            json = Resources.toString(url, Charsets.UTF_8);
            adapter = moshi.adapter(ResponseJ.class);
            response = adapter.fromJson(json);
        }

        public Moshi moshi;
        public String json;
        public ResponseJ response;
        public JsonAdapter<ResponseJ> adapter;
    }

    @State(Scope.Benchmark)
    public static class ReflectiveGson {

        @Param({"true", "false"})
        public boolean minified;

        @Setup
        public void setupTrial() throws Exception {
            gson = new GsonBuilder().create();
            URL url = Resources.getResource("largesample" + (minified ? "_minified" : "") + ".json");
            json = Resources.toString(url, Charsets.UTF_8);
            adapter = gson.getAdapter(Response.class);
            response = adapter.fromJson(json);
        }

        public Gson gson;
        public String json;
        public Response response;
        public TypeAdapter<Response> adapter;
    }

    @State(Scope.Benchmark)
    public static class AVMoshi {

        @Param({"true", "false"})
        public boolean minified;

        @Setup
        public void setupTrial() throws Exception {
            moshi = new Moshi.Builder()
                    .add(GeneratedJsonAdapterFactory.create())
                    .build();
            URL url = Resources.getResource("largesample" + (minified ? "_minified" : "") + ".json");
            json = Resources.toString(url, Charsets.UTF_8);
            adapter = moshi.adapter(ResponseAV.class);
            response = adapter.fromJson(json);
        }

        public Moshi moshi;
        public String json;
        public ResponseAV response;
        public JsonAdapter<ResponseAV> adapter;
    }

    @State(Scope.Benchmark)
    public static class ReflectiveMoshiKotlin {

        @Param({"true", "false"})
        public boolean minified;

        @Setup
        public void setupTrial() throws Exception {
            moshi = new Moshi.Builder()
                    .add(new KotlinJsonAdapterFactory())
                    .build();
            URL url = Resources.getResource("largesample" + (minified ? "_minified" : "") + ".json");
            json = Resources.toString(url, Charsets.UTF_8);
            adapter = moshi.adapter(KRResponse.class);
            response = adapter.fromJson(json);
        }

        public Moshi moshi;
        public String json;
        public KRResponse response;
        public JsonAdapter<KRResponse> adapter;
    }

    @State(Scope.Benchmark)
    public static class CodegenMoshiKotlin {

        @Param({"true", "false"})
        public boolean minified;

        @Setup
        public void setupTrial() throws Exception {
            moshi = new Moshi.Builder()
                    .build();
            URL url = Resources.getResource("largesample" + (minified ? "_minified" : "") + ".json");
            json = Resources.toString(url, Charsets.UTF_8);
            adapter = moshi.adapter(KCGResponse.class);
            response = adapter.fromJson(json);
        }

        public Moshi moshi;
        public String json;
        public KCGResponse response;
        public JsonAdapter<KCGResponse> adapter;
    }

    @State(Scope.Benchmark)
    public static class AVMoshiBuffer {

        @Param({"true", "false"})
        public boolean minified;

        @Setup
        public void setupTrial() throws Exception {
            moshi = new Moshi.Builder()
                    .add(GeneratedJsonAdapterFactory.create())
                    .build();
            URL url = Resources.getResource("largesample" + (minified ? "_minified" : "") + ".json");
            json = Resources.toString(url, Charsets.UTF_8);
            adapter = moshi.adapter(ResponseAV.class);
            response = adapter.fromJson(json);
        }

        @Setup(Level.Invocation)
        public void setupIteration() {
            bufferedSource = new Buffer().write(json.getBytes());
            bufferedSink = new Buffer();
        }

        public Moshi moshi;
        public String json;
        public BufferedSource bufferedSource;
        public BufferedSink bufferedSink;
        public ResponseAV response;
        public JsonAdapter<ResponseAV> adapter;
    }

    @State(Scope.Benchmark)
    public static class ReflectiveMoshiKotlinBuffer {

        @Param({"true", "false"})
        public boolean minified;

        @Setup
        public void setupTrial() throws Exception {
            moshi = new Moshi.Builder()
                    .add(new KotlinJsonAdapterFactory())
                    .build();
            URL url = Resources.getResource("largesample" + (minified ? "_minified" : "") + ".json");
            json = Resources.toString(url, Charsets.UTF_8);
            adapter = moshi.adapter(KRResponse.class);
            response = adapter.fromJson(json);
        }

        @Setup(Level.Invocation)
        public void setupIteration() {
            bufferedSource = new Buffer().write(json.getBytes());
            bufferedSink = new Buffer();
        }

        public Moshi moshi;
        public String json;
        public BufferedSource bufferedSource;
        public BufferedSink bufferedSink;
        public KRResponse response;
        public JsonAdapter<KRResponse> adapter;
    }

    @State(Scope.Benchmark)
    public static class CodegenMoshiKotlinBuffer {

        @Param({"true", "false"})
        public boolean minified;

        @Setup
        public void setupTrial() throws Exception {
            moshi = new Moshi.Builder()
                    .build();
            URL url = Resources.getResource("largesample" + (minified ? "_minified" : "") + ".json");
            json = Resources.toString(url, Charsets.UTF_8);
            adapter = moshi.adapter(KCGResponse.class);
            response = adapter.fromJson(json);
        }

        @Setup(Level.Invocation)
        public void setupIteration() {
            bufferedSource = new Buffer().write(json.getBytes());
            bufferedSink = new Buffer();
        }

        public Moshi moshi;
        public String json;
        public BufferedSource bufferedSource;
        public BufferedSink bufferedSink;
        public KCGResponse response;
        public JsonAdapter<KCGResponse> adapter;
    }

    @State(Scope.Benchmark)
    public static class AVGson {

        @Param({"true", "false"})
        public boolean minified;

        @Setup
        public void setupTrial() throws Exception {
            gson = new GsonBuilder()
                    .registerTypeAdapterFactory(GeneratedTypeAdapterFactory.create())
                    .create();
            URL url = Resources.getResource("largesample" + (minified ? "_minified" : "") + ".json");
            json = Resources.toString(url, Charsets.UTF_8);
            adapter = gson.getAdapter(ResponseAV.class);
            response = adapter.fromJson(json);
        }

        public Gson gson;
        public String json;
        public ResponseAV response;
        public TypeAdapter<ResponseAV> adapter;
    }

    @State(Scope.Benchmark)
    public static class AVGsonBuffer {

        @Param({"true", "false"})
        public boolean minified;

        @Setup
        public void setupTrial() throws Exception {
            gson = new GsonBuilder()
                    .registerTypeAdapterFactory(GeneratedTypeAdapterFactory.create())
                    .create();
            URL url = Resources.getResource("largesample" + (minified ? "_minified" : "") + ".json");
            json = Resources.toString(url, Charsets.UTF_8);
            adapter = gson.getAdapter(ResponseAV.class);
            response = adapter.fromJson(json);
        }

        @Setup(Level.Invocation)
        public void setupIteration() {
            source = new InputStreamReader(new Buffer().write(json.getBytes()).inputStream(), Charsets.UTF_8);
            sink = new OutputStreamWriter(new Buffer().outputStream(), Charsets.UTF_8);
        }

        public Gson gson;
        public String json;
        public Reader source;
        public Writer sink;
        public ResponseAV response;
        public TypeAdapter<ResponseAV> adapter;
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public String kserializer_string_toJson(KotlinxSerialization param) throws IOException {
        return param.response.stringify(param.kSerializer);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public String moshi_reflective_string_toJson(ReflectiveMoshi param) throws IOException {
        return param.adapter.toJson(param.response);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public String moshi_autovalue_string_toJson(AVMoshi param) throws IOException {
        return param.adapter.toJson(param.response);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public String moshi_kotlin_reflective_string_toJson(ReflectiveMoshiKotlin param) throws IOException {
        return param.adapter.toJson(param.response);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public String moshi_kotlin_codegen_string_toJson(CodegenMoshiKotlin param) throws IOException {
        return param.adapter.toJson(param.response);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public BufferedSink moshi_autovalue_buffer_toJson(AVMoshiBuffer param) throws IOException {
        param.adapter.toJson(param.bufferedSink, param.response);
        return param.bufferedSink;
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public BufferedSink moshi_kotlin_reflective_buffer_toJson(ReflectiveMoshiKotlinBuffer param) throws IOException {
        param.adapter.toJson(param.bufferedSink, param.response);
        return param.bufferedSink;
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public BufferedSink moshi_kotlin_codegen_buffer_toJson(CodegenMoshiKotlinBuffer param) throws IOException {
        param.adapter.toJson(param.bufferedSink, param.response);
        return param.bufferedSink;
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public String gson_reflective_string_toJson(ReflectiveGson param) throws IOException {
        return param.adapter.toJson(param.response);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public String gson_autovalue_string_toJson(AVGson param) throws IOException {
        return param.adapter.toJson(param.response);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public Writer gson_autovalue_buffer_toJson(AVGsonBuffer param) throws IOException {
        param.adapter.toJson(param.sink, param.response);
        return param.sink;
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public Response kserializer_string_fromJson(KotlinxSerialization param) throws IOException {
        return Response.parse(param.kSerializer, param.json);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public ResponseJ moshi_reflective_string_fromJson(ReflectiveMoshi param) throws Exception {
        return param.adapter.fromJson(param.json);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public ResponseAV moshi_autovalue_string_fromJson(AVMoshi param) throws Exception {
        return param.adapter.fromJson(param.json);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public KRResponse moshi_kotlin_reflective_string_fromJson(ReflectiveMoshiKotlin param) throws Exception {
        return param.adapter.fromJson(param.json);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public KCGResponse moshi_kotlin_codegen_string_fromJson(CodegenMoshiKotlin param) throws Exception {
        return param.adapter.fromJson(param.json);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public ResponseAV moshi_autovalue_buffer_fromJson(AVMoshiBuffer param) throws IOException {
        return param.adapter.fromJson(param.bufferedSource);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public KRResponse moshi_kotlin_reflective_buffer_fromJson(ReflectiveMoshiKotlinBuffer param) throws IOException {
        return param.adapter.fromJson(param.bufferedSource);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public KCGResponse moshi_kotlin_codegen_buffer_fromJson(CodegenMoshiKotlinBuffer param) throws IOException {
        return param.adapter.fromJson(param.bufferedSource);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public Response gson_reflective_string_fromJson(ReflectiveGson param) throws IOException {
        return param.adapter.fromJson(param.json);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public ResponseAV gson_autovalue_string_fromJson(AVGson param) throws IOException {
        return param.adapter.fromJson(param.json);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public ResponseAV gson_autovalue_buffer_fromJson(AVGsonBuffer param) throws IOException {
        return param.adapter.fromJson(param.source);
    }

    private static <T> Class<T> getAutoValueClass(Class<T> clazz) throws ClassNotFoundException {
        String pkgName = clazz.getPackage().getName();
        String className = pkgName + ".AutoValue_" + clazz.getSimpleName();
        //noinspection unchecked
        return (Class<T>) Class.forName(className);
    }
}
