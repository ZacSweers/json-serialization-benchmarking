package dev.zacsweers.jsonserialization.jmh

import java.util.Locale

fun main() {

  val data = """
JmhBenchmark.gson_autovalue_buffer_fromJson                    thrpt   25  1222.809 ±  6.776  ops/s
JmhBenchmark.gson_autovalue_buffer_fromJson_minified           thrpt   25  1336.091 ± 16.250  ops/s
JmhBenchmark.gson_autovalue_buffer_toJson                      thrpt   25   642.586 ± 19.840  ops/s
JmhBenchmark.gson_autovalue_string_fromJson                    thrpt   25  1295.682 ± 24.112  ops/s
JmhBenchmark.gson_autovalue_string_fromJson_minified           thrpt   25  1422.573 ± 17.722  ops/s
JmhBenchmark.gson_autovalue_string_toJson                      thrpt   25  1212.849 ± 17.831  ops/s
JmhBenchmark.gson_reflective_string_fromJson                   thrpt   25  1388.944 ± 24.591  ops/s
JmhBenchmark.gson_reflective_string_toJson                     thrpt   25  1012.079 ±  9.862  ops/s
JmhBenchmark.kserializer_string_fromJson                       thrpt   25  1413.365 ± 13.823  ops/s
JmhBenchmark.kserializer_string_fromJson_minified              thrpt   25  1639.444 ± 14.787  ops/s
JmhBenchmark.kserializer_string_toJson                         thrpt   25  1340.387 ±  8.966  ops/s
JmhBenchmark.moshi_autovalue_buffer_fromJson                   thrpt   25   974.813 ± 12.968  ops/s
JmhBenchmark.moshi_autovalue_buffer_fromJson_minified          thrpt   25   959.581 ± 13.067  ops/s
JmhBenchmark.moshi_autovalue_buffer_toJson                     thrpt   25  1577.050 ± 77.249  ops/s
JmhBenchmark.moshi_autovalue_string_fromJson                   thrpt   25   888.303 ±  6.405  ops/s
JmhBenchmark.moshi_autovalue_string_fromJson_minified          thrpt   25  1199.122 ±  9.959  ops/s
JmhBenchmark.moshi_autovalue_string_toJson                     thrpt   25  1477.759 ± 12.712  ops/s
JmhBenchmark.moshi_kotlin_codegen_buffer_fromJson              thrpt   25   984.343 ± 14.441  ops/s
JmhBenchmark.moshi_kotlin_codegen_buffer_fromJson_minified     thrpt   25  1375.027 ± 35.050  ops/s
JmhBenchmark.moshi_kotlin_codegen_buffer_toJson                thrpt   25  1572.972 ± 95.974  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_fromJson              thrpt   25   872.131 ±  5.331  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_fromJson_minified     thrpt   25  1205.473 ±  7.955  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_toJson                thrpt   25  1473.877 ± 14.480  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_fromJson           thrpt   25   838.602 ±  7.488  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_fromJson_minified  thrpt   25  1128.853 ±  9.983  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_toJson             thrpt   25  1372.856 ± 22.928  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_fromJson           thrpt   25   741.541 ± 24.851  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_toJson             thrpt   25  1186.398 ± 21.733  ops/s
JmhBenchmark.moshi_reflective_string_fromJson                  thrpt   25   791.379 ± 11.537  ops/s
JmhBenchmark.moshi_reflective_string_toJson                    thrpt   25  1420.034 ± 17.002  ops/s
  """.trimIndent()

  // Skip the header line
  val results = data.lineSequence()
      .map { line ->
        // JmhBenchmark.kserializer_string_fromJson_minified              thrpt   25  2004.991 ±  15.008  ops/s
        val (benchmark, mode, count, score, _, error, units) = line.split("\\s+".toRegex())
        Analysis(
            benchmark,
            mode,
            count.toInt(),
            score.toDouble(),
            error.toDouble(),
            units
        )
      }
      .toList()

  ResultType.values().forEach {
    printResults(it, results)
  }
}

private fun printResults(type: ResultType, results: List<Analysis>) {
  val groupedResults = type.groupings.associate { grouping ->
    grouping to results.filter {
      grouping.matchFunction(it.benchmark)
    }
  }
  val benchmarkLength = results.maxBy { it.benchmark.length }!!.benchmark.length
  val scoreLength = results.maxBy { it.score.toString().length }!!.score.toString().length
  val errorLength = results.maxBy { it.error.toString().length }!!.error.toString().length

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
          val content = matchedAnalyses.sortedByDescending { it.score }
              .joinToString("\n") { it.formattedString(benchmarkLength, scoreLength, errorLength) }
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

private data class Grouping(
    val name: String,
    val matchFunction: (String) -> Boolean
)

private data class Analysis(
    val benchmark: String,
    val mode: String,
    val count: Int,
    val score: Double,
    val error: Double,
    val units: String
) {
  override fun toString() = "$benchmark\t$mode\t$count\t$score\t±\t$error\t$units"
  fun formattedString(benchmarkLength: Int, scoreLength: Int, errorLength: Int): String {
    return String.format(Locale.US,
        "%-${benchmarkLength}s  %s  %s  %${scoreLength}s  ±  %${errorLength}s  %s",
        benchmark, mode, count, score, error, units)
  }

}

private operator fun <T> List<T>.component6(): T = this[5]
private operator fun <T> List<T>.component7(): T = this[6]
