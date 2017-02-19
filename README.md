Misc benchmarks for json serialization

This compares gson and moshi, reflective and streaming APIs. Streaming APIs are done via AutoValue models with the auto-value-gson/moshi extensions.

Current results:

(Run on a mid-2015 15" Macbook Pro. 2.8 GHz Intel Core i7, 16 GB 1600 MHz DDR3)

```
Benchmark                             Mode  Cnt     Score    Error  Units
SpeedTest.gson_reflective_fromJson   thrpt  200   774.555 ±  9.185  ops/s
SpeedTest.gson_reflective_toJson     thrpt  200   968.213 ±  8.266  ops/s
SpeedTest.gson_streaming_fromJson    thrpt  200   808.730 ± 10.991  ops/s
SpeedTest.gson_streaming_toJson      thrpt  200  1178.336 ± 13.461  ops/s
SpeedTest.moshi_reflective_fromJson  thrpt  200   837.154 ± 10.142  ops/s
SpeedTest.moshi_reflective_toJson    thrpt  200  1298.406 ± 17.830  ops/s
SpeedTest.moshi_streaming_fromJson   thrpt  200   846.998 ± 11.773  ops/s
SpeedTest.moshi_streaming_toJson     thrpt  200  1391.106 ± 16.983  ops/s
```

Rearranged with gson/moshi comparisons grouped:

```
Benchmark                             Mode  Cnt     Score    Error  Units
SpeedTest.gson_reflective_fromJson   thrpt  200   774.555 ±  9.185  ops/s
SpeedTest.moshi_reflective_fromJson  thrpt  200   837.154 ± 10.142  ops/s

SpeedTest.gson_streaming_fromJson    thrpt  200   808.730 ± 10.991  ops/s
SpeedTest.moshi_streaming_fromJson   thrpt  200   846.998 ± 11.773  ops/s

SpeedTest.gson_reflective_toJson     thrpt  200   968.213 ±  8.266  ops/s
SpeedTest.moshi_reflective_toJson    thrpt  200  1298.406 ± 17.830  ops/s

SpeedTest.gson_streaming_toJson      thrpt  200  1178.336 ± 13.461  ops/s
SpeedTest.moshi_streaming_toJson     thrpt  200  1391.106 ± 16.983  ops/s
```

Rearranged with reflective/streaming comparisons grouped (not as relevant, but interesting to see how streaming affects perf within a library):

```
Benchmark                             Mode  Cnt     Score    Error  Units
SpeedTest.gson_reflective_fromJson   thrpt  200   774.555 ±  9.185  ops/s
SpeedTest.gson_streaming_fromJson    thrpt  200   808.730 ± 10.991  ops/s

SpeedTest.moshi_reflective_fromJson  thrpt  200   837.154 ± 10.142  ops/s
SpeedTest.moshi_streaming_fromJson   thrpt  200   846.998 ± 11.773  ops/s

SpeedTest.gson_reflective_toJson     thrpt  200   968.213 ±  8.266  ops/s
SpeedTest.gson_streaming_toJson      thrpt  200  1178.336 ± 13.461  ops/s

SpeedTest.moshi_reflective_toJson    thrpt  200  1298.406 ± 17.830  ops/s
SpeedTest.moshi_streaming_toJson     thrpt  200  1391.106 ± 16.983  ops/s
```

To run:

`./gradlew jmh`
