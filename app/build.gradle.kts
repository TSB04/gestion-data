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
    // If you're using ViewModel, uncomment the line below:
    // implementation(libs.androidx.lifecycle.viewModel)

    // Preferences
    implementation(libs.androidx.preference.ktx) // Preference library

    // Gson for parsing JSON
    //implementation "com.google.code.gson:gson:2.8.8"
    implementation(libs.gson)

    //consrtraint layout
    implementation(libs.androidx.constraintlayout)

    // RecyclerView
    implementation(libs.androidx.recyclerview)

    // google android material
    implementation(libs.material)


    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

// Ensure Room schema export (optional but recommended for managing Room migrations)
kapt {
    arguments {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}
