- These benchmarks compare...
  - Gson, Moshi, kotlinx serialization
  - Reflective vs streaming APIs (via code gen)
  - Moshi's Kotlin support (reflective or code gen for streaming APIs)
- Note Kotlinx serialization only works via the standard compiler plugin
- "Minified" means the JSON was minified with whitespaces between keys and values removed.
  - Note this is only relevant for deserialization.
- AutoValue APIs are done via AutoValue models with the auto-value-gson/Moshi extensions, thus using generated adapters.
- Kotlin codegen generates Kotlin JsonAdapters.
- Reflection is done usually on a simple class due to limitations of instantiation. Moshi's reflection library is the exception (it can handle `data class` types)
- String serialization is a practical example, buffered input/output is likely more representative of how your stack works in terms of I/O

## Latest results TL;DR

* Fastest reader: Moshi
* Fastest writer: Moshi

Speed is not everything though! Different libraries come with different trade offs. That said, these *are* benchmarks.

## Running locally

To run:

`./gradlew jmh -Pjmh=<FileName to Run>`

You can use respective `*DataParser.kt` files to parse the raw results (starting with the first
benchmark line after the headers).
