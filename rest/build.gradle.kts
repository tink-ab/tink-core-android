plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("com.github.ben-manes.versions") version "0.38.0"
    id("com.yelp.codegen.plugin") version "1.4.1"
}

android {
    compileSdk = Versions.compileSdk
    buildToolsVersion = Versions.buildTools

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        buildConfigField("long", "VERSION_CODE", "${Version.code}")
        buildConfigField("String","VERSION_NAME","\"${Version.name}\"")
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

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
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
