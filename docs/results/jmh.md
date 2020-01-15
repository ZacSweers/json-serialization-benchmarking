## Current raw JMH results (higher score is better)

Run on a mid-2015 15" Macbook Pro. 2.8 GHz Intel Core i7, 16 GB 1600 MHz DDR3

Raw

```
Benchmark                                                      (minified)   Mode  Cnt     Score    Error  Units
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
```

Grouped by serialization type (read, write, buffered, string):

```
Read (buffered)
JmhBenchmark.gson_autovalue_buffer_fromJson[minified=true]                     thrpt  25  1694.589  ±  16.038  ops/s
JmhBenchmark.moshi_autovalue_buffer_fromJson[minified=true]                    thrpt  25  1548.544  ±  35.862  ops/s
JmhBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=true]               thrpt  25  1528.458  ±   31.33  ops/s
JmhBenchmark.gson_autovalue_buffer_fromJson[minified=false]                    thrpt  25  1429.873  ±  53.567  ops/s
JmhBenchmark.moshi_kotlin_reflective_metadata_buffer_fromJson[minified=true]   thrpt  25  1386.728  ±  15.615  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=true]            thrpt  25  1317.167  ±  11.294  ops/s
JmhBenchmark.moshi_autovalue_buffer_fromJson[minified=false]                   thrpt  25  1069.282  ±  11.627  ops/s
JmhBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=false]              thrpt  25  1047.044  ±  15.846  ops/s
JmhBenchmark.moshi_kotlin_reflective_metadata_buffer_fromJson[minified=false]  thrpt  25   976.127  ±   4.783  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=false]           thrpt  25   946.928  ±    3.91  ops/s

Read (string)
JmhBenchmark.gson_autovalue_string_fromJson[minified=true]                     thrpt  25  1978.363  ±  23.024  ops/s
JmhBenchmark.gson_reflective_string_fromJson[minified=true]                    thrpt  25  1854.543  ±  65.485  ops/s
JmhBenchmark.gson_autovalue_string_fromJson[minified=false]                    thrpt  25  1697.228  ±  54.541  ops/s
JmhBenchmark.gson_reflective_string_fromJson[minified=false]                   thrpt  25  1631.023  ±  91.259  ops/s
JmhBenchmark.kserializer_string_fromJson[minified=true]                        thrpt  25  1516.173  ±  16.853  ops/s
JmhBenchmark.kserializer_string_fromJson[minified=false]                       thrpt  25  1431.558  ±  16.761  ops/s
JmhBenchmark.moshi_autovalue_string_fromJson[minified=true]                    thrpt  25  1347.846  ±  28.476  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_fromJson[minified=true]               thrpt  25  1337.028  ±  31.004  ops/s
JmhBenchmark.moshi_reflective_string_fromJson[minified=true]                   thrpt  25  1316.507  ±   34.06  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_fromJson[minified=true]            thrpt  25  1215.453  ±  20.914  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_fromJson[minified=false]              thrpt  25   949.187  ±  25.544  ops/s
JmhBenchmark.moshi_autovalue_string_fromJson[minified=false]                   thrpt  25   944.124  ±  22.116  ops/s
JmhBenchmark.moshi_reflective_string_fromJson[minified=false]                  thrpt  25   929.292  ±  22.495  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_fromJson[minified=false]           thrpt  25    875.29  ±   6.342  ops/s

Write (buffered)
JmhBenchmark.moshi_kotlin_codegen_buffer_toJson[minified=true]                 thrpt  25  1749.996  ±  69.528  ops/s
JmhBenchmark.moshi_autovalue_buffer_toJson[minified=true]                      thrpt  25  1725.926  ±  12.618  ops/s
JmhBenchmark.moshi_kotlin_reflective_metadata_buffer_toJson[minified=true]     thrpt  25  1592.001  ±  81.603  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_toJson[minified=true]              thrpt  25  1478.326  ±    17.0  ops/s
JmhBenchmark.gson_autovalue_buffer_toJson[minified=true]                       thrpt  25   770.072  ±   6.531  ops/s

Write (string)
JmhBenchmark.gson_autovalue_string_toJson[minified=true]                       thrpt  25  1651.928  ±  55.944  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_toJson[minified=true]                 thrpt  25    1493.3  ±  61.486  ops/s
JmhBenchmark.moshi_autovalue_string_toJson[minified=true]                      thrpt  25  1438.208  ±  16.157  ops/s
JmhBenchmark.moshi_reflective_string_toJson[minified=true]                     thrpt  25  1379.795  ±  19.318  ops/s
JmhBenchmark.kserializer_string_toJson[minified=true]                          thrpt  25  1312.221  ±   53.84  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_toJson[minified=true]              thrpt  25  1281.294  ±   8.931  ops/s
JmhBenchmark.gson_reflective_string_toJson[minified=true]                      thrpt  25  1256.877  ±  51.406  ops/s
```

Grouped by library (interesting to see how reflection vs custom adapters affects perf within a library):

```
GSON
JmhBenchmark.gson_autovalue_string_fromJson[minified=true]                     thrpt  25  1978.363  ±  23.024  ops/s
JmhBenchmark.gson_reflective_string_fromJson[minified=true]                    thrpt  25  1854.543  ±  65.485  ops/s
JmhBenchmark.gson_autovalue_string_fromJson[minified=false]                    thrpt  25  1697.228  ±  54.541  ops/s
JmhBenchmark.gson_autovalue_buffer_fromJson[minified=true]                     thrpt  25  1694.589  ±  16.038  ops/s
JmhBenchmark.gson_autovalue_string_toJson[minified=true]                       thrpt  25  1651.928  ±  55.944  ops/s
JmhBenchmark.gson_reflective_string_fromJson[minified=false]                   thrpt  25  1631.023  ±  91.259  ops/s
JmhBenchmark.gson_autovalue_buffer_fromJson[minified=false]                    thrpt  25  1429.873  ±  53.567  ops/s
JmhBenchmark.gson_reflective_string_toJson[minified=true]                      thrpt  25  1256.877  ±  51.406  ops/s
JmhBenchmark.gson_autovalue_buffer_toJson[minified=true]                       thrpt  25   770.072  ±   6.531  ops/s

Kotlinx Serialization
JmhBenchmark.kserializer_string_fromJson[minified=true]                        thrpt  25  1516.173  ±  16.853  ops/s
JmhBenchmark.kserializer_string_fromJson[minified=false]                       thrpt  25  1431.558  ±  16.761  ops/s
JmhBenchmark.kserializer_string_toJson[minified=true]                          thrpt  25  1312.221  ±   53.84  ops/s

Moshi
JmhBenchmark.moshi_autovalue_buffer_toJson[minified=true]                      thrpt  25  1725.926  ±  12.618  ops/s
JmhBenchmark.moshi_autovalue_buffer_fromJson[minified=true]                    thrpt  25  1548.544  ±  35.862  ops/s
JmhBenchmark.moshi_autovalue_string_toJson[minified=true]                      thrpt  25  1438.208  ±  16.157  ops/s
JmhBenchmark.moshi_reflective_string_toJson[minified=true]                     thrpt  25  1379.795  ±  19.318  ops/s
JmhBenchmark.moshi_autovalue_string_fromJson[minified=true]                    thrpt  25  1347.846  ±  28.476  ops/s
JmhBenchmark.moshi_reflective_string_fromJson[minified=true]                   thrpt  25  1316.507  ±   34.06  ops/s
JmhBenchmark.moshi_autovalue_buffer_fromJson[minified=false]                   thrpt  25  1069.282  ±  11.627  ops/s
JmhBenchmark.moshi_autovalue_string_fromJson[minified=false]                   thrpt  25   944.124  ±  22.116  ops/s
JmhBenchmark.moshi_reflective_string_fromJson[minified=false]                  thrpt  25   929.292  ±  22.495  ops/s

Moshi Kotlin
JmhBenchmark.moshi_kotlin_codegen_buffer_toJson[minified=true]                 thrpt  25  1749.996  ±  69.528  ops/s
JmhBenchmark.moshi_kotlin_reflective_metadata_buffer_toJson[minified=true]     thrpt  25  1592.001  ±  81.603  ops/s
JmhBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=true]               thrpt  25  1528.458  ±   31.33  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_toJson[minified=true]                 thrpt  25    1493.3  ±  61.486  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_toJson[minified=true]              thrpt  25  1478.326  ±    17.0  ops/s
JmhBenchmark.moshi_kotlin_reflective_metadata_buffer_fromJson[minified=true]   thrpt  25  1386.728  ±  15.615  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_fromJson[minified=true]               thrpt  25  1337.028  ±  31.004  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=true]            thrpt  25  1317.167  ±  11.294  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_toJson[minified=true]              thrpt  25  1281.294  ±   8.931  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_fromJson[minified=true]            thrpt  25  1215.453  ±  20.914  ops/s
JmhBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=false]              thrpt  25  1047.044  ±  15.846  ops/s
JmhBenchmark.moshi_kotlin_reflective_metadata_buffer_fromJson[minified=false]  thrpt  25   976.127  ±   4.783  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_fromJson[minified=false]              thrpt  25   949.187  ±  25.544  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=false]           thrpt  25   946.928  ±    3.91  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_fromJson[minified=false]           thrpt  25    875.29  ±   6.342  ops/s
```