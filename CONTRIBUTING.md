Contributing to this repo
=========================

This project is first and foremost a simple benchmarking test project that I mostly use for periodic
comparisons of Moshi, Gson, and kotlinx-serialization. In the past it's compared other serializers,
but making this an exhaustive benchmarking suite of different tools is a non-goal. That said, I'm
not against PRs adding new tools as long as they fit the existing suite.

Do's
- If your PR modifies or adds a benchmark, please update the benchmark results. Be sure to include
your machine's tech specs.
- If adding a new benchmark, explain what value it's adding and why it's relevant.
- If modifying a benchmark, explain what issue it's resolving or benefit it's adding.
- If removing a benchmark, please open an issue first to discuss.
- Dependency updates are welcome!

Don'ts
- Add a new serialization tool without discussing in an issue first. If you want to demo one without
wanting to have it merged, that's welcome and just mark it as a draft PR and then close it after.
