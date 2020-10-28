package dev.zacsweers.jsonserialization.android

import java.util.Locale

fun main() {

  val data = """
benchmark:     5,678,593 ns AndroidBenchmark.gson_reflective_string_fromJson[minified=true]
benchmark:     4,854,687 ns AndroidBenchmark.gson_autovalue_string_fromJson[minified=true]
benchmark:     5,705,469 ns AndroidBenchmark.moshi_autovalue_buffer_fromJson[minified=true]
benchmark:     5,413,751 ns AndroidBenchmark.moshi_autovalue_string_toJson[minified=true]
benchmark:     6,669,532 ns AndroidBenchmark.moshi_autovalue_string_fromJson[minified=true]
benchmark:   160,393,974 ns AndroidBenchmark.gson_autovalue_buffer_toJson[minified=true]
benchmark:     5,735,052 ns AndroidBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=true]
benchmark:     8,100,000 ns AndroidBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=true]
benchmark:     3,825,365 ns AndroidBenchmark.moshi_kotlin_codegen_buffer_toJson[minified=true]
benchmark:     6,673,698 ns AndroidBenchmark.moshi_kotlin_codegen_string_fromJson[minified=true]
benchmark:     9,024,428 ns AndroidBenchmark.moshi_kotlin_reflective_string_fromJson[minified=true]
benchmark:     7,219,272 ns AndroidBenchmark.moshi_kotlin_reflective_buffer_toJson[minified=true]
benchmark:     5,772,345 ns AndroidBenchmark.kserializer_string_fromJson[minified=true]
benchmark:     6,162,865 ns AndroidBenchmark.gson_autovalue_string_toJson[minified=true]
benchmark:     6,316,824 ns AndroidBenchmark.moshi_reflective_string_toJson[minified=true]
benchmark:     5,442,292 ns AndroidBenchmark.moshi_kotlin_codegen_string_toJson[minified=true]
benchmark:     5,368,125 ns AndroidBenchmark.kserializer_string_toJson[minified=true]
benchmark:     7,916,772 ns AndroidBenchmark.gson_reflective_string_toJson[minified=true]
benchmark:     3,760,470 ns AndroidBenchmark.moshi_autovalue_buffer_toJson[minified=true]
benchmark:     8,197,866 ns AndroidBenchmark.moshi_reflective_string_fromJson[minified=true]
benchmark:     8,733,439 ns AndroidBenchmark.moshi_kotlin_reflective_string_toJson[minified=true]
benchmark:     6,195,417 ns AndroidBenchmark.gson_autovalue_buffer_fromJson[minified=true]
benchmark:     6,346,979 ns AndroidBenchmark.gson_reflective_string_fromJson[minified=false]
benchmark:     5,340,678 ns AndroidBenchmark.gson_autovalue_string_fromJson[minified=false]
benchmark:     7,641,979 ns AndroidBenchmark.moshi_autovalue_buffer_fromJson[minified=false]
benchmark:     5,477,969 ns AndroidBenchmark.moshi_autovalue_string_toJson[minified=false]
benchmark:     8,940,730 ns AndroidBenchmark.moshi_autovalue_string_fromJson[minified=false]
benchmark:   160,235,693 ns AndroidBenchmark.gson_autovalue_buffer_toJson[minified=false]
benchmark:     7,740,677 ns AndroidBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=false]
benchmark:    10,143,074 ns AndroidBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=false]
benchmark:     3,786,511 ns AndroidBenchmark.moshi_kotlin_codegen_buffer_toJson[minified=false]
benchmark:     8,981,979 ns AndroidBenchmark.moshi_kotlin_codegen_string_fromJson[minified=false]
benchmark:    11,272,241 ns AndroidBenchmark.moshi_kotlin_reflective_string_fromJson[minified=false]
benchmark:     7,118,177 ns AndroidBenchmark.moshi_kotlin_reflective_buffer_toJson[minified=false]
benchmark:     6,430,939 ns AndroidBenchmark.kserializer_string_fromJson[minified=false]
benchmark:     6,150,938 ns AndroidBenchmark.gson_autovalue_string_toJson[minified=false]
benchmark:     6,378,959 ns AndroidBenchmark.moshi_reflective_string_toJson[minified=false]
benchmark:     5,425,104 ns AndroidBenchmark.moshi_kotlin_codegen_string_toJson[minified=false]
benchmark:     5,257,553 ns AndroidBenchmark.kserializer_string_toJson[minified=false]
benchmark:     7,851,667 ns AndroidBenchmark.gson_reflective_string_toJson[minified=false]
benchmark:     3,734,376 ns AndroidBenchmark.moshi_autovalue_buffer_toJson[minified=false]
benchmark:    10,500,313 ns AndroidBenchmark.moshi_reflective_string_fromJson[minified=false]
benchmark:     8,552,606 ns AndroidBenchmark.moshi_kotlin_reflective_string_toJson[minified=false]
benchmark:     7,027,032 ns AndroidBenchmark.gson_autovalue_buffer_fromJson[minified=false]
  """.trimIndent()

  // Skip the header line
  val results = data.lineSequence()
      .map { line ->
        // benchmark:     6,154,949 ns SpeedTest.gson_autovalue_buffer_fromJson_minified
        val (_, score, units, benchmark) = line.split("\\s+".toRegex())
        Analysis(
            benchmark = benchmark,
            score = score.replace(",", "").toLong(),
            units = units
        )
      }
      .filterNot {
        // Minified doesn't matter in toJson, so filter out half of them
        "_toJson" in it.benchmark && "minified=false" in it.benchmark
      }
      .toList()

  ResultType.values().forEach { printResults(it, results) }
}

private fun printResults(type: ResultType, results: List<Analysis>) {
  val groupedResults = type.groupings.associateWith { grouping ->
    results.filter {
      grouping.matchFunction(it.benchmark)
    }
  }
  val benchmarkLength = results.maxBy { it.benchmark.length }!!.benchmark.length
  val scoreLength = results.maxBy { it.formattedScore.length }!!.formattedScore.length

  check(groupedResults.values.flatten().size == results.size) {
    "Missing information!"
  }

  val output = buildString {
    appendln()
    append(type.description)
    appendln(':')
    appendln()
    appendln("```")
    groupedResults.entries
        .joinTo(this, "\n\n", postfix = "\n```") { (grouping, matchedAnalyses) ->
          val content = matchedAnalyses.sortedBy { it.score }
              .joinToString("\n") { it.formattedString(benchmarkLength, scoreLength) }
          "${grouping.name}\n$content"
        }
  }

  println(output)
}

private enum class ResultType(val description: String, val groupings: List<Grouping>) {
  SERIALIZATION_TYPE(
      description = "Grouped by serialization type (read, write, buffered, string)",
      groupings = listOf(
          Grouping("Read (buffered)") {
            "_buffer" in it && "_fromJson" in it
          },
          Grouping("Read (string)") {
            "_string" in it && "_fromJson" in it
          },
          Grouping("Write (buffered)") {
            "_buffer" in it && "_toJson" in it
          },
          Grouping("Write (string)") {
            "_string" in it && "_toJson" in it
          }
      )
  ),
  LIBRARY(
      description = "Grouped by library (interesting to see how reflection vs custom adapters affects perf within a library)",
      groupings = listOf(
          Grouping("GSON") {
            "gson" in it
          },
          Grouping("Kotlinx Serialization") {
            "kserializer" in it
          },
          Grouping("Moshi") {
            "moshi_" in it && "kotlin" !in it
          },
          Grouping("Moshi Kotlin") {
            "moshi_kotlin" in it
          },
          Grouping("MoshiX Kotlin (Metadata)") {
            "moshix_kotlin" in it
          }
      )
  )
}

internal data class Grouping(
    val name: String,
    val matchFunction: (String) -> Boolean
)

internal data class Analysis(
    val benchmark: String,
    val score: Long,
    val units: String
) {
  override fun toString() = "$benchmark\t$score\t$units"

  fun formattedString(benchmarkLength: Int, scoreLength: Int): String {
    return String.format(Locale.US,
        "%-${benchmarkLength}s  %${scoreLength}s  %s",
        benchmark, formattedScore, units)
  }

  val formattedScore: String
    get() = String.format(Locale.US, "%,d", score)
}

internal operator fun <T> List<T>.component6(): T = this[5]
internal operator fun <T> List<T>.component7(): T = this[6]