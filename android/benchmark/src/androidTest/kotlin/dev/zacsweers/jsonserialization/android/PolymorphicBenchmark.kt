package dev.zacsweers.jsonserialization.android

import androidx.benchmark.BenchmarkRule
import androidx.benchmark.measureRepeated
import androidx.test.filters.LargeTest
import com.google.common.base.Charsets
import com.google.common.io.Resources
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import dev.zacsweers.jsonserialization.models.adapter.GeneratedTypeAdapterFactory
import dev.zacsweers.jsonserialization.models.gson.RuntimeTypeAdapterFactory
import dev.zacsweers.jsonserialization.models.model_av.AbstractResponseAV
import dev.zacsweers.jsonserialization.models.model_av.ResponseAV
import dev.zacsweers.jsonserialization.models.moshiKotlinCodegen.KCGAbstractResponse
import dev.zacsweers.jsonserialization.models.moshiKotlinCodegen.KCGResponse
import kotlinx.serialization.ImplicitReflectionSerializer
import okio.Buffer
import okio.BufferedSink
import okio.BufferedSource
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.Reader
import java.io.Writer

@ImplicitReflectionSerializer
@LargeTest
@RunWith(Parameterized::class)
class PolymorphicBenchmark(
    minified: Boolean,
    typeKeyLocation: String
) {

  companion object {
    @JvmStatic
    @Parameters(name = "minified={0}")
    fun data(): List<Array<*>> {
      return listOf(
          arrayOf(true, false), // minified
          arrayOf("first", "last") // typeKeyLocation
      )
    }
  }

  class CodegenMoshiKotlinBuffer(private val json: String) {

    private val moshi: Moshi = Moshi.Builder()
        .add(
            PolymorphicJsonAdapterFactory.of(KCGAbstractResponse::class.java, "type")
                .withSubtype(KCGResponse::class.java, "type")
        )
        .build()
    lateinit var bufferedSource: BufferedSource
    lateinit var bufferedSink: BufferedSink
    val response: KCGAbstractResponse
    val adapter: JsonAdapter<KCGAbstractResponse>

    init {
      adapter = moshi.adapter(KCGAbstractResponse::class.java)
      response = adapter.fromJson(json)!!
    }

    fun setupIteration() {
      bufferedSource = Buffer().write(json.toByteArray())
      bufferedSink = Buffer()
    }
  }

  class AVGsonBuffer(private val json: String) {

    private val gson: Gson = GsonBuilder()
        .registerTypeAdapterFactory(GeneratedTypeAdapterFactory.create())
        .registerTypeAdapterFactory(
            RuntimeTypeAdapterFactory.of(AbstractResponseAV::class.java, "type")
                .registerSubtype(ResponseAV::class.java, "type"))
        .create()
    lateinit var source: Reader
    lateinit var sink: Writer
    val response: AbstractResponseAV
    val adapter: TypeAdapter<AbstractResponseAV>

    init {
      adapter = gson.getAdapter(AbstractResponseAV::class.java)
      response = adapter.fromJson(json)
    }

    fun setupIteration() {
      source = InputStreamReader(Buffer().write(json.toByteArray()).inputStream(), Charsets.UTF_8)
      sink = OutputStreamWriter(Buffer().outputStream(), Charsets.UTF_8)
    }
  }

  @get:Rule
  val benchmarkRule = BenchmarkRule()
  @Suppress("UnstableApiUsage")
  private val json = buildString {
    append("largesample")
    if (minified) {
      append("_minified")
    }
    append("_type")
    append("_")
    append(typeKeyLocation)
    append(".json")
  }.let(Resources::getResource).readText()

  @Test
  fun moshi_fromJson() = benchmarkRule.measureRepeated {
    val param = runWithTimingDisabled {
      CodegenMoshiKotlinBuffer(json).also { it.setupIteration() }
    }
    param.adapter.fromJson(param.bufferedSource)
  }

  @Test
  fun moshi_toJson() = benchmarkRule.measureRepeated {
    val param = runWithTimingDisabled {
      CodegenMoshiKotlinBuffer(json).also { it.setupIteration() }
    }
    param.adapter.toJson(param.bufferedSink, param.response)
  }

  @Test
  fun gson_fromJson() = benchmarkRule.measureRepeated {
    val param = runWithTimingDisabled {
      AVGsonBuffer(json).also { it.setupIteration() }
    }
    param.adapter.fromJson(param.source)
  }

  @Test
  fun gson_toJson() = benchmarkRule.measureRepeated {
    val param = runWithTimingDisabled {
      AVGsonBuffer(json).also { it.setupIteration() }
    }
    param.adapter.toJson(param.sink, param.response)
  }
}