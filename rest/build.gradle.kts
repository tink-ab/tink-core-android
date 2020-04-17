plugins {
    id("com.yelp.codegen.plugin") version "1.4.1"
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation(Dependencies.kotlin_stdlib)

    implementation(Dependencies.Moshi.moshi)
    implementation(Dependencies.Moshi.moshi_adapters)
    implementation(Dependencies.Moshi.moshi_kotlin)
    kapt(Dependencies.Moshi.moshi_codegen)

    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.retrofit_converter_moshi)

    implementation(Dependencies.three_ten_abp)
    implementation(Dependencies.okhttp)
}

apply(from = "../publishing.gradle")
apply(from = "swagger.gradle")
