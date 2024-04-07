plugins {
    kotlin("jvm")
    kotlin("plugin.allopen")
    application
    `maven-publish`
}

apply(from = "gradle/tasks/generateEvents.gradle.kts")

apply(from = "gradle/tasks/checkEvents.gradle.kts")

apply(from = "gradle/tasks/eventClassJavaDocChecker.gradle")

dependencies {
    api(project(":yde-impl"))

    // config.json
    api("io.github.realyusufismail:jconfig:" + properties["jconfigVersion"])

    // ws and https
    api("com.neovisionaries:nv-websocket-client:" + properties["nvWebsocketClientVersion"])
    api("com.codahale:xsalsa20poly1305:" + properties["xsalsa20poly1305Version"])

    // decode Opus + voice support
    api("club.minnced:opus-java-api:" + properties["opus-java-api"])
    // TODO: fix the version issue
    api("net.java.dev.jna:jna:5.14.0")
    implementation("org.apache.tika:tika-core:2.9.2")
    implementation("org.apache.tika:tika-parsers:2.9.2")

    // files to bytes
    api("commons-io:commons-io:" + properties["commonsIoVersion"])

    // guava
    api("com.google.guava:guava:" + properties["guavaVersion"])
    implementation("io.ktor:ktor-client-okhttp-jvm:3.0.0-beta-1")

    // test
    testImplementation("org.jetbrains.kotlin:kotlin-test:" + properties["kotlinTestVersion"])
    testImplementation("org.mockito:mockito-core:" + properties["mockitoCoreVersion"])
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("--enable-preview")
}

tasks {
    val compileKotlinTask = named<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>("compileKotlin")
    compileKotlinTask.configure { dependsOn("generateEvents") }
}

tasks.build {
    dependsOn(tasks.test) // run tests before building
}
