object Versions {
    const val coreKtx = "1.8.0"
    const val appcompat = "1.6.1"
    const val material = "1.9.0"
    const val constraintlayout = "2.1.4"
    const val lifecycleLivedata = "2.6.1"
    const val lifecycleViewmodel = "2.6.1"
    const val legacy_support = "1.0.0"
    const val junit = "4.13.2"
    const val androidxJunit = "1.1.5"
    const val espressoCore = "3.5.1"
    const val coroutinesCore = "1.6.4"
    const val coroutinesAndroid = "1.6.4"
    const val play_services_location = "21.0.1"

    //ViewBinding
    const val viewbindingpropertydelegate = "1.5.9"

    //Koin
    const val koin = "3.4.2"
    const val koinAndroid = "3.4.2"

    //Glide
    const val glide = "4.12.0"
    const val glideCompiler = "4.12.0"

    //Dagger
    const val dagger = "2.46"
    const val dagger_android = "2.46"
    const val dagger_android_support = "2.46"
    const val dagger_compiler = "2.46"
    const val dagger_android_processor = "2.46"

    //Retrofit
    const val okhttp3 = "4.9.1"
    const val retrofit = "2.9.0"
    const val converter_gson = "2.9.0"
    const val adapter_rxjava3 = "2.9.0"

    //Cicerone
    const val cicerone = "7.1"

    //Room
    const val room_runtime = "2.3.0"
    const val room_rxjava3 = "2.3.0"
    const val room_compiler = "2.3.0"
    const val room_ktx = "2.3.0"
}

object Modules {
    const val repository = ":repository"
}

object ViewBindingProp {
    const val viewbindingpropertydelegate =
        "com.github.kirich1409:viewbindingpropertydelegate:${Versions.viewbindingpropertydelegate}"
}

object Koin {
    const val koin = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koinAndroid}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideCompiler}"
}

object Dagger {
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_android = "com.google.dagger:dagger-android:${Versions.dagger_android}"
    const val dagger_android_support = "com.google.dagger:dagger-android-support:${Versions.dagger_android_support}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger_compiler}"
    const val dagger_android_processor = "com.google.dagger:dagger-android-processor:${Versions.dagger_android_processor}"
}

object Retrofit {
    const val okhttp3 = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converter_gson}"
    const val adapter_rxjava3 = "com.squareup.retrofit2:adapter-rxjava3:${Versions.adapter_rxjava3}"
}

object Cicerone {
    const val cicerone = "com.github.terrakok:cicerone:${Versions.cicerone}"
}

object Room {
    const val room_runtime = "androidx.room:room-runtime:${Versions.room_runtime}"
    const val room_rxjava3 = "androidx.room:room-rxjava3:${Versions.room_rxjava3}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room_compiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room_ktx}"
}

object AndroidDep {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val lifecycleLivedata =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleLivedata}"
    const val lifecycleViewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewmodel}"
    const val legacy_support = "androidx.legacy:legacy-support-v4:${Versions.legacy_support}"
    const val junit = "junit:junit:${Versions.junit}"
    const val androidxJunit = "androidx.test.ext:junit:${Versions.androidxJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
    const val play_services_location =
        "com.google.android.gms:play-services-location:${Versions.play_services_location}"
}