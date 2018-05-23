Misc benchmarks for json serialization

## Notes

- This test compares...
  - Gson, moshi, kotlinx serialization, and Kryo (as an alternative serialization comparison)
  - Reflective vs streaming APIs (via code gen)
  - Moshi's kotlin support (reflective or code gen for streaming APIs)
- Note kotlinx serialization and kryo only work via reflection (kryo) or the standard compiler plugin (kotlin)
- "Minified" means the json was minified with whitespaces between keys and values removed.
- AutoValue APIs are done via AutoValue models with the auto-value-gson/moshi extensions, thus using generated adapters.
- Kotlin codegen generates kotlin JsonAdapters.
- Reflection is done usually on a simple class due to limitations of instantiation. Moshi's reflection library is the exception (it can handle `data class` types)
- A custom snapshot of Okio with a more performant trie-based implementation for the select API
- String serialization is a practical example, buffered input/output is likely more representative of how your stack works in terms of I/O

## TL;DR

* Fastest reader: GSON
* Fastest writer: Moshi

Speed is not everything though! Different libraries come with different tradeoffs. That said, these *are* benchmarks.

## Current raw results (higher score is better)

Run on a mid-2015 15" Macbook Pro. 2.8 GHz Intel Core i7, 16 GB 1600 MHz DDR3

```
# Run complete. Total time: 03:37:38

Benchmark                                                    Mode  Cnt     Score    Error  Units
SpeedTest.gson_autovalue_buffer_fromJson                    thrpt  200  1305.687 ±  4.756  ops/s
SpeedTest.gson_autovalue_buffer_fromJson_minified           thrpt  200  1500.076 ±  3.238  ops/s
SpeedTest.gson_autovalue_buffer_toJson                      thrpt  200   616.999 ±  1.365  ops/s
SpeedTest.gson_autovalue_string_fromJson                    thrpt  200  1501.642 ± 16.501  ops/s
SpeedTest.gson_autovalue_string_fromJson_minified           thrpt  200  1610.007 ± 23.511  ops/s
SpeedTest.gson_autovalue_string_toJson                      thrpt  200  1221.374 ±  2.972  ops/s
SpeedTest.gson_reflective_string_fromJson                   thrpt  200  1429.620 ±  8.491  ops/s
SpeedTest.gson_reflective_string_toJson                     thrpt  200   977.967 ±  4.039  ops/s
SpeedTest.kryo_fromBytes                                    thrpt  200  1337.743 ±  3.532  ops/s
SpeedTest.kryo_toBytes                                      thrpt  200   951.468 ±  1.999  ops/s
SpeedTest.kserializer_string_fromJson                       thrpt  200   933.622 ± 58.509  ops/s
SpeedTest.kserializer_string_fromJson_minified              thrpt  200  1116.711 ± 68.464  ops/s
SpeedTest.kserializer_string_toJson                         thrpt  200  1197.405 ± 13.311  ops/s
SpeedTest.moshi_autovalue_buffer_fromJson                   thrpt  200  1019.747 ±  4.865  ops/s
SpeedTest.moshi_autovalue_buffer_fromJson_minified          thrpt  200  1374.263 ±  4.125  ops/s
SpeedTest.moshi_autovalue_buffer_toJson                     thrpt  200  1659.574 ± 15.520  ops/s
SpeedTest.moshi_autovalue_string_fromJson                   thrpt  200   884.915 ±  8.067  ops/s
SpeedTest.moshi_autovalue_string_fromJson_minified          thrpt  200  1187.466 ±  8.148  ops/s
SpeedTest.moshi_autovalue_string_toJson                     thrpt  200  1486.164 ±  7.188  ops/s
SpeedTest.moshi_kotlin_codegen_buffer_fromJson              thrpt  200  1008.205 ±  5.708  ops/s
SpeedTest.moshi_kotlin_codegen_buffer_fromJson_minified     thrpt  200  1345.220 ±  3.571  ops/s
SpeedTest.moshi_kotlin_codegen_buffer_toJson                thrpt  200  1699.049 ±  9.304  ops/s
SpeedTest.moshi_kotlin_codegen_string_fromJson              thrpt  200   894.285 ±  4.104  ops/s
SpeedTest.moshi_kotlin_codegen_string_fromJson_minified     thrpt  200  1198.403 ±  5.119  ops/s
SpeedTest.moshi_kotlin_codegen_string_toJson                thrpt  200  1484.823 ±  3.913  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_fromJson           thrpt  200   891.004 ±  3.154  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_fromJson_minified  thrpt  200  1134.176 ±  3.934  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_toJson             thrpt  200  1335.505 ± 18.700  ops/s
SpeedTest.moshi_kotlin_reflective_string_fromJson           thrpt  200   784.143 ±  5.367  ops/s
SpeedTest.moshi_kotlin_reflective_string_toJson             thrpt  200  1206.002 ±  5.596  ops/s
SpeedTest.moshi_reflective_string_fromJson                  thrpt  200   853.909 ±  4.197  ops/s
SpeedTest.moshi_reflective_string_toJson                    thrpt  200  1383.914 ±  9.629  ops/s
```

Grouped by serialization type (read, write, buffered, string):

```
Benchmark                                                    Mode  Cnt     Score    Error  Units

// Reading from a JSON buffered input
SpeedTest.gson_autovalue_buffer_fromJson                    thrpt  200  1305.687 ±  4.756  ops/s
SpeedTest.gson_autovalue_buffer_fromJson_minified           thrpt  200  1500.076 ±  3.238  ops/s
SpeedTest.moshi_autovalue_buffer_fromJson                   thrpt  200  1019.747 ±  4.865  ops/s
SpeedTest.moshi_autovalue_buffer_fromJson_minified          thrpt  200  1374.263 ±  4.125  ops/s
SpeedTest.moshi_kotlin_codegen_buffer_fromJson              thrpt  200  1008.205 ±  5.708  ops/s
SpeedTest.moshi_kotlin_codegen_buffer_fromJson_minified     thrpt  200  1345.220 ±  3.571  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_fromJson           thrpt  200   891.004 ±  3.154  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_fromJson_minified  thrpt  200  1134.176 ±  3.934  ops/s

// Reading from JSON String
SpeedTest.gson_autovalue_string_fromJson                    thrpt  200  1501.642 ± 16.501  ops/s
SpeedTest.gson_autovalue_string_fromJson_minified           thrpt  200  1610.007 ± 23.511  ops/s
SpeedTest.gson_reflective_string_fromJson                   thrpt  200  1429.620 ±  8.491  ops/s
SpeedTest.kserializer_string_fromJson                       thrpt  200   933.622 ± 58.509  ops/s
SpeedTest.kserializer_string_fromJson_minified              thrpt  200  1116.711 ± 68.464  ops/s
SpeedTest.moshi_autovalue_string_fromJson                   thrpt  200   884.915 ±  8.067  ops/s
SpeedTest.moshi_autovalue_string_fromJson_minified          thrpt  200  1187.466 ±  8.148  ops/s
SpeedTest.moshi_kotlin_codegen_string_fromJson              thrpt  200   894.285 ±  4.104  ops/s
SpeedTest.moshi_kotlin_codegen_string_fromJson_minified     thrpt  200  1198.403 ±  5.119  ops/s
SpeedTest.moshi_kotlin_reflective_string_fromJson           thrpt  200   784.143 ±  5.367  ops/s
SpeedTest.moshi_reflective_string_fromJson                  thrpt  200   853.909 ±  4.197  ops/s

// Writing JSON to a buffered output
SpeedTest.gson_autovalue_buffer_toJson                      thrpt  200   616.999 ±  1.365  ops/s
SpeedTest.moshi_autovalue_buffer_toJson                     thrpt  200  1659.574 ± 15.520  ops/s
SpeedTest.moshi_kotlin_codegen_buffer_toJson                thrpt  200  1699.049 ±  9.304  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_toJson             thrpt  200  1335.505 ± 18.700  ops/s

// Writing out a JSON String
SpeedTest.gson_autovalue_string_toJson                      thrpt  200  1221.374 ±  2.972  ops/s
SpeedTest.gson_reflective_string_toJson                     thrpt  200   977.967 ±  4.039  ops/s
SpeedTest.kserializer_string_toJson                         thrpt  200  1197.405 ± 13.311  ops/s
SpeedTest.moshi_autovalue_string_toJson                     thrpt  200  1486.164 ±  7.188  ops/s
SpeedTest.moshi_kotlin_codegen_string_toJson                thrpt  200  1484.823 ±  3.913  ops/s
SpeedTest.moshi_kotlin_reflective_string_toJson             thrpt  200  1206.002 ±  5.596  ops/s
SpeedTest.moshi_reflective_string_toJson                    thrpt  200  1383.914 ±  9.629  ops/s

// Kryo for alternative comparison
SpeedTest.kryo_fromBytes                                    thrpt  200  1337.743 ±  3.532  ops/s
SpeedTest.kryo_toBytes                                      thrpt  200   951.468 ±  1.999  ops/s
```

Grouped by library (interesting to see how reflection vs custom adapters affects perf within a library):

```
Benchmark                                                    Mode  Cnt     Score    Error  Units

// GSON
SpeedTest.gson_autovalue_buffer_fromJson                    thrpt  200  1305.687 ±  4.756  ops/s
SpeedTest.gson_autovalue_buffer_fromJson_minified           thrpt  200  1500.076 ±  3.238  ops/s
SpeedTest.gson_autovalue_buffer_toJson                      thrpt  200   616.999 ±  1.365  ops/s
SpeedTest.gson_autovalue_string_fromJson                    thrpt  200  1501.642 ± 16.501  ops/s
SpeedTest.gson_autovalue_string_fromJson_minified           thrpt  200  1610.007 ± 23.511  ops/s
SpeedTest.gson_autovalue_string_toJson                      thrpt  200  1221.374 ±  2.972  ops/s
SpeedTest.gson_reflective_string_fromJson                   thrpt  200  1429.620 ±  8.491  ops/s
SpeedTest.gson_reflective_string_toJson                     thrpt  200   977.967 ±  4.039  ops/s

// Kryo
SpeedTest.kryo_fromBytes                                    thrpt  200  1337.743 ±  3.532  ops/s
SpeedTest.kryo_toBytes                                      thrpt  200   951.468 ±  1.999  ops/s

// Kotlinx Serialization
SpeedTest.kserializer_string_fromJson                       thrpt  200   933.622 ± 58.509  ops/s
SpeedTest.kserializer_string_fromJson_minified              thrpt  200  1116.711 ± 68.464  ops/s
SpeedTest.kserializer_string_toJson                         thrpt  200  1197.405 ± 13.311  ops/s

// Moshi
SpeedTest.moshi_autovalue_buffer_fromJson                   thrpt  200  1019.747 ±  4.865  ops/s
SpeedTest.moshi_autovalue_buffer_fromJson_minified          thrpt  200  1374.263 ±  4.125  ops/s
SpeedTest.moshi_autovalue_buffer_toJson                     thrpt  200  1659.574 ± 15.520  ops/s
SpeedTest.moshi_autovalue_string_fromJson                   thrpt  200   884.915 ±  8.067  ops/s
SpeedTest.moshi_autovalue_string_fromJson_minified          thrpt  200  1187.466 ±  8.148  ops/s
SpeedTest.moshi_autovalue_string_toJson                     thrpt  200  1486.164 ±  7.188  ops/s
SpeedTest.moshi_reflective_string_fromJson                  thrpt  200   853.909 ±  4.197  ops/s
SpeedTest.moshi_reflective_string_toJson                    thrpt  200  1383.914 ±  9.629  ops/s

// Moshi Kotlin
SpeedTest.moshi_kotlin_codegen_buffer_fromJson              thrpt  200  1008.205 ±  5.708  ops/s
SpeedTest.moshi_kotlin_codegen_buffer_fromJson_minified     thrpt  200  1345.220 ±  3.571  ops/s
SpeedTest.moshi_kotlin_codegen_buffer_toJson                thrpt  200  1699.049 ±  9.304  ops/s
SpeedTest.moshi_kotlin_codegen_string_fromJson              thrpt  200   894.285 ±  4.104  ops/s
SpeedTest.moshi_kotlin_codegen_string_fromJson_minified     thrpt  200  1198.403 ±  5.119  ops/s
SpeedTest.moshi_kotlin_codegen_string_toJson                thrpt  200  1484.823 ±  3.913  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_fromJson           thrpt  200   891.004 ±  3.154  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_fromJson_minified  thrpt  200  1134.176 ±  3.934  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_toJson             thrpt  200  1335.505 ± 18.700  ops/s
SpeedTest.moshi_kotlin_reflective_string_fromJson           thrpt  200   784.143 ±  5.367  ops/s
SpeedTest.moshi_kotlin_reflective_string_toJson             thrpt  200  1206.002 ±  5.596  ops/s
```

Grouped by read/write and ordered by raw throughput:

```
Benchmark                                                    Mode  Cnt     Score    Error  Units

// Read (buffered)
SpeedTest.gson_autovalue_buffer_fromJson_minified           thrpt  200  1500.076 ±  3.238  ops/s
SpeedTest.moshi_autovalue_buffer_fromJson_minified          thrpt  200  1374.263 ±  4.125  ops/s
SpeedTest.moshi_kotlin_codegen_buffer_fromJson_minified     thrpt  200  1345.220 ±  3.571  ops/s
SpeedTest.gson_autovalue_buffer_fromJson                    thrpt  200  1305.687 ±  4.756  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_fromJson_minified  thrpt  200  1134.176 ±  3.934  ops/s
SpeedTest.moshi_autovalue_buffer_fromJson                   thrpt  200  1019.747 ±  4.865  ops/s
SpeedTest.moshi_kotlin_codegen_buffer_fromJson              thrpt  200  1008.205 ±  5.708  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_fromJson           thrpt  200   891.004 ±  3.154  ops/s

// Read (string)
SpeedTest.gson_autovalue_string_fromJson_minified           thrpt  200  1610.007 ± 23.511  ops/s
SpeedTest.gson_autovalue_string_fromJson                    thrpt  200  1501.642 ± 16.501  ops/s
SpeedTest.gson_reflective_string_fromJson                   thrpt  200  1429.620 ±  8.491  ops/s
SpeedTest.moshi_autovalue_string_fromJson_minified          thrpt  200  1187.466 ±  8.148  ops/s
SpeedTest.kserializer_string_fromJson_minified              thrpt  200  1116.711 ± 68.464  ops/s
SpeedTest.moshi_kotlin_codegen_string_fromJson_minified     thrpt  200  1198.403 ±  5.119  ops/s
SpeedTest.kserializer_string_fromJson                       thrpt  200   933.622 ± 58.509  ops/s
SpeedTest.moshi_kotlin_codegen_string_fromJson              thrpt  200   894.285 ±  4.104  ops/s
SpeedTest.moshi_autovalue_string_fromJson                   thrpt  200   884.915 ±  8.067  ops/s
SpeedTest.moshi_reflective_string_fromJson                  thrpt  200   853.909 ±  4.197  ops/s
SpeedTest.moshi_kotlin_reflective_string_fromJson           thrpt  200   784.143 ±  5.367  ops/s

// Write (buffered)
SpeedTest.moshi_kotlin_codegen_buffer_toJson                thrpt  200  1699.049 ±  9.304  ops/s
SpeedTest.moshi_autovalue_buffer_toJson                     thrpt  200  1659.574 ± 15.520  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_toJson             thrpt  200  1335.505 ± 18.700  ops/s
SpeedTest.gson_autovalue_buffer_toJson                      thrpt  200   616.999 ±  1.365  ops/s

// Write (string)
SpeedTest.moshi_autovalue_string_toJson                     thrpt  200  1486.164 ±  7.188  ops/s
SpeedTest.moshi_kotlin_codegen_string_toJson                thrpt  200  1484.823 ±  3.913  ops/s
SpeedTest.moshi_reflective_string_toJson                    thrpt  200  1383.914 ±  9.629  ops/s
SpeedTest.gson_autovalue_string_toJson                      thrpt  200  1221.374 ±  2.972  ops/s
SpeedTest.moshi_kotlin_reflective_string_toJson             thrpt  200  1206.002 ±  5.596  ops/s
SpeedTest.kserializer_string_toJson                         thrpt  200  1197.405 ± 13.311  ops/s
SpeedTest.gson_reflective_string_toJson                     thrpt  200   977.967 ±  4.039  ops/s

// Kryo alternative
SpeedTest.kryo_fromBytes                                    thrpt  200  1337.743 ±  3.532  ops/s
SpeedTest.kryo_toBytes                                      thrpt  200   951.468 ±  1.999  ops/s
```

To run:

`./gradlew jmh`
