package dev.zacsweers.jsonserialization.android

import java.util.Locale

fun main() {

  val data = """
benchmark:     5,687,136 ns AndroidBenchmark.gson_reflective_string_fromJson[minified=true]
benchmark:     4,257,866 ns AndroidBenchmark.gson_autovalue_string_fromJson[minified=true]
benchmark:     5,646,979 ns AndroidBenchmark.moshi_autovalue_buffer_fromJson[minified=true]
benchmark:     5,607,552 ns AndroidBenchmark.moshi_autovalue_string_toJson[minified=true]
benchmark:     6,587,761 ns AndroidBenchmark.moshi_autovalue_string_fromJson[minified=true]
benchmark:   159,264,495 ns AndroidBenchmark.gson_autovalue_buffer_toJson[minified=true]
benchmark:     5,674,636 ns AndroidBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=true]
benchmark:     8,177,969 ns AndroidBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=true]
benchmark:     3,896,510 ns AndroidBenchmark.moshi_kotlin_codegen_buffer_toJson[minified=true]
benchmark:     6,726,668 ns AndroidBenchmark.moshi_kotlin_codegen_string_fromJson[minified=true]
benchmark:     9,005,157 ns AndroidBenchmark.moshi_kotlin_reflective_string_fromJson[minified=true]
benchmark:     6,995,782 ns AndroidBenchmark.moshi_kotlin_reflective_buffer_toJson[minified=true]
benchmark:     6,077,866 ns AndroidBenchmark.moshi_kotlin_reflective_metadata_buffer_toJson[minified=true]
benchmark:     5,863,750 ns AndroidBenchmark.kserializer_string_fromJson[minified=true]
benchmark:     6,048,854 ns AndroidBenchmark.gson_autovalue_string_toJson[minified=true]
benchmark:     6,394,480 ns AndroidBenchmark.moshi_reflective_string_toJson[minified=true]
benchmark:     5,481,512 ns AndroidBenchmark.moshi_kotlin_codegen_string_toJson[minified=true]
benchmark:     5,277,344 ns AndroidBenchmark.kserializer_string_toJson[minified=true]
benchmark:     7,824,688 ns AndroidBenchmark.gson_reflective_string_toJson[minified=true]
benchmark:     6,903,908 ns AndroidBenchmark.moshi_kotlin_reflective_metadata_buffer_fromJson[minified=true]
benchmark:     3,856,147 ns AndroidBenchmark.moshi_autovalue_buffer_toJson[minified=true]
benchmark:     8,253,960 ns AndroidBenchmark.moshi_reflective_string_fromJson[minified=true]
benchmark:     8,613,803 ns AndroidBenchmark.moshi_kotlin_reflective_string_toJson[minified=true]
benchmark:     5,517,449 ns AndroidBenchmark.gson_autovalue_buffer_fromJson[minified=true]
benchmark:     6,265,209 ns AndroidBenchmark.gson_reflective_string_fromJson[minified=false]
benchmark:     4,724,739 ns AndroidBenchmark.gson_autovalue_string_fromJson[minified=false]
benchmark:     7,683,960 ns AndroidBenchmark.moshi_autovalue_buffer_fromJson[minified=false]
benchmark:     5,432,605 ns AndroidBenchmark.moshi_autovalue_string_toJson[minified=false]
benchmark:     8,930,678 ns AndroidBenchmark.moshi_autovalue_string_fromJson[minified=false]
benchmark:   158,213,610 ns AndroidBenchmark.gson_autovalue_buffer_toJson[minified=false]
benchmark:     7,659,064 ns AndroidBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=false]
benchmark:    10,072,658 ns AndroidBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=false]
benchmark:     3,835,574 ns AndroidBenchmark.moshi_kotlin_codegen_buffer_toJson[minified=false]
benchmark:     8,938,752 ns AndroidBenchmark.moshi_kotlin_codegen_string_fromJson[minified=false]
benchmark:    11,321,147 ns AndroidBenchmark.moshi_kotlin_reflective_string_fromJson[minified=false]
benchmark:     7,133,386 ns AndroidBenchmark.moshi_kotlin_reflective_buffer_toJson[minified=false]
benchmark:     6,035,417 ns AndroidBenchmark.moshi_kotlin_reflective_metadata_buffer_toJson[minified=false]
benchmark:     6,510,887 ns AndroidBenchmark.kserializer_string_fromJson[minified=false]
benchmark:     6,038,021 ns AndroidBenchmark.gson_autovalue_string_toJson[minified=false]
benchmark:     6,424,219 ns AndroidBenchmark.moshi_reflective_string_toJson[minified=false]
benchmark:     5,477,917 ns AndroidBenchmark.moshi_kotlin_codegen_string_toJson[minified=false]
benchmark:     5,295,573 ns AndroidBenchmark.kserializer_string_toJson[minified=false]
benchmark:     7,924,636 ns AndroidBenchmark.gson_reflective_string_toJson[minified=false]
benchmark:     8,989,220 ns AndroidBenchmark.moshi_kotlin_reflective_metadata_buffer_fromJson[minified=false]
benchmark:     3,864,688 ns AndroidBenchmark.moshi_autovalue_buffer_toJson[minified=false]
benchmark:    10,554,220 ns AndroidBenchmark.moshi_reflective_string_fromJson[minified=false]
benchmark:     8,505,209 ns AndroidBenchmark.moshi_kotlin_reflective_string_toJson[minified=false]
benchmark:     6,439,323 ns AndroidBenchmark.gson_autovalue_buffer_fromJson[minified=false]
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