## Current raw Android polymorphic benchmark results (lower score is better)

_Run on a 2018 Pixel 3_

Grouped by serialization type (read, write, buffered, string):

```
Read (buffered)
AndroidBenchmark.gson_autovalue_buffer_fromJson[minified=true]                       7,153,439  ns
AndroidBenchmark.moshi_autovalue_buffer_fromJson[minified=true]                      7,442,240  ns
AndroidBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=true]                 7,553,282  ns
AndroidBenchmark.gson_autovalue_buffer_fromJson[minified=false]                      8,308,906  ns
AndroidBenchmark.moshi_kotlin_reflective_metadata_buffer_fromJson[minified=true]     9,167,501  ns
AndroidBenchmark.moshi_autovalue_buffer_fromJson[minified=false]                    10,000,887  ns
AndroidBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=false]               10,039,950  ns
AndroidBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=true]             10,454,168  ns
AndroidBenchmark.moshi_kotlin_reflective_metadata_buffer_fromJson[minified=false]   11,691,721  ns
AndroidBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=false]            12,930,158  ns

Read (string)
AndroidBenchmark.gson_autovalue_string_fromJson[minified=true]                       5,490,782  ns
AndroidBenchmark.gson_autovalue_string_fromJson[minified=false]                      6,160,991  ns
AndroidBenchmark.gson_reflective_string_fromJson[minified=true]                      7,395,001  ns
AndroidBenchmark.kserializer_string_fromJson[minified=true]                          7,672,917  ns
AndroidBenchmark.gson_reflective_string_fromJson[minified=false]                     8,080,002  ns
AndroidBenchmark.kserializer_string_fromJson[minified=false]                         8,395,470  ns
AndroidBenchmark.moshi_autovalue_string_fromJson[minified=true]                      8,697,760  ns
AndroidBenchmark.moshi_kotlin_codegen_string_fromJson[minified=true]                 8,746,668  ns
AndroidBenchmark.moshi_reflective_string_fromJson[minified=true]                    10,757,136  ns
AndroidBenchmark.moshi_autovalue_string_fromJson[minified=false]                    11,692,918  ns
AndroidBenchmark.moshi_kotlin_codegen_string_fromJson[minified=false]               11,716,408  ns
AndroidBenchmark.moshi_kotlin_reflective_string_fromJson[minified=true]             11,734,532  ns
AndroidBenchmark.moshi_reflective_string_fromJson[minified=false]                   13,728,440  ns
AndroidBenchmark.moshi_kotlin_reflective_string_fromJson[minified=false]            14,747,397  ns

Write (buffered)
AndroidBenchmark.moshi_autovalue_buffer_toJson[minified=true]                        4,938,021  ns
AndroidBenchmark.moshi_kotlin_codegen_buffer_toJson[minified=true]                   5,052,032  ns
AndroidBenchmark.moshi_kotlin_reflective_metadata_buffer_toJson[minified=true]       8,096,823  ns
AndroidBenchmark.moshi_kotlin_reflective_buffer_toJson[minified=true]                9,461,355  ns
AndroidBenchmark.gson_autovalue_buffer_toJson[minified=true]                       205,073,510  ns

Write (string)
AndroidBenchmark.kserializer_string_toJson[minified=true]                            7,024,272  ns
AndroidBenchmark.moshi_kotlin_codegen_string_toJson[minified=true]                   7,072,449  ns
AndroidBenchmark.moshi_autovalue_string_toJson[minified=true]                        7,117,866  ns
AndroidBenchmark.gson_autovalue_string_toJson[minified=true]                         7,686,303  ns
AndroidBenchmark.moshi_reflective_string_toJson[minified=true]                       8,185,157  ns
AndroidBenchmark.gson_reflective_string_toJson[minified=true]                       10,190,886  ns
AndroidBenchmark.moshi_kotlin_reflective_string_toJson[minified=true]               11,308,542  ns
```

Grouped by library (interesting to see how reflection vs custom adapters affects perf within a library):

```
GSON
AndroidBenchmark.gson_autovalue_string_fromJson[minified=true]                       5,490,782  ns
AndroidBenchmark.gson_autovalue_string_fromJson[minified=false]                      6,160,991  ns
AndroidBenchmark.gson_autovalue_buffer_fromJson[minified=true]                       7,153,439  ns
AndroidBenchmark.gson_reflective_string_fromJson[minified=true]                      7,395,001  ns
AndroidBenchmark.gson_autovalue_string_toJson[minified=true]                         7,686,303  ns
AndroidBenchmark.gson_reflective_string_fromJson[minified=false]                     8,080,002  ns
AndroidBenchmark.gson_autovalue_buffer_fromJson[minified=false]                      8,308,906  ns
AndroidBenchmark.gson_reflective_string_toJson[minified=true]                       10,190,886  ns
AndroidBenchmark.gson_autovalue_buffer_toJson[minified=true]                       205,073,510  ns

Kotlinx Serialization
AndroidBenchmark.kserializer_string_toJson[minified=true]                            7,024,272  ns
AndroidBenchmark.kserializer_string_fromJson[minified=true]                          7,672,917  ns
AndroidBenchmark.kserializer_string_fromJson[minified=false]                         8,395,470  ns

Moshi
AndroidBenchmark.moshi_autovalue_buffer_toJson[minified=true]                        4,938,021  ns
AndroidBenchmark.moshi_autovalue_string_toJson[minified=true]                        7,117,866  ns
AndroidBenchmark.moshi_autovalue_buffer_fromJson[minified=true]                      7,442,240  ns
AndroidBenchmark.moshi_reflective_string_toJson[minified=true]                       8,185,157  ns
AndroidBenchmark.moshi_autovalue_string_fromJson[minified=true]                      8,697,760  ns
AndroidBenchmark.moshi_autovalue_buffer_fromJson[minified=false]                    10,000,887  ns
AndroidBenchmark.moshi_reflective_string_fromJson[minified=true]                    10,757,136  ns
AndroidBenchmark.moshi_autovalue_string_fromJson[minified=false]                    11,692,918  ns
AndroidBenchmark.moshi_reflective_string_fromJson[minified=false]                   13,728,440  ns

Moshi Kotlin
AndroidBenchmark.moshi_kotlin_codegen_buffer_toJson[minified=true]                   5,052,032  ns
AndroidBenchmark.moshi_kotlin_codegen_string_toJson[minified=true]                   7,072,449  ns
AndroidBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=true]                 7,553,282  ns
AndroidBenchmark.moshi_kotlin_reflective_metadata_buffer_toJson[minified=true]       8,096,823  ns
AndroidBenchmark.moshi_kotlin_codegen_string_fromJson[minified=true]                 8,746,668  ns
AndroidBenchmark.moshi_kotlin_reflective_metadata_buffer_fromJson[minified=true]     9,167,501  ns
AndroidBenchmark.moshi_kotlin_reflective_buffer_toJson[minified=true]                9,461,355  ns
AndroidBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=false]               10,039,950  ns
AndroidBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=true]             10,454,168  ns
AndroidBenchmark.moshi_kotlin_reflective_string_toJson[minified=true]               11,308,542  ns
AndroidBenchmark.moshi_kotlin_reflective_metadata_buffer_fromJson[minified=false]   11,691,721  ns
AndroidBenchmark.moshi_kotlin_codegen_string_fromJson[minified=false]               11,716,408  ns
AndroidBenchmark.moshi_kotlin_reflective_string_fromJson[minified=true]             11,734,532  ns
AndroidBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=false]            12,930,158  ns
AndroidBenchmark.moshi_kotlin_reflective_string_fromJson[minified=false]            14,747,397  ns
```
