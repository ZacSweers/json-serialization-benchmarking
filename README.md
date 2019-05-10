Misc benchmarks for json serialization

## Notes

- This test compares...
  - Gson, moshi, kotlinx serialization
  - Reflective vs streaming APIs (via code gen)
  - Moshi's kotlin support (reflective or code gen for streaming APIs)
- Note kotlinx serialization only works via the standard compiler plugin
- "Minified" means the json was minified with whitespaces between keys and values removed.
- AutoValue APIs are done via AutoValue models with the auto-value-gson/moshi extensions, thus using generated adapters.
- Kotlin codegen generates kotlin JsonAdapters.
- Reflection is done usually on a simple class due to limitations of instantiation. Moshi's reflection library is the exception (it can handle `data class` types)
- A custom snapshot of Okio with a more performant trie-based implementation for the select API
- String serialization is a practical example, buffered input/output is likely more representative of how your stack works in terms of I/O

## TL;DR

* Fastest reader: Moshi
* Fastest writer: Moshi

Speed is not everything though! Different libraries come with different trade offs. That said, these *are* benchmarks.

## Current raw results (higher score is better)

Run on a mid-2015 15" Macbook Pro. 2.8 GHz Intel Core i7, 16 GB 1600 MHz DDR3

Raw

```
Benchmark                                                    Mode  Cnt     Score    Error  Units
SpeedTest.gson_autovalue_buffer_fromJson                    thrpt   25  1222.809 ±  6.776  ops/s
SpeedTest.gson_autovalue_buffer_fromJson_minified           thrpt   25  1336.091 ± 16.250  ops/s
SpeedTest.gson_autovalue_buffer_toJson                      thrpt   25   642.586 ± 19.840  ops/s
SpeedTest.gson_autovalue_string_fromJson                    thrpt   25  1295.682 ± 24.112  ops/s
SpeedTest.gson_autovalue_string_fromJson_minified           thrpt   25  1422.573 ± 17.722  ops/s
SpeedTest.gson_autovalue_string_toJson                      thrpt   25  1212.849 ± 17.831  ops/s
SpeedTest.gson_reflective_string_fromJson                   thrpt   25  1388.944 ± 24.591  ops/s
SpeedTest.gson_reflective_string_toJson                     thrpt   25  1012.079 ±  9.862  ops/s
SpeedTest.kserializer_string_fromJson                       thrpt   25  1413.365 ± 13.823  ops/s
SpeedTest.kserializer_string_fromJson_minified              thrpt   25  1639.444 ± 14.787  ops/s
SpeedTest.kserializer_string_toJson                         thrpt   25  1340.387 ±  8.966  ops/s
SpeedTest.moshi_autovalue_buffer_fromJson                   thrpt   25   974.813 ± 12.968  ops/s
SpeedTest.moshi_autovalue_buffer_fromJson_minified          thrpt   25   959.581 ± 13.067  ops/s
SpeedTest.moshi_autovalue_buffer_toJson                     thrpt   25  1577.050 ± 77.249  ops/s
SpeedTest.moshi_autovalue_string_fromJson                   thrpt   25   888.303 ±  6.405  ops/s
SpeedTest.moshi_autovalue_string_fromJson_minified          thrpt   25  1199.122 ±  9.959  ops/s
SpeedTest.moshi_autovalue_string_toJson                     thrpt   25  1477.759 ± 12.712  ops/s
SpeedTest.moshi_kotlin_codegen_buffer_fromJson              thrpt   25   984.343 ± 14.441  ops/s
SpeedTest.moshi_kotlin_codegen_buffer_fromJson_minified     thrpt   25  1375.027 ± 35.050  ops/s
SpeedTest.moshi_kotlin_codegen_buffer_toJson                thrpt   25  1572.972 ± 95.974  ops/s
SpeedTest.moshi_kotlin_codegen_string_fromJson              thrpt   25   872.131 ±  5.331  ops/s
SpeedTest.moshi_kotlin_codegen_string_fromJson_minified     thrpt   25  1205.473 ±  7.955  ops/s
SpeedTest.moshi_kotlin_codegen_string_toJson                thrpt   25  1473.877 ± 14.480  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_fromJson           thrpt   25   838.602 ±  7.488  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_fromJson_minified  thrpt   25  1128.853 ±  9.983  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_toJson             thrpt   25  1372.856 ± 22.928  ops/s
SpeedTest.moshi_kotlin_reflective_string_fromJson           thrpt   25   741.541 ± 24.851  ops/s
SpeedTest.moshi_kotlin_reflective_string_toJson             thrpt   25  1186.398 ± 21.733  ops/s
SpeedTest.moshi_reflective_string_fromJson                  thrpt   25   791.379 ± 11.537  ops/s
SpeedTest.moshi_reflective_string_toJson                    thrpt   25  1420.034 ± 17.002  ops/s
```

Grouped by serialization type (read, write, buffered, string)

```
Read (buffered)
SpeedTest.moshi_kotlin_codegen_buffer_fromJson_minified	thrpt	25	1375.027	±	35.05	ops/s
SpeedTest.gson_autovalue_buffer_fromJson_minified	thrpt	25	1336.091	±	16.25	ops/s
SpeedTest.gson_autovalue_buffer_fromJson	thrpt	25	1222.809	±	6.776	ops/s
SpeedTest.moshi_kotlin_reflective_buffer_fromJson_minified	thrpt	25	1128.853	±	9.983	ops/s
SpeedTest.moshi_kotlin_codegen_buffer_fromJson	thrpt	25	984.343	±	14.441	ops/s
SpeedTest.moshi_autovalue_buffer_fromJson	thrpt	25	974.813	±	12.968	ops/s
SpeedTest.moshi_autovalue_buffer_fromJson_minified	thrpt	25	959.581	±	13.067	ops/s
SpeedTest.moshi_kotlin_reflective_buffer_fromJson	thrpt	25	838.602	±	7.488	ops/s

Read (string)
SpeedTest.kserializer_string_fromJson_minified	thrpt	25	1639.444	±	14.787	ops/s
SpeedTest.gson_autovalue_string_fromJson_minified	thrpt	25	1422.573	±	17.722	ops/s
SpeedTest.kserializer_string_fromJson	thrpt	25	1413.365	±	13.823	ops/s
SpeedTest.gson_reflective_string_fromJson	thrpt	25	1388.944	±	24.591	ops/s
SpeedTest.gson_autovalue_string_fromJson	thrpt	25	1295.682	±	24.112	ops/s
SpeedTest.moshi_kotlin_codegen_string_fromJson_minified	thrpt	25	1205.473	±	7.955	ops/s
SpeedTest.moshi_autovalue_string_fromJson_minified	thrpt	25	1199.122	±	9.959	ops/s
SpeedTest.moshi_autovalue_string_fromJson	thrpt	25	888.303	±	6.405	ops/s
SpeedTest.moshi_kotlin_codegen_string_fromJson	thrpt	25	872.131	±	5.331	ops/s
SpeedTest.moshi_reflective_string_fromJson	thrpt	25	791.379	±	11.537	ops/s
SpeedTest.moshi_kotlin_reflective_string_fromJson	thrpt	25	741.541	±	24.851	ops/s

Write (buffered)
SpeedTest.moshi_autovalue_buffer_toJson	thrpt	25	1577.05	±	77.249	ops/s
SpeedTest.moshi_kotlin_codegen_buffer_toJson	thrpt	25	1572.972	±	95.974	ops/s
SpeedTest.moshi_kotlin_reflective_buffer_toJson	thrpt	25	1372.856	±	22.928	ops/s
SpeedTest.gson_autovalue_buffer_toJson	thrpt	25	642.586	±	19.84	ops/s

Write (string)
SpeedTest.moshi_autovalue_string_toJson	thrpt	25	1477.759	±	12.712	ops/s
SpeedTest.moshi_kotlin_codegen_string_toJson	thrpt	25	1473.877	±	14.48	ops/s
SpeedTest.moshi_reflective_string_toJson	thrpt	25	1420.034	±	17.002	ops/s
SpeedTest.kserializer_string_toJson	thrpt	25	1340.387	±	8.966	ops/s
SpeedTest.gson_autovalue_string_toJson	thrpt	25	1212.849	±	17.831	ops/s
SpeedTest.moshi_kotlin_reflective_string_toJson	thrpt	25	1186.398	±	21.733	ops/s
SpeedTest.gson_reflective_string_toJson	thrpt	25	1012.079	±	9.862	ops/s
```

Grouped by library (interesting to see how reflection vs custom adapters affects perf within a library)

```
GSON
SpeedTest.gson_autovalue_string_fromJson_minified	thrpt	25	1422.573	±	17.722	ops/s
SpeedTest.gson_reflective_string_fromJson	thrpt	25	1388.944	±	24.591	ops/s
SpeedTest.gson_autovalue_buffer_fromJson_minified	thrpt	25	1336.091	±	16.25	ops/s
SpeedTest.gson_autovalue_string_fromJson	thrpt	25	1295.682	±	24.112	ops/s
SpeedTest.gson_autovalue_buffer_fromJson	thrpt	25	1222.809	±	6.776	ops/s
SpeedTest.gson_autovalue_string_toJson	thrpt	25	1212.849	±	17.831	ops/s
SpeedTest.gson_reflective_string_toJson	thrpt	25	1012.079	±	9.862	ops/s
SpeedTest.gson_autovalue_buffer_toJson	thrpt	25	642.586	±	19.84	ops/s

Kotlinx Serialization
SpeedTest.kserializer_string_fromJson_minified	thrpt	25	1639.444	±	14.787	ops/s
SpeedTest.kserializer_string_fromJson	thrpt	25	1413.365	±	13.823	ops/s
SpeedTest.kserializer_string_toJson	thrpt	25	1340.387	±	8.966	ops/s

Moshi
SpeedTest.moshi_autovalue_buffer_toJson	thrpt	25	1577.05	±	77.249	ops/s
SpeedTest.moshi_autovalue_string_toJson	thrpt	25	1477.759	±	12.712	ops/s
SpeedTest.moshi_reflective_string_toJson	thrpt	25	1420.034	±	17.002	ops/s
SpeedTest.moshi_autovalue_string_fromJson_minified	thrpt	25	1199.122	±	9.959	ops/s
SpeedTest.moshi_autovalue_buffer_fromJson	thrpt	25	974.813	±	12.968	ops/s
SpeedTest.moshi_autovalue_buffer_fromJson_minified	thrpt	25	959.581	±	13.067	ops/s
SpeedTest.moshi_autovalue_string_fromJson	thrpt	25	888.303	±	6.405	ops/s
SpeedTest.moshi_reflective_string_fromJson	thrpt	25	791.379	±	11.537	ops/s

Moshi Kotlin
SpeedTest.moshi_kotlin_codegen_buffer_toJson	thrpt	25	1572.972	±	95.974	ops/s
SpeedTest.moshi_kotlin_codegen_string_toJson	thrpt	25	1473.877	±	14.48	ops/s
SpeedTest.moshi_kotlin_codegen_buffer_fromJson_minified	thrpt	25	1375.027	±	35.05	ops/s
SpeedTest.moshi_kotlin_reflective_buffer_toJson	thrpt	25	1372.856	±	22.928	ops/s
SpeedTest.moshi_kotlin_codegen_string_fromJson_minified	thrpt	25	1205.473	±	7.955	ops/s
SpeedTest.moshi_kotlin_reflective_string_toJson	thrpt	25	1186.398	±	21.733	ops/s
SpeedTest.moshi_kotlin_reflective_buffer_fromJson_minified	thrpt	25	1128.853	±	9.983	ops/s
SpeedTest.moshi_kotlin_codegen_buffer_fromJson	thrpt	25	984.343	±	14.441	ops/s
SpeedTest.moshi_kotlin_codegen_string_fromJson	thrpt	25	872.131	±	5.331	ops/s
SpeedTest.moshi_kotlin_reflective_buffer_fromJson	thrpt	25	838.602	±	7.488	ops/s
SpeedTest.moshi_kotlin_reflective_string_fromJson	thrpt	25	741.541	±	24.851	ops/s
```

To run:

`./gradlew jmh`

You can use `DataParser.kt` to parse the raw results (starting with the first benchmark line after the headers).
