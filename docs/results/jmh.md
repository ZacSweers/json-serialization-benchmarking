## Current raw JMH results (higher score is better)

Run on a mid-2015 15" Macbook Pro. 2.8 GHz Intel Core i7, 16 GB 1600 MHz DDR3

Raw

```
Benchmark                                             (minified)   Mode  Cnt     Score    Error  Units
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
```

Grouped by serialization type (read, write, buffered, string):

```
Read (buffered)
JmhBenchmark.moshi_autovalue_buffer_fromJson[minified=true]           thrpt  25  1599.001  ±  18.123  ops/s
JmhBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=true]      thrpt  25  1589.254  ±  11.012  ops/s
JmhBenchmark.gson_autovalue_buffer_fromJson[minified=true]            thrpt  25  1525.482  ±  42.836  ops/s
JmhBenchmark.gson_autovalue_buffer_fromJson[minified=false]           thrpt  25  1409.158  ±   8.407  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=true]   thrpt  25  1371.793  ±  12.505  ops/s
JmhBenchmark.moshi_autovalue_buffer_fromJson[minified=false]          thrpt  25  1083.885  ±  25.161  ops/s
JmhBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=false]     thrpt  25  1070.076  ±  11.175  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=false]  thrpt  25   966.614  ±   13.09  ops/s

Read (string)
JmhBenchmark.gson_reflective_string_fromJson[minified=true]           thrpt  25  1886.678  ±   69.66  ops/s
JmhBenchmark.gson_autovalue_string_fromJson[minified=true]            thrpt  25  1781.907  ±  22.567  ops/s
JmhBenchmark.gson_reflective_string_fromJson[minified=false]          thrpt  25   1767.98  ±  15.606  ops/s
JmhBenchmark.gson_autovalue_string_fromJson[minified=false]           thrpt  25  1593.688  ±  48.073  ops/s
JmhBenchmark.kserializer_string_fromJson[minified=true]               thrpt  25  1560.665  ±  21.197  ops/s
JmhBenchmark.kserializer_string_fromJson[minified=false]              thrpt  25   1431.96  ±  22.652  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_fromJson[minified=true]      thrpt  25  1413.896  ±  26.525  ops/s
JmhBenchmark.moshi_autovalue_string_fromJson[minified=true]           thrpt  25   1403.61  ±  33.427  ops/s
JmhBenchmark.moshi_reflective_string_fromJson[minified=true]          thrpt  25  1373.449  ±   5.726  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_fromJson[minified=true]   thrpt  25  1250.486  ±  17.789  ops/s
JmhBenchmark.moshi_autovalue_string_fromJson[minified=false]          thrpt  25   993.052  ±   30.95  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_fromJson[minified=false]     thrpt  25   960.448  ±  23.985  ops/s
JmhBenchmark.moshi_reflective_string_fromJson[minified=false]         thrpt  25   932.438  ±  16.473  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_fromJson[minified=false]  thrpt  25   874.268  ±  15.619  ops/s

Write (buffered)
JmhBenchmark.moshi_kotlin_codegen_buffer_toJson[minified=true]        thrpt  25   1857.14  ±  82.876  ops/s
JmhBenchmark.moshi_autovalue_buffer_toJson[minified=true]             thrpt  25  1765.929  ±  16.492  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_toJson[minified=true]     thrpt  25  1560.115  ±   54.81  ops/s
JmhBenchmark.gson_autovalue_buffer_toJson[minified=true]              thrpt  25   754.222  ±  15.435  ops/s

Write (string)
JmhBenchmark.moshi_kotlin_codegen_string_toJson[minified=true]        thrpt  25  1680.681  ±  70.284  ops/s
JmhBenchmark.gson_autovalue_string_toJson[minified=true]              thrpt  25  1665.459  ±  91.108  ops/s
JmhBenchmark.moshi_autovalue_string_toJson[minified=true]             thrpt  25  1502.739  ±   6.458  ops/s
JmhBenchmark.moshi_reflective_string_toJson[minified=true]            thrpt  25  1441.979  ±  11.832  ops/s
JmhBenchmark.kserializer_string_toJson[minified=true]                 thrpt  25  1387.467  ±  40.396  ops/s
JmhBenchmark.gson_reflective_string_toJson[minified=true]             thrpt  25  1321.148  ±  40.046  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_toJson[minified=true]     thrpt  25   1291.33  ±   12.34  ops/s
```

Grouped by library (interesting to see how reflection vs custom adapters affects perf within a library):

```
GSON
JmhBenchmark.gson_reflective_string_fromJson[minified=true]           thrpt  25  1886.678  ±   69.66  ops/s
JmhBenchmark.gson_autovalue_string_fromJson[minified=true]            thrpt  25  1781.907  ±  22.567  ops/s
JmhBenchmark.gson_reflective_string_fromJson[minified=false]          thrpt  25   1767.98  ±  15.606  ops/s
JmhBenchmark.gson_autovalue_string_toJson[minified=true]              thrpt  25  1665.459  ±  91.108  ops/s
JmhBenchmark.gson_autovalue_string_fromJson[minified=false]           thrpt  25  1593.688  ±  48.073  ops/s
JmhBenchmark.gson_autovalue_buffer_fromJson[minified=true]            thrpt  25  1525.482  ±  42.836  ops/s
JmhBenchmark.gson_autovalue_buffer_fromJson[minified=false]           thrpt  25  1409.158  ±   8.407  ops/s
JmhBenchmark.gson_reflective_string_toJson[minified=true]             thrpt  25  1321.148  ±  40.046  ops/s
JmhBenchmark.gson_autovalue_buffer_toJson[minified=true]              thrpt  25   754.222  ±  15.435  ops/s

Kotlinx Serialization
JmhBenchmark.kserializer_string_fromJson[minified=true]               thrpt  25  1560.665  ±  21.197  ops/s
JmhBenchmark.kserializer_string_fromJson[minified=false]              thrpt  25   1431.96  ±  22.652  ops/s
JmhBenchmark.kserializer_string_toJson[minified=true]                 thrpt  25  1387.467  ±  40.396  ops/s

Moshi
JmhBenchmark.moshi_autovalue_buffer_toJson[minified=true]             thrpt  25  1765.929  ±  16.492  ops/s
JmhBenchmark.moshi_autovalue_buffer_fromJson[minified=true]           thrpt  25  1599.001  ±  18.123  ops/s
JmhBenchmark.moshi_autovalue_string_toJson[minified=true]             thrpt  25  1502.739  ±   6.458  ops/s
JmhBenchmark.moshi_reflective_string_toJson[minified=true]            thrpt  25  1441.979  ±  11.832  ops/s
JmhBenchmark.moshi_autovalue_string_fromJson[minified=true]           thrpt  25   1403.61  ±  33.427  ops/s
JmhBenchmark.moshi_reflective_string_fromJson[minified=true]          thrpt  25  1373.449  ±   5.726  ops/s
JmhBenchmark.moshi_autovalue_buffer_fromJson[minified=false]          thrpt  25  1083.885  ±  25.161  ops/s
JmhBenchmark.moshi_autovalue_string_fromJson[minified=false]          thrpt  25   993.052  ±   30.95  ops/s
JmhBenchmark.moshi_reflective_string_fromJson[minified=false]         thrpt  25   932.438  ±  16.473  ops/s

Moshi Kotlin
JmhBenchmark.moshi_kotlin_codegen_buffer_toJson[minified=true]        thrpt  25   1857.14  ±  82.876  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_toJson[minified=true]        thrpt  25  1680.681  ±  70.284  ops/s
JmhBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=true]      thrpt  25  1589.254  ±  11.012  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_toJson[minified=true]     thrpt  25  1560.115  ±   54.81  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_fromJson[minified=true]      thrpt  25  1413.896  ±  26.525  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=true]   thrpt  25  1371.793  ±  12.505  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_toJson[minified=true]     thrpt  25   1291.33  ±   12.34  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_fromJson[minified=true]   thrpt  25  1250.486  ±  17.789  ops/s
JmhBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=false]     thrpt  25  1070.076  ±  11.175  ops/s
JmhBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=false]  thrpt  25   966.614  ±   13.09  ops/s
JmhBenchmark.moshi_kotlin_codegen_string_fromJson[minified=false]     thrpt  25   960.448  ±  23.985  ops/s
JmhBenchmark.moshi_kotlin_reflective_string_fromJson[minified=false]  thrpt  25   874.268  ±  15.619  ops/s
```