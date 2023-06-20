plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.example.deliverytest"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.deliverytest"
        minSdk = 31
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        buildFeatures.viewBinding = true
    }
}

dependencies {
    implementation(AndroidDep.coreKtx)
    implementation(AndroidDep.appcompat)
    implementation(AndroidDep.material)
    implementation(AndroidDep.constraintlayout)
    implementation(AndroidDep.lifecycleLivedata)
    implementation(AndroidDep.lifecycleViewmodel)
    implementation(AndroidDep.legacy_support)
    implementation(AndroidDep.play_services_location)
    testImplementation(AndroidDep.junit)
    androidTestImplementation(AndroidDep.androidxJunit)
    androidTestImplementation(AndroidDep.espressoCore)

    //Modules
    implementation(project(Modules.repository))

    //ViewBinding
    implementation(ViewBindingProp.viewbindingpropertydelegate)

    //Koin
    implementation(Koin.koin)
    implementation(Koin.koinAndroid)

    //Glide
    implementation(Glide.glide)
    kapt(Glide.glideCompiler)

    //Dagger
    //implementation Dagger.dagger
    //implementation Dagger.dagger_android
    //implementation Dagger.dagger_android_support
    //kapt Dagger.dagger_compiler
    //kapt Dagger.dagger_android_processor

    //Retrofit
    implementation(Retrofit.okhttp3)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.converter_gson)
    implementation(Retrofit.adapter_rxjava3)

    //Cicerone
    implementation(Cicerone.cicerone)

    //Room
    implementation(Room.room_runtime)
    implementation(Room.room_rxjava3)
    implementation(Room.room_ktx)
    kapt(Room.room_compiler)
}