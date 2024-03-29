plugins {
    id("com.yelp.codegen.plugin") version "1.4.1"
    kotlin("jvm")
    kotlin("kapt")
    id("com.github.ben-manes.versions") version "0.38.0"
}

tasks.jar {
    archiveName = "rest.jar"
}

dependencies {
    implementation(Dependencies.kotlin_stdlib)

    implementation(Dependencies.Moshi.moshi)
    implementation(Dependencies.Moshi.moshi_adapters)
    implementation(Dependencies.Moshi.moshi_kotlin)
    kapt(Dependencies.Moshi.moshi_codegen)

    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.retrofit_converter_moshi)

    implementation(Dependencies.okhttp)

    testImplementation(Dependencies.Testing.test_assertj)
    testImplementation(Dependencies.Testing.test_junit_api)
    testRuntimeOnly(Dependencies.Testing.test_junit_engine)
}

apply(from = "../publishing.gradle")
apply(from = "swagger.gradle")