plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt) // Enable KAPT for annotation processing
}

android {
    namespace = "com.example.data"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.data"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    viewBinding.isEnabled = true
}

dependencies {
    // Core libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Room (Database)
    implementation(libs.androidx.room.runtime) // Room runtime library
    kapt(libs.androidx.room.compiler) // Annotation processor for Room

    // Lifecycle components
    implementation(libs.androidx.lifecycle.livedata) // LiveData support
    implementation(libs.androidx.lifecycle.viewmodel) // ViewModel support

    // Preferences
    implementation(libs.androidx.preference.ktx) // Preference library

    // Gson for parsing JSON
    implementation(libs.gson)

    // ConstraintLayout
    implementation(libs.androidx.constraintlayout)

    // RecyclerView
    implementation(libs.androidx.recyclerview)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

// Ensure Room schema export (optional but recommended for managing Room migrations)
kapt {
    arguments {
        arg("room.schemaLocation", "$projectDir/schemas")
        arg("room.incremental", "true") // Optional for better build performance
    }
}

