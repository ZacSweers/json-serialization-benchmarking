package dev.zacsweers.jsonserialization.android

import java.util.Locale

fun main() {

  val data = """
benchmark:     5,650,730 ns AndroidBenchmark.gson_reflective_string_fromJson[minified=true]
benchmark:     4,253,281 ns AndroidBenchmark.gson_autovalue_string_fromJson[minified=true]
benchmark:     5,668,178 ns AndroidBenchmark.moshi_autovalue_buffer_fromJson[minified=true]
benchmark:     5,462,708 ns AndroidBenchmark.moshi_autovalue_string_toJson[minified=true]
benchmark:     6,586,147 ns AndroidBenchmark.moshi_autovalue_string_fromJson[minified=true]
benchmark:   161,880,016 ns AndroidBenchmark.gson_autovalue_buffer_toJson[minified=true]
benchmark:     5,767,344 ns AndroidBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=true]
benchmark:     8,197,605 ns AndroidBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=true]
benchmark:     3,814,011 ns AndroidBenchmark.moshi_kotlin_codegen_buffer_toJson[minified=true]
benchmark:     6,732,084 ns AndroidBenchmark.moshi_kotlin_codegen_string_fromJson[minified=true]
benchmark:     9,004,688 ns AndroidBenchmark.moshi_kotlin_reflective_string_fromJson[minified=true]
benchmark:     7,096,876 ns AndroidBenchmark.moshi_kotlin_reflective_buffer_toJson[minified=true]
benchmark:     6,079,063 ns AndroidBenchmark.moshi_kotlin_reflective_metadata_buffer_toJson[minified=true]
benchmark:     5,871,667 ns AndroidBenchmark.kserializer_string_fromJson[minified=true]
benchmark:     6,142,813 ns AndroidBenchmark.gson_autovalue_string_toJson[minified=true]
benchmark:     6,312,709 ns AndroidBenchmark.moshi_reflective_string_toJson[minified=true]
benchmark:     5,417,606 ns AndroidBenchmark.moshi_kotlin_codegen_string_toJson[minified=true]
benchmark:     5,388,647 ns AndroidBenchmark.kserializer_string_toJson[minified=true]
benchmark:     7,964,219 ns AndroidBenchmark.gson_reflective_string_toJson[minified=true]
benchmark:     6,948,907 ns AndroidBenchmark.moshi_kotlin_reflective_metadata_buffer_fromJson[minified=true]
benchmark:     3,860,157 ns AndroidBenchmark.moshi_autovalue_buffer_toJson[minified=true]
benchmark:     8,418,542 ns AndroidBenchmark.moshi_reflective_string_fromJson[minified=true]
benchmark:     8,544,271 ns AndroidBenchmark.moshi_kotlin_reflective_string_toJson[minified=true]
benchmark:     5,518,386 ns AndroidBenchmark.gson_autovalue_buffer_fromJson[minified=true]
benchmark:     6,219,948 ns AndroidBenchmark.gson_reflective_string_fromJson[minified=false]
benchmark:     4,702,501 ns AndroidBenchmark.gson_autovalue_string_fromJson[minified=false]
benchmark:     7,611,094 ns AndroidBenchmark.moshi_autovalue_buffer_fromJson[minified=false]
benchmark:     5,491,616 ns AndroidBenchmark.moshi_autovalue_string_toJson[minified=false]
benchmark:     9,057,656 ns AndroidBenchmark.moshi_autovalue_string_fromJson[minified=false]
benchmark:   162,346,995 ns AndroidBenchmark.gson_autovalue_buffer_toJson[minified=false]
benchmark:     7,720,990 ns AndroidBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=false]
benchmark:    10,072,657 ns AndroidBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=false]
benchmark:     3,872,553 ns AndroidBenchmark.moshi_kotlin_codegen_buffer_toJson[minified=false]
benchmark:     8,989,793 ns AndroidBenchmark.moshi_kotlin_codegen_string_fromJson[minified=false]
benchmark:    11,370,626 ns AndroidBenchmark.moshi_kotlin_reflective_string_fromJson[minified=false]
benchmark:     7,248,439 ns AndroidBenchmark.moshi_kotlin_reflective_buffer_toJson[minified=false]
benchmark:     6,205,938 ns AndroidBenchmark.moshi_kotlin_reflective_metadata_buffer_toJson[minified=false]
benchmark:     6,475,001 ns AndroidBenchmark.kserializer_string_fromJson[minified=false]
benchmark:     6,070,106 ns AndroidBenchmark.gson_autovalue_string_toJson[minified=false]
benchmark:     6,466,823 ns AndroidBenchmark.moshi_reflective_string_toJson[minified=false]
benchmark:     5,436,563 ns AndroidBenchmark.moshi_kotlin_codegen_string_toJson[minified=false]
benchmark:     5,449,376 ns AndroidBenchmark.kserializer_string_toJson[minified=false]
benchmark:     8,075,887 ns AndroidBenchmark.gson_reflective_string_toJson[minified=false]
benchmark:     8,911,042 ns AndroidBenchmark.moshi_kotlin_reflective_metadata_buffer_fromJson[minified=false]
benchmark:     3,854,062 ns AndroidBenchmark.moshi_autovalue_buffer_toJson[minified=false]
benchmark:    10,684,324 ns AndroidBenchmark.moshi_reflective_string_fromJson[minified=false]
benchmark:     8,763,907 ns AndroidBenchmark.moshi_kotlin_reflective_string_toJson[minified=false]
benchmark:     6,392,604 ns AndroidBenchmark.gson_autovalue_buffer_fromJson[minified=false]
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