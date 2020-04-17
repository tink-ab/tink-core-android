plugins {
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