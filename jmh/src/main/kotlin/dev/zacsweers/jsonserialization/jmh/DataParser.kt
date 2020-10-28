package dev.zacsweers.jsonserialization.jmh

import java.util.Locale

fun main() {

  val data = """
JmhBenchmark.gson_autovalue_buffer_fromJson                 true  thrpt   25  1525.482 ± 42.836  ops/s
JmhBenchmark.gson_autovalue_buffer_fromJson                false  thrpt   25  1409.158 ±  8.407  ops/s
JmhBenchmark.gson_autovalue_buffer_toJson                   true  thrpt   25   754.222 ± 15.435  ops/s
JmhBenchmark.gson_autovalue_buffer_toJson                  false  thrpt   25   760.290 ±  9.819  ops/s
JmhBenchmark.gson_autovalue_string_fromJson                 true  thrpt   25  1781.907 ± 22.567  ops/s
JmhBenchmark.gson_autovalue_string_fromJson                false  thrpt   25  1593.688 ± 48.073  ops/s
JmhBenchmark.gson_autovalue_string_toJson                   true  thrpt   25  1665.459 ± 91.108  ops/s
JmhBenchmark.gson_autovalue_string_toJson                  false  thrpt   25  1746.740 ± 13.886  ops/s
JmhBenchmark.gson_reflective_string_fromJson                true  thrpt   25  1886.678 ± 69.660  ops/s
JmhBenchmark.gson_reflective_string_fromJson               false  thrpt   25  1767.980 ± 15.606  ops/s
JmhBenchmark.gson_reflective_string_toJson                  true  thrpt   25  1321.148 ± 40.046  ops/s
JmhBenchmark.gson_reflective_string_toJson                 false  thrpt   25  1356.219 ± 34.235  ops/s
JmhBenchmark.kserializer_string_fromJson                    true  thrpt   25  1560.665 ± 21.197  ops/s
JmhBenchmark.kserializer_string_fromJson                   false  thrpt   25  1431.960 ± 22.652  ops/s
JmhBenchmark.kserializer_string_toJson                      true  thrpt   25  1387.467 ± 40.396  ops/s
JmhBenchmark.kserializer_string_toJson                     false  thrpt   25  1346.139 ± 16.223  ops/s
JmhBenchmark.moshi_autovalue_buffer_fromJson                true  thrpt   25  1599.001 ± 18.123  ops/s
JmhBenchmark.moshi_autovalue_buffer_fromJson               false  thrpt   25  1083.885 ± 25.161  ops/s
JmhBenchmark.moshi_autovalue_buffer_toJson                  true  thrpt   25  1765.929 ± 16.492  ops/s
JmhBenchmark.moshi_autovalue_buffer_toJson                 false  thrpt   25  1713.932 ± 43.726  ops/s
JmhBenchmark.moshi_autovalue_string_fromJson                true  thrpt   25  1403.610 ± 33.427  ops/s
JmhBenchmark.moshi_autovalue_string_fromJson               false  thrpt   25   993.052 ± 30.950  ops/s
JmhBenchmark.moshi_autovalue_string_toJson                  true  thrpt   25  1502.739 ±  6.458  ops/s
JmhBenchmark.moshi_autovalue_string_toJson                 false  thrpt   25  1480.423 ± 28.656  ops/s
JmhBenchmark.moshi_kotlin_codegen_buffer_fromJson           true  thrpt   25  1589.254 ± 11.012  ops/s
JmhBenchmark.moshi_kotlin_codegen_buffer_fromJson          false  thrpt   25  1070.076 ± 11.175  ops/s
JmhBenchmark.moshi_kotlin_codegen_buffer_toJson             true  thrpt   25  1857.140 ± 82.876  ops/s
JmhBenchmark.moshi_kotlin_codegen_buffer_toJson            false  thrpt   25  1751.169 ± 96.660  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_fromJson           true  thrpt   25  1413.896 ± 26.525  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_fromJson          false  thrpt   25   960.448 ± 23.985  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_toJson             true  thrpt   25  1680.681 ± 70.284  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_toJson            false  thrpt   25  1493.409 ± 17.646  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_fromJson        true  thrpt   25  1371.793 ± 12.505  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_fromJson       false  thrpt   25   966.614 ± 13.090  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_toJson          true  thrpt   25  1560.115 ± 54.810  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_toJson         false  thrpt   25  1579.324 ± 74.541  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_fromJson        true  thrpt   25  1250.486 ± 17.789  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_fromJson       false  thrpt   25   874.268 ± 15.619  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_toJson          true  thrpt   25  1291.330 ± 12.340  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_toJson         false  thrpt   25  1319.377 ± 39.128  ops/s
JmhBenchmark.moshi_reflective_string_fromJson               true  thrpt   25  1373.449 ±  5.726  ops/s
JmhBenchmark.moshi_reflective_string_fromJson              false  thrpt   25   932.438 ± 16.473  ops/s
JmhBenchmark.moshi_reflective_string_toJson                 true  thrpt   25  1441.979 ± 11.832  ops/s
JmhBenchmark.moshi_reflective_string_toJson                false  thrpt   25  1503.545 ± 63.463  ops/s
  """.trimIndent()

  // Skip the header line
  val results = data.lineSequence()
      .mapNotNull { line ->
        // JmhBenchmark.kserializer_string_fromJson_minified              thrpt   25  2004.991 ±  15.008  ops/s
        val (benchmark, minified, mode, count, score, _, error, units) = line.split("\\s+".toRegex())
        if ("_toJson" in benchmark && !minified.toBoolean()) {
          // Minified doesn't matter in toJson so just filter them out
          return@mapNotNull null
        }
        Analysis(
            "$benchmark[minified=$minified]",
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
          },
          Grouping("MoshiX Kotlin (Metadata)") {
            "moshix_kotlin" in it
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
private operator fun <T> List<T>.component8(): T = this[7]
