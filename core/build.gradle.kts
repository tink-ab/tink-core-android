plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.github.ben-manes.versions") version "0.38.0"
}

android {
    compileSdk = Versions.compileSdk
    buildToolsVersion = Versions.buildTools

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk

        buildConfigField("String", "libraryVersion", "\"${Version.name}\"")
        
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

}

dependencies {
    api(project(":service"))
    api(project(":models"))

    implementation(Dependencies.kotlin_stdlib)

    implementation(Dependencies.Dagger.core)
    implementation(Dependencies.Dagger.dagger_android)
    implementation(Dependencies.Dagger.dagger_android_support)
    kapt(Dependencies.Dagger.dagger_android_processor)
    kapt(Dependencies.Dagger.dagger_android_compiler)

    implementation(Dependencies.rxjava)

    implementation(Dependencies.Coroutines.core)

    testImplementation(Dependencies.Testing.test_assertj)
    testImplementation(Dependencies.Testing.test_junit_api)
    testRuntimeOnly(Dependencies.Testing.test_junit_engine)
    testImplementation(Dependencies.Testing.test_mockk)
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