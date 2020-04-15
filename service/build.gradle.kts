plugins {
    id("com.android.library")
    id("com.yelp.codegen.plugin") version "1.4.1"
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)

    defaultConfig {
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = Version.code
        versionName = Version.name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    api(project(":models"))

    implementation(Dependencies.kotlin_stdlib)
    implementation(Dependencies.three_ten_abp)

    implementation("com.squareup.moshi:moshi:1.9.2")
    implementation("com.squareup.moshi:moshi-adapters:1.9.2")

    implementation(Dependencies.Androidx.lifecycle_extensions)

    implementation(Dependencies.Dagger.core)
    implementation(Dependencies.Dagger.dagger_android)
    implementation(Dependencies.Dagger.dagger_android_support)
    kapt(Dependencies.Dagger.dagger_android_processor)
    kapt(Dependencies.Dagger.dagger_android_compiler)

    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Coroutines.android)

    implementation(Dependencies.okhttp)
    implementation(Dependencies.conscrypt)
    implementation(Dependencies.Moshi.moshi)
    implementation(Dependencies.Moshi.moshi_kotlin)
    kapt(Dependencies.Moshi.moshi_codegen)

    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.retrofit_adapter_rxjava)
    implementation(Dependencies.Retrofit.retrofit_converter_gson)
    implementation(Dependencies.Retrofit.retrofit_converter_moshi)
    implementation(Dependencies.rxjava)

    implementation(Dependencies.timber)

    testImplementation(Dependencies.Testing.test_assertj)
    testImplementation(Dependencies.Testing.test_junit_api)
    testRuntimeOnly(Dependencies.Testing.test_junit_engine)
}

// Workaround for https://youtrack.jetbrains.com/issue/KT-32804
// See thread: https://github.com/google/dagger/issues/1449
if (project.hasProperty("kapt")) {
    // Reference for 'kapt' DSL: https://kotlinlang.org/docs/reference/kapt.html#java-compiler-options
    kapt {
        // we expect this closure to run over a org.jetbrains.kotlin.gradle.plugin.KaptExtension
        javacOptions {
            option("-source", "8")
            option("-target", "8")
        }
    }
}

apply(from = "../publishing.gradle")
apply(from = "swagger.gradle")

