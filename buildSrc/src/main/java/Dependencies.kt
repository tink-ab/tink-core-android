object Versions {
    const val compileSdk = 29
    const val buildTools = "29.0.1"
    const val minSdk = 21
    const val targetSdk = 29

    const val kotlin = "1.4.10"
    const val dagger = "2.16"
    const val coroutines = "1.4.2"
    const val retrofit = "2.6.2"
    const val rxjava = "2.1.9"

    const val moshi = "1.11.0"

    const val junit = "5.6.2"
}

object Dependencies {

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object Dagger {
        private const val base_url = "com.google.dagger"
        const val core = "$base_url:dagger-android-support:${Versions.dagger}"
        const val dagger_android = "$base_url:dagger-android:${Versions.dagger}"
        const val dagger_android_support = "$base_url:dagger-android-support:${Versions.dagger}"
        const val dagger_android_processor = "$base_url:dagger-android-processor:${Versions.dagger}"
        const val dagger_android_compiler = "$base_url:dagger-compiler:${Versions.dagger}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofit_adapter_rxjava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
        const val retrofit_converter_gson =  "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val retrofit_converter_moshi =  "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    }

    object Moshi {
        const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
        const val moshi_codegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}" // Kapt codegen
        const val moshi_kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}" // Reflect
        const val moshi_adapters = "com.squareup.moshi:moshi-adapters:${Versions.moshi}" // Json adapters
    }

    object Testing {

        const val test_junit_api = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
        const val test_junit_engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"
        const val test_junit_params = "org.junit.jupiter:junit-jupiter-params:${Versions.junit}"
        const val test_mockk = "io.mockk:mockk:1.8.7"
        const val test_assertj = "org.assertj:assertj-core:3.11.1"
    }

    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    const val three_ten_abp = "com.jakewharton.threetenabp:threetenabp:1.2.1"
    const val timber = "com.jakewharton.timber:timber:4.7.1"

    const val ktlint = "com.pinterest:ktlint:0.33.0"

    const val okhttp = "com.squareup.okhttp3:okhttp:4.4.0"
    const val conscrypt = "org.conscrypt:conscrypt-android:2.4.0"
}
