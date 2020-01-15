package dev.zacsweers.jsonserialization.android

import java.util.Locale

fun main() {

  val data = """
benchmark:     7,395,001 ns AndroidBenchmark.gson_reflective_string_fromJson[minified=true]
benchmark:     5,490,782 ns AndroidBenchmark.gson_autovalue_string_fromJson[minified=true]
benchmark:     7,442,240 ns AndroidBenchmark.moshi_autovalue_buffer_fromJson[minified=true]
benchmark:     7,117,866 ns AndroidBenchmark.moshi_autovalue_string_toJson[minified=true]
benchmark:     8,697,760 ns AndroidBenchmark.moshi_autovalue_string_fromJson[minified=true]
benchmark:   205,073,510 ns AndroidBenchmark.gson_autovalue_buffer_toJson[minified=true]
benchmark:     7,553,282 ns AndroidBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=true]
benchmark:    10,454,168 ns AndroidBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=true]
benchmark:     5,052,032 ns AndroidBenchmark.moshi_kotlin_codegen_buffer_toJson[minified=true]
benchmark:     8,746,668 ns AndroidBenchmark.moshi_kotlin_codegen_string_fromJson[minified=true]
benchmark:    11,734,532 ns AndroidBenchmark.moshi_kotlin_reflective_string_fromJson[minified=true]
benchmark:     9,461,355 ns AndroidBenchmark.moshi_kotlin_reflective_buffer_toJson[minified=true]
benchmark:     8,096,823 ns AndroidBenchmark.moshi_kotlin_reflective_metadata_buffer_toJson[minified=true]
benchmark:     7,672,917 ns AndroidBenchmark.kserializer_string_fromJson[minified=true]
benchmark:     7,686,303 ns AndroidBenchmark.gson_autovalue_string_toJson[minified=true]
benchmark:     8,185,157 ns AndroidBenchmark.moshi_reflective_string_toJson[minified=true]
benchmark:     7,072,449 ns AndroidBenchmark.moshi_kotlin_codegen_string_toJson[minified=true]
benchmark:     7,024,272 ns AndroidBenchmark.kserializer_string_toJson[minified=true]
benchmark:    10,190,886 ns AndroidBenchmark.gson_reflective_string_toJson[minified=true]
benchmark:     9,167,501 ns AndroidBenchmark.moshi_kotlin_reflective_metadata_buffer_fromJson[minified=true]
benchmark:     4,938,021 ns AndroidBenchmark.moshi_autovalue_buffer_toJson[minified=true]
benchmark:    10,757,136 ns AndroidBenchmark.moshi_reflective_string_fromJson[minified=true]
benchmark:    11,308,542 ns AndroidBenchmark.moshi_kotlin_reflective_string_toJson[minified=true]
benchmark:     7,153,439 ns AndroidBenchmark.gson_autovalue_buffer_fromJson[minified=true]
benchmark:     8,080,002 ns AndroidBenchmark.gson_reflective_string_fromJson[minified=false]
benchmark:     6,160,991 ns AndroidBenchmark.gson_autovalue_string_fromJson[minified=false]
benchmark:    10,000,887 ns AndroidBenchmark.moshi_autovalue_buffer_fromJson[minified=false]
benchmark:     6,960,678 ns AndroidBenchmark.moshi_autovalue_string_toJson[minified=false]
benchmark:    11,692,918 ns AndroidBenchmark.moshi_autovalue_string_fromJson[minified=false]
benchmark:   208,076,948 ns AndroidBenchmark.gson_autovalue_buffer_toJson[minified=false]
benchmark:    10,039,950 ns AndroidBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=false]
benchmark:    12,930,158 ns AndroidBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=false]
benchmark:     5,007,083 ns AndroidBenchmark.moshi_kotlin_codegen_buffer_toJson[minified=false]
benchmark:    11,716,408 ns AndroidBenchmark.moshi_kotlin_codegen_string_fromJson[minified=false]
benchmark:    14,747,397 ns AndroidBenchmark.moshi_kotlin_reflective_string_fromJson[minified=false]
benchmark:     9,456,408 ns AndroidBenchmark.moshi_kotlin_reflective_buffer_toJson[minified=false]
benchmark:     7,971,147 ns AndroidBenchmark.moshi_kotlin_reflective_metadata_buffer_toJson[minified=false]
benchmark:     8,395,470 ns AndroidBenchmark.kserializer_string_fromJson[minified=false]
benchmark:     7,659,845 ns AndroidBenchmark.gson_autovalue_string_toJson[minified=false]
benchmark:     8,138,594 ns AndroidBenchmark.moshi_reflective_string_toJson[minified=false]
benchmark:     6,982,136 ns AndroidBenchmark.moshi_kotlin_codegen_string_toJson[minified=false]
benchmark:     6,925,991 ns AndroidBenchmark.kserializer_string_toJson[minified=false]
benchmark:    10,263,543 ns AndroidBenchmark.gson_reflective_string_toJson[minified=false]
benchmark:    11,691,721 ns AndroidBenchmark.moshi_kotlin_reflective_metadata_buffer_fromJson[minified=false]
benchmark:     4,984,324 ns AndroidBenchmark.moshi_autovalue_buffer_toJson[minified=false]
benchmark:    13,728,440 ns AndroidBenchmark.moshi_reflective_string_fromJson[minified=false]
benchmark:    11,254,480 ns AndroidBenchmark.moshi_kotlin_reflective_string_toJson[minified=false]
benchmark:     8,308,906 ns AndroidBenchmark.gson_autovalue_buffer_fromJson[minified=false]
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