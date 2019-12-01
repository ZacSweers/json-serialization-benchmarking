## Current raw Android polymorphic benchmark results (lower score is better)

_Run on a 15" 2018 MacBook Pro. 32GB RAM, 2.9 GHz 6-Core Intel Core i9_

Grouped by serialization type (read, write, buffered, string):

```
Read (buffered)
AndroidBenchmark.moshi_autovalue_buffer_fromJson[minified=true]             5,705,469  ns
AndroidBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=true]        5,735,052  ns
AndroidBenchmark.gson_autovalue_buffer_fromJson[minified=true]              6,195,417  ns
AndroidBenchmark.gson_autovalue_buffer_fromJson[minified=false]             7,027,032  ns
AndroidBenchmark.moshi_autovalue_buffer_fromJson[minified=false]            7,641,979  ns
AndroidBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=false]       7,740,677  ns
AndroidBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=true]     8,100,000  ns
AndroidBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=false]   10,143,074  ns

Read (string)
AndroidBenchmark.gson_autovalue_string_fromJson[minified=true]              4,854,687  ns
AndroidBenchmark.gson_autovalue_string_fromJson[minified=false]             5,340,678  ns
AndroidBenchmark.gson_reflective_string_fromJson[minified=true]             5,678,593  ns
AndroidBenchmark.kserializer_string_fromJson[minified=true]                 5,772,345  ns
AndroidBenchmark.gson_reflective_string_fromJson[minified=false]            6,346,979  ns
AndroidBenchmark.kserializer_string_fromJson[minified=false]                6,430,939  ns
AndroidBenchmark.moshi_autovalue_string_fromJson[minified=true]             6,669,532  ns
AndroidBenchmark.moshi_kotlin_codegen_string_fromJson[minified=true]        6,673,698  ns
AndroidBenchmark.moshi_reflective_string_fromJson[minified=true]            8,197,866  ns
AndroidBenchmark.moshi_autovalue_string_fromJson[minified=false]            8,940,730  ns
AndroidBenchmark.moshi_kotlin_codegen_string_fromJson[minified=false]       8,981,979  ns
AndroidBenchmark.moshi_kotlin_reflective_string_fromJson[minified=true]     9,024,428  ns
AndroidBenchmark.moshi_reflective_string_fromJson[minified=false]          10,500,313  ns
AndroidBenchmark.moshi_kotlin_reflective_string_fromJson[minified=false]   11,272,241  ns

Write (buffered)
AndroidBenchmark.moshi_autovalue_buffer_toJson[minified=true]               3,760,470  ns
AndroidBenchmark.moshi_kotlin_codegen_buffer_toJson[minified=true]          3,825,365  ns
AndroidBenchmark.moshi_kotlin_reflective_buffer_toJson[minified=true]       7,219,272  ns
AndroidBenchmark.gson_autovalue_buffer_toJson[minified=true]              160,393,974  ns

Write (string)
AndroidBenchmark.kserializer_string_toJson[minified=true]                   5,368,125  ns
AndroidBenchmark.moshi_autovalue_string_toJson[minified=true]               5,413,751  ns
AndroidBenchmark.moshi_kotlin_codegen_string_toJson[minified=true]          5,442,292  ns
AndroidBenchmark.gson_autovalue_string_toJson[minified=true]                6,162,865  ns
AndroidBenchmark.moshi_reflective_string_toJson[minified=true]              6,316,824  ns
AndroidBenchmark.gson_reflective_string_toJson[minified=true]               7,916,772  ns
AndroidBenchmark.moshi_kotlin_reflective_string_toJson[minified=true]       8,733,439  ns
```

Grouped by library (interesting to see how reflection vs custom adapters affects perf within a library):

```
GSON
AndroidBenchmark.gson_autovalue_string_fromJson[minified=true]              4,854,687  ns
AndroidBenchmark.gson_autovalue_string_fromJson[minified=false]             5,340,678  ns
AndroidBenchmark.gson_reflective_string_fromJson[minified=true]             5,678,593  ns
AndroidBenchmark.gson_autovalue_string_toJson[minified=true]                6,162,865  ns
AndroidBenchmark.gson_autovalue_buffer_fromJson[minified=true]              6,195,417  ns
AndroidBenchmark.gson_reflective_string_fromJson[minified=false]            6,346,979  ns
AndroidBenchmark.gson_autovalue_buffer_fromJson[minified=false]             7,027,032  ns
AndroidBenchmark.gson_reflective_string_toJson[minified=true]               7,916,772  ns
AndroidBenchmark.gson_autovalue_buffer_toJson[minified=true]              160,393,974  ns

Kotlinx Serialization
AndroidBenchmark.kserializer_string_toJson[minified=true]                   5,368,125  ns
AndroidBenchmark.kserializer_string_fromJson[minified=true]                 5,772,345  ns
AndroidBenchmark.kserializer_string_fromJson[minified=false]                6,430,939  ns

Moshi
AndroidBenchmark.moshi_autovalue_buffer_toJson[minified=true]               3,760,470  ns
AndroidBenchmark.moshi_autovalue_string_toJson[minified=true]               5,413,751  ns
AndroidBenchmark.moshi_autovalue_buffer_fromJson[minified=true]             5,705,469  ns
AndroidBenchmark.moshi_reflective_string_toJson[minified=true]              6,316,824  ns
AndroidBenchmark.moshi_autovalue_string_fromJson[minified=true]             6,669,532  ns
AndroidBenchmark.moshi_autovalue_buffer_fromJson[minified=false]            7,641,979  ns
AndroidBenchmark.moshi_reflective_string_fromJson[minified=true]            8,197,866  ns
AndroidBenchmark.moshi_autovalue_string_fromJson[minified=false]            8,940,730  ns
AndroidBenchmark.moshi_reflective_string_fromJson[minified=false]          10,500,313  ns

Moshi Kotlin
AndroidBenchmark.moshi_kotlin_codegen_buffer_toJson[minified=true]          3,825,365  ns
AndroidBenchmark.moshi_kotlin_codegen_string_toJson[minified=true]          5,442,292  ns
AndroidBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=true]        5,735,052  ns
AndroidBenchmark.moshi_kotlin_codegen_string_fromJson[minified=true]        6,673,698  ns
AndroidBenchmark.moshi_kotlin_reflective_buffer_toJson[minified=true]       7,219,272  ns
AndroidBenchmark.moshi_kotlin_codegen_buffer_fromJson[minified=false]       7,740,677  ns
AndroidBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=true]     8,100,000  ns
AndroidBenchmark.moshi_kotlin_reflective_string_toJson[minified=true]       8,733,439  ns
AndroidBenchmark.moshi_kotlin_codegen_string_fromJson[minified=false]       8,981,979  ns
AndroidBenchmark.moshi_kotlin_reflective_string_fromJson[minified=true]     9,024,428  ns
AndroidBenchmark.moshi_kotlin_reflective_buffer_fromJson[minified=false]   10,143,074  ns
AndroidBenchmark.moshi_kotlin_reflective_string_fromJson[minified=false]   11,272,241  ns
```