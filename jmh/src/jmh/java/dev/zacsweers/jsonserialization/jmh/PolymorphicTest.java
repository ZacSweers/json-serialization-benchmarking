package dev.zacsweers.jsonserialization.jmh;

import dev.zacsweers.jsonserialization.models.adapter.GeneratedTypeAdapterFactory;
import dev.zacsweers.jsonserialization.models.gson.RuntimeTypeAdapterFactory;
import dev.zacsweers.jsonserialization.models.model_av.AbstractResponseAV;
import dev.zacsweers.jsonserialization.models.model_av.ResponseAV;
import dev.zacsweers.jsonserialization.models.moshiKotlinCodegen.KCGAbstractResponse;
import dev.zacsweers.jsonserialization.models.moshiKotlinCodegen.KCGResponse;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.concurrent.TimeUnit;
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
public class PolymorphicTest {

  @State(Scope.Benchmark)
  public static class CodegenMoshiKotlinBuffer {

    @Param({"first", "last"})
    public String typeKeyLocation;

    @Param({"true", "false"})
    public boolean minified;

    @Setup
    public void setupTrial() throws Exception {
      moshi = new Moshi.Builder()
          .add(
              PolymorphicJsonAdapterFactory.of(KCGAbstractResponse.class, "type")
                  .withSubtype(KCGResponse.class, "type"))
          .build();
      StringBuilder urlBuilder = new StringBuilder("largesample");
      if (minified) {
        urlBuilder.append("_minified");
      }
      urlBuilder.append("_type").append("_").append(typeKeyLocation).append(".json");
      URL url = Resources.getResource(urlBuilder.toString());
      json = Resources.toString(url, Charsets.UTF_8);
      adapter = moshi.adapter(KCGAbstractResponse.class);
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
    public KCGAbstractResponse response;
    public JsonAdapter<KCGAbstractResponse> adapter;
  }

  @State(Scope.Benchmark)
  public static class AVGsonBuffer {

    @Param({"first", "last"})
    public String typeKeyLocation;

    @Param({"true", "false"})
    public boolean minified;

    @Setup
    public void setupTrial() throws Exception {
      gson = new GsonBuilder()
          .registerTypeAdapterFactory(GeneratedTypeAdapterFactory.create())
          .registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(AbstractResponseAV.class, "type")
              .registerSubtype(ResponseAV.class, "type"))
          .create();
      StringBuilder urlBuilder = new StringBuilder("largesample");
      if (minified) {
        urlBuilder.append("_minified");
      }
      urlBuilder.append("_type").append("_").append(typeKeyLocation).append(".json");
      URL url = Resources.getResource(urlBuilder.toString());
      json = Resources.toString(url, Charsets.UTF_8);
      adapter = gson.getAdapter(AbstractResponseAV.class);
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
    public AbstractResponseAV response;
    public TypeAdapter<AbstractResponseAV> adapter;
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public AbstractResponseAV gson_fromJson(AVGsonBuffer param) throws
      IOException {
    return param.adapter.fromJson(param.source);
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public KCGAbstractResponse moshi_fromJson(CodegenMoshiKotlinBuffer param) throws
      IOException {
    return param.adapter.fromJson(param.bufferedSource);
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public Writer gson_toJson(AVGsonBuffer param) throws IOException {
    param.adapter.toJson(param.sink, param.response);
    return param.sink;
  }

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.SECONDS)
  public BufferedSink moshi_toJson(CodegenMoshiKotlinBuffer param) throws IOException {
    param.adapter.toJson(param.bufferedSink, param.response);
    return param.bufferedSink;
  }

}
