plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("com.github.ben-manes.versions") version "0.38.0"
}

androidExtensions {
    isExperimental = true
}

android {
    compileSdk = Versions.compileSdk
    buildToolsVersion = Versions.buildTools

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        consumerProguardFile("tink-core-proguard-rules.txt")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(Dependencies.kotlin_stdlib)
    api(Dependencies.three_ten_abp)

    testImplementation(Dependencies.Testing.test_assertj)
    testImplementation(Dependencies.Testing.test_junit_api)
    testRuntimeOnly(Dependencies.Testing.test_junit_engine)
    testImplementation(Dependencies.Testing.test_mockk)
}

apply(from = "../publishing.gradle")
