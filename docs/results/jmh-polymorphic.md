## Current raw JMH polymorphic results (higher score is better)

_Run on a 15" 2018 MacBook Pro. 32GB RAM, 2.9 GHz 6-Core Intel Core i9_

Grouped by serialization type (read, write):

```
Benchmark                       (minified)  (typeKeyLocation)   Mode  Cnt     Score    Error  Units
PolymorphicTest.moshi_fromJson        true              first  thrpt   25  1537.384 ± 17.531  ops/s
PolymorphicTest.moshi_fromJson       false              first  thrpt   25  1085.072 ±  8.002  ops/s
PolymorphicTest.moshi_fromJson        true               last  thrpt   25   955.086 ± 40.841  ops/s
PolymorphicTest.gson_fromJson         true              first  thrpt   25   735.502 ± 23.243  ops/s
PolymorphicTest.gson_fromJson         true               last  thrpt   25   727.168 ± 18.258  ops/s
PolymorphicTest.gson_fromJson        false              first  thrpt   25   713.038 ±  3.854  ops/s
PolymorphicTest.gson_fromJson        false               last  thrpt   25   696.026 ± 13.284  ops/s
PolymorphicTest.moshi_fromJson       false               last  thrpt   25   606.661 ±  8.519  ops/s

PolymorphicTest.moshi_toJson          true              first  thrpt   25  1726.830 ± 14.515  ops/s
PolymorphicTest.moshi_toJson          true               last  thrpt   25  1703.664 ± 16.337  ops/s
PolymorphicTest.gson_toJson           true              first  thrpt   25   683.573 ± 14.634  ops/s
PolymorphicTest.gson_toJson           true               last  thrpt   25   658.894 ± 11.774  ops/s
```