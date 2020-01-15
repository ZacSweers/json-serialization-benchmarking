package dev.zacsweers.jsonserialization.jmh

import java.util.Locale

fun main() {

  val data = """
JmhBenchmark.gson_autovalue_buffer_fromJson                          true  thrpt   25  1694.589 ± 16.038  ops/s
JmhBenchmark.gson_autovalue_buffer_fromJson                         false  thrpt   25  1429.873 ± 53.567  ops/s
JmhBenchmark.gson_autovalue_buffer_toJson                            true  thrpt   25   770.072 ±  6.531  ops/s
JmhBenchmark.gson_autovalue_buffer_toJson                           false  thrpt   25   766.374 ±  6.326  ops/s
JmhBenchmark.gson_autovalue_string_fromJson                          true  thrpt   25  1978.363 ± 23.024  ops/s
JmhBenchmark.gson_autovalue_string_fromJson                         false  thrpt   25  1697.228 ± 54.541  ops/s
JmhBenchmark.gson_autovalue_string_toJson                            true  thrpt   25  1651.928 ± 55.944  ops/s
JmhBenchmark.gson_autovalue_string_toJson                           false  thrpt   25  1634.247 ± 90.446  ops/s
JmhBenchmark.gson_reflective_string_fromJson                         true  thrpt   25  1854.543 ± 65.485  ops/s
JmhBenchmark.gson_reflective_string_fromJson                        false  thrpt   25  1631.023 ± 91.259  ops/s
JmhBenchmark.gson_reflective_string_toJson                           true  thrpt   25  1256.877 ± 51.406  ops/s
JmhBenchmark.gson_reflective_string_toJson                          false  thrpt   25  1224.881 ± 21.644  ops/s
JmhBenchmark.kserializer_string_fromJson                             true  thrpt   25  1516.173 ± 16.853  ops/s
JmhBenchmark.kserializer_string_fromJson                            false  thrpt   25  1431.558 ± 16.761  ops/s
JmhBenchmark.kserializer_string_toJson                               true  thrpt   25  1312.221 ± 53.840  ops/s
JmhBenchmark.kserializer_string_toJson                              false  thrpt   25  1340.611 ± 35.620  ops/s
JmhBenchmark.moshi_autovalue_buffer_fromJson                         true  thrpt   25  1548.544 ± 35.862  ops/s
JmhBenchmark.moshi_autovalue_buffer_fromJson                        false  thrpt   25  1069.282 ± 11.627  ops/s
JmhBenchmark.moshi_autovalue_buffer_toJson                           true  thrpt   25  1725.926 ± 12.618  ops/s
JmhBenchmark.moshi_autovalue_buffer_toJson                          false  thrpt   25  1742.968 ± 79.461  ops/s
JmhBenchmark.moshi_autovalue_string_fromJson                         true  thrpt   25  1347.846 ± 28.476  ops/s
JmhBenchmark.moshi_autovalue_string_fromJson                        false  thrpt   25   944.124 ± 22.116  ops/s
JmhBenchmark.moshi_autovalue_string_toJson                           true  thrpt   25  1438.208 ± 16.157  ops/s
JmhBenchmark.moshi_autovalue_string_toJson                          false  thrpt   25  1506.431 ± 60.167  ops/s
JmhBenchmark.moshi_kotlin_codegen_buffer_fromJson                    true  thrpt   25  1528.458 ± 31.330  ops/s
JmhBenchmark.moshi_kotlin_codegen_buffer_fromJson                   false  thrpt   25  1047.044 ± 15.846  ops/s
JmhBenchmark.moshi_kotlin_codegen_buffer_toJson                      true  thrpt   25  1749.996 ± 69.528  ops/s
JmhBenchmark.moshi_kotlin_codegen_buffer_toJson                     false  thrpt   25  1615.732 ± 49.429  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_fromJson                    true  thrpt   25  1337.028 ± 31.004  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_fromJson                   false  thrpt   25   949.187 ± 25.544  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_toJson                      true  thrpt   25  1493.300 ± 61.486  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_toJson                     false  thrpt   25  1450.959 ± 40.108  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_fromJson                 true  thrpt   25  1317.167 ± 11.294  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_fromJson                false  thrpt   25   946.928 ±  3.910  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_toJson                   true  thrpt   25  1478.326 ± 17.000  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_toJson                  false  thrpt   25  1519.779 ± 60.833  ops/s
JmhBenchmark.moshi_kotlin_reflective_metadata_buffer_fromJson        true  thrpt   25  1386.728 ± 15.615  ops/s
JmhBenchmark.moshi_kotlin_reflective_metadata_buffer_fromJson       false  thrpt   25   976.127 ±  4.783  ops/s
JmhBenchmark.moshi_kotlin_reflective_metadata_buffer_toJson          true  thrpt   25  1592.001 ± 81.603  ops/s
JmhBenchmark.moshi_kotlin_reflective_metadata_buffer_toJson         false  thrpt   25  1679.985 ± 72.774  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_fromJson                 true  thrpt   25  1215.453 ± 20.914  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_fromJson                false  thrpt   25   875.290 ±  6.342  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_toJson                   true  thrpt   25  1281.294 ±  8.931  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_toJson                  false  thrpt   25  1300.308 ± 34.613  ops/s
JmhBenchmark.moshi_reflective_string_fromJson                        true  thrpt   25  1316.507 ± 34.060  ops/s
JmhBenchmark.moshi_reflective_string_fromJson                       false  thrpt   25   929.292 ± 22.495  ops/s
JmhBenchmark.moshi_reflective_string_toJson                          true  thrpt   25  1379.795 ± 19.318  ops/s
JmhBenchmark.moshi_reflective_string_toJson                         false  thrpt   25  1415.515 ± 55.968  ops/s
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
