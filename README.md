Misc benchmarks for json serialization

Notes:
- This test...
  - Compares gson and moshi
  - Reflective vs streaming APIs
  - Moshi's kotlin support (reflective or code gen for streaming APIs)
  - Also kotlinx serialization and Kryo for added comparisons
    - Note these only work via reflection (kryo) or the standard compiler plugin (kotlin)
- AutoValue APIs are done via AutoValue models with the auto-value-gson/moshi extensions, thus using generated adapters.
- Kotlin codegen generates kotlin JsonAdapters.
- Reflection is done usually on a simple class due to limitations of instantiation. Moshi's reflection library is the exception (it can handle `data class` types)
- A custom snapshot of Okio with a more performant trie-based implementation for the select API

Current results (higher score is better):

(Run on a mid-2015 15" Macbook Pro. 2.8 GHz Intel Core i7, 16 GB 1600 MHz DDR3)

```
Benchmark                                           Mode  Cnt     Score    Error  Units
SpeedTest.gson_autovalue_buffer_fromJson           thrpt  200  1305.751 ±  3.250  ops/s
SpeedTest.gson_autovalue_buffer_toJson             thrpt  200   636.358 ±  6.160  ops/s
SpeedTest.gson_autovalue_string_fromJson           thrpt  200  1435.158 ± 18.064  ops/s
SpeedTest.gson_autovalue_string_toJson             thrpt  200  1197.856 ±  2.830  ops/s
SpeedTest.gson_reflective_string_fromJson          thrpt  200  1385.616 ± 14.821  ops/s
SpeedTest.gson_reflective_string_toJson            thrpt  200   957.015 ±  4.439  ops/s
SpeedTest.kryo_fromBytes                           thrpt  200  1315.361 ±  3.594  ops/s
SpeedTest.kryo_toBytes                             thrpt  200   940.597 ±  2.033  ops/s
SpeedTest.kserializer_string_fromJson              thrpt  200  1107.929 ± 51.456  ops/s
SpeedTest.kserializer_string_toJson                thrpt  200  1175.760 ± 17.874  ops/s
SpeedTest.moshi_autovalue_buffer_fromJson          thrpt  200  1007.122 ±  3.232  ops/s
SpeedTest.moshi_autovalue_buffer_toJson            thrpt  200  1706.448 ± 17.938  ops/s
SpeedTest.moshi_autovalue_string_fromJson          thrpt  200   887.876 ±  6.539  ops/s
SpeedTest.moshi_autovalue_string_toJson            thrpt  200  1478.233 ±  2.913  ops/s
SpeedTest.moshi_kotlin_codegen_buffer_fromJson     thrpt  200   991.785 ±  5.820  ops/s
SpeedTest.moshi_kotlin_codegen_buffer_toJson       thrpt  200  1651.748 ± 18.600  ops/s
SpeedTest.moshi_kotlin_codegen_string_fromJson     thrpt  200   869.635 ±  6.311  ops/s
SpeedTest.moshi_kotlin_codegen_string_toJson       thrpt  200  1460.091 ±  3.726  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_fromJson  thrpt  200   889.625 ±  2.226  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_toJson    thrpt  200  1318.989 ± 16.083  ops/s
SpeedTest.moshi_kotlin_reflective_string_fromJson  thrpt  200   785.574 ±  5.834  ops/s
SpeedTest.moshi_kotlin_reflective_string_toJson    thrpt  200  1207.557 ±  8.226  ops/s
SpeedTest.moshi_reflective_string_fromJson         thrpt  200   854.247 ±  6.300  ops/s
SpeedTest.moshi_reflective_string_toJson           thrpt  200  1380.440 ± 10.762  ops/s
```

Grouped by serialization type (read, write, buffered, string):

```
Benchmark                                           Mode  Cnt     Score    Error  Units

// Reading from a JSON buffered input
SpeedTest.gson_autovalue_buffer_fromJson           thrpt  200  1305.751 ±  3.250  ops/s
SpeedTest.moshi_autovalue_buffer_fromJson          thrpt  200  1007.122 ±  3.232  ops/s
SpeedTest.moshi_kotlin_codegen_buffer_fromJson     thrpt  200   991.785 ±  5.820  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_fromJson  thrpt  200   889.625 ±  2.226  ops/s

// Writing JSON to a buffered output
SpeedTest.gson_autovalue_buffer_toJson             thrpt  200   636.358 ±  6.160  ops/s
SpeedTest.moshi_autovalue_buffer_toJson            thrpt  200  1706.448 ± 17.938  ops/s
SpeedTest.moshi_kotlin_codegen_buffer_toJson       thrpt  200  1651.748 ± 18.600  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_toJson    thrpt  200  1318.989 ± 16.083  ops/s

// Reading from JSON String
SpeedTest.gson_autovalue_string_fromJson           thrpt  200  1435.158 ± 18.064  ops/s
SpeedTest.gson_reflective_string_fromJson          thrpt  200  1385.616 ± 14.821  ops/s
SpeedTest.kryo_fromBytes                           thrpt  200  1315.361 ±  3.594  ops/s
SpeedTest.kserializer_string_fromJson              thrpt  200  1107.929 ± 51.456  ops/s
SpeedTest.moshi_autovalue_string_fromJson          thrpt  200   887.876 ±  6.539  ops/s
SpeedTest.moshi_kotlin_codegen_string_fromJson     thrpt  200   869.635 ±  6.311  ops/s
SpeedTest.moshi_kotlin_reflective_string_fromJson  thrpt  200   785.574 ±  5.834  ops/s
SpeedTest.moshi_reflective_string_fromJson         thrpt  200   854.247 ±  6.300  ops/s

// Writing out a JSON String
SpeedTest.gson_autovalue_string_toJson             thrpt  200  1197.856 ±  2.830  ops/s
SpeedTest.gson_reflective_string_toJson            thrpt  200   957.015 ±  4.439  ops/s
SpeedTest.kryo_toBytes                             thrpt  200   940.597 ±  2.033  ops/s
SpeedTest.kserializer_string_toJson                thrpt  200  1175.760 ± 17.874  ops/s
SpeedTest.moshi_autovalue_string_toJson            thrpt  200  1478.233 ±  2.913  ops/s
SpeedTest.moshi_kotlin_codegen_string_toJson       thrpt  200  1460.091 ±  3.726  ops/s
SpeedTest.moshi_kotlin_reflective_string_toJson    thrpt  200  1207.557 ±  8.226  ops/s
SpeedTest.moshi_reflective_string_toJson           thrpt  200  1380.440 ± 10.762  ops/s
```

Grouped by library (interesting to see how reflection vs custom adapters affects perf within a library):

```
Benchmark                                           Mode  Cnt     Score    Error  Units

// Gson
SpeedTest.gson_autovalue_buffer_fromJson           thrpt  200  1305.751 ±  3.250  ops/s
SpeedTest.gson_autovalue_buffer_toJson             thrpt  200   636.358 ±  6.160  ops/s
SpeedTest.gson_autovalue_string_fromJson           thrpt  200  1435.158 ± 18.064  ops/s
SpeedTest.gson_reflective_string_fromJson          thrpt  200  1385.616 ± 14.821  ops/s
SpeedTest.gson_autovalue_string_toJson             thrpt  200  1197.856 ±  2.830  ops/s
SpeedTest.gson_reflective_string_toJson            thrpt  200   957.015 ±  4.439  ops/s

// Moshi
SpeedTest.moshi_autovalue_buffer_fromJson          thrpt  200  1007.122 ±  3.232  ops/s
SpeedTest.moshi_autovalue_buffer_toJson            thrpt  200  1706.448 ± 17.938  ops/s
SpeedTest.moshi_autovalue_string_fromJson          thrpt  200   887.876 ±  6.539  ops/s
SpeedTest.moshi_reflective_string_fromJson         thrpt  200   854.247 ±  6.300  ops/s
SpeedTest.moshi_autovalue_string_toJson            thrpt  200  1478.233 ±  2.913  ops/s
SpeedTest.moshi_reflective_string_toJson           thrpt  200  1380.440 ± 10.762  ops/s

// Moshi Kotlin
SpeedTest.moshi_kotlin_codegen_buffer_fromJson     thrpt  200   991.785 ±  5.820  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_fromJson  thrpt  200   889.625 ±  2.226  ops/s
SpeedTest.moshi_kotlin_codegen_buffer_toJson       thrpt  200  1651.748 ± 18.600  ops/s
SpeedTest.moshi_kotlin_reflective_buffer_toJson    thrpt  200  1318.989 ± 16.083  ops/s
SpeedTest.moshi_kotlin_codegen_string_fromJson     thrpt  200   869.635 ±  6.311  ops/s
SpeedTest.moshi_kotlin_reflective_string_fromJson  thrpt  200   785.574 ±  5.834  ops/s
SpeedTest.moshi_kotlin_codegen_string_toJson       thrpt  200  1460.091 ±  3.726  ops/s
SpeedTest.moshi_kotlin_reflective_string_toJson    thrpt  200  1207.557 ±  8.226  ops/s

// Misc
SpeedTest.kryo_fromBytes                           thrpt  200  1315.361 ±  3.594  ops/s
SpeedTest.kserializer_string_fromJson              thrpt  200  1107.929 ± 51.456  ops/s
SpeedTest.kryo_toBytes                             thrpt  200   940.597 ±  2.033  ops/s
SpeedTest.kserializer_string_toJson                thrpt  200  1175.760 ± 17.874  ops/s
```

To run:

`./gradlew jmh`
