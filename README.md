[![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Kotlin](https://img.shields.io/badge/kotlin-1.7.10-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![codecov](https://codecov.io/gh/RealYusufIsmail/YDWK/branch/master/graph/badge.svg?token=LKIA8T6N6J)](https://codecov.io/gh/RealYusufIsmail/YDWK)

# YDWK
A discord wrapper made in kotlin(Not related to YDW)

## Problems this wrapper solves
You might ask why should i use this wrapper when there is already a kotlin wrapper out there. Well, this wrapper is made to solve the following problems:
- This wrapper aims to be as simple and as fast as possible while still being easy to use and understand
- This is built to be similar to discord.js
- I will stick to the same naming as in the discord api for consistency

## In progress and to be done
- [ ] Create entities - in progress
- [ ] Handle events - In progress
- [ ] Handle rate limiting in websocket

## Future Features
- [ ] Handle slash commands

## Implemented
- [x] Handle Rest API
- [x] Connect to gateway
- [x] Parse json
- [x] Handle all op codes
- [x] Caching
- [x] Handle reconnect and resuming

## Getting started

Add the following to your `build.gradle`:

```gradle
dependencies {
    implementation("io.github.realyusufismail:ydwk:${project.version}")
}
```
To create a default bot, add the following to your main class:

```kotlin
fun main() {
    createDefaultBot("TOKEN")
}
```

A default bot has all the recommend gateway intents.
