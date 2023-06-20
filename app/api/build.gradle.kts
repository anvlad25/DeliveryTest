plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.api"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.api"
        minSdk = 31
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(project(mapOf("path" to ":app")))
    testImplementation(AndroidDep.junit)
    androidTestImplementation(AndroidDep.androidxJunit)
    androidTestImplementation(AndroidDep.espressoCore)

    //Retrofit
    implementation (Retrofit.okhttp3)
    implementation (Retrofit.retrofit)
    implementation (Retrofit.converter_gson)
    implementation (Retrofit.adapter_rxjava3)
}