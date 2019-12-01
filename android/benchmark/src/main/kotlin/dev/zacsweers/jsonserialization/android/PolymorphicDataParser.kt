package dev.zacsweers.jsonserialization.android

fun main() {

  val data = """
benchmark:     5,741,043 ns PolymorphicBenchmark.moshi_fromJson[minified=true,typeKeyLocation=first]
benchmark:     7,726,407 ns PolymorphicBenchmark.moshi_fromJson[minified=false,typeKeyLocation=first]
benchmark:     9,240,001 ns PolymorphicBenchmark.moshi_fromJson[minified=true,typeKeyLocation=last]
benchmark:     9,511,094 ns PolymorphicBenchmark.gson_fromJson[minified=true,typeKeyLocation=last]
benchmark:     9,530,990 ns PolymorphicBenchmark.gson_fromJson[minified=true,typeKeyLocation=first]
benchmark:    10,419,532 ns PolymorphicBenchmark.gson_fromJson[minified=false,typeKeyLocation=first]
benchmark:    10,473,907 ns PolymorphicBenchmark.gson_fromJson[minified=false,typeKeyLocation=last]
benchmark:    14,063,751 ns PolymorphicBenchmark.moshi_fromJson[minified=false,typeKeyLocation=last]
benchmark:     7,679,844 ns PolymorphicBenchmark.moshi_toJson[minified=true,typeKeyLocation=first]
benchmark:     7,688,855 ns PolymorphicBenchmark.moshi_toJson[minified=true,typeKeyLocation=last]
benchmark:   163,759,807 ns PolymorphicBenchmark.gson_toJson[minified=true,typeKeyLocation=last]
benchmark:   165,742,933 ns PolymorphicBenchmark.gson_toJson[minified=true,typeKeyLocation=first]
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

  PolymorphicResultType.values().forEach { printResults(it, results) }
}

private fun printResults(type: PolymorphicResultType, results: List<Analysis>) {
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

private enum class PolymorphicResultType(val description: String, val groupings: List<Grouping>) {
  SERIALIZATION_TYPE(
      description = "Grouped by serialization type (read, write)",
      groupings = listOf(
          Grouping("Read") {
            "_fromJson" in it
          },
          Grouping("Write") {
            "_toJson" in it
          }
      )
  )
}

