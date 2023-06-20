plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.example.api"
    compileSdk = 33

    defaultConfig {
        minSdk = 31
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        externalNativeBuild {
            cmake {
                cppFlags("")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    externalNativeBuild {
        cmake {
            path("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(AndroidDep.coreKtx)
    implementation(AndroidDep.appcompat)
    implementation(AndroidDep.material)
    testImplementation(AndroidDep.junit)
    androidTestImplementation(AndroidDep.androidxJunit)
    androidTestImplementation(AndroidDep.espressoCore)

    //Retrofit
    implementation(Retrofit.okhttp3)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.converter_gson)
    implementation(Retrofit.adapter_rxjava3)

    //Room
    implementation(Room.room_runtime)
    implementation(Room.room_rxjava3)
    implementation(Room.room_ktx)
    kapt(Room.room_compiler)
}