## Current raw Android polymorphic benchmark results (lower score is better)

_Run on a 15" 2018 MacBook Pro. 32GB RAM, 2.9 GHz 6-Core Intel Core i9_

Grouped by serialization type (read, write):

```
Read
PolymorphicBenchmark.moshi_fromJson[minified=true,typeKeyLocation=first]     5,741,043  ns
PolymorphicBenchmark.moshi_fromJson[minified=false,typeKeyLocation=first]    7,726,407  ns
PolymorphicBenchmark.moshi_fromJson[minified=true,typeKeyLocation=last]      9,240,001  ns
PolymorphicBenchmark.gson_fromJson[minified=true,typeKeyLocation=last]       9,511,094  ns
PolymorphicBenchmark.gson_fromJson[minified=true,typeKeyLocation=first]      9,530,990  ns
PolymorphicBenchmark.gson_fromJson[minified=false,typeKeyLocation=first]    10,419,532  ns
PolymorphicBenchmark.gson_fromJson[minified=false,typeKeyLocation=last]     10,473,907  ns
PolymorphicBenchmark.moshi_fromJson[minified=false,typeKeyLocation=last]    14,063,751  ns

Write
PolymorphicBenchmark.moshi_toJson[minified=true,typeKeyLocation=first]       7,679,844  ns
PolymorphicBenchmark.moshi_toJson[minified=true,typeKeyLocation=last]        7,688,855  ns
PolymorphicBenchmark.gson_toJson[minified=true,typeKeyLocation=last]       163,759,807  ns
PolymorphicBenchmark.gson_toJson[minified=true,typeKeyLocation=first]      165,742,933  ns
```