import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
    id("org.jetbrains.dokka") version "1.9.10"
}

tasks.dokkaHtml {
    outputDirectory.set(file("$rootDir/docs"))
}

android {
    namespace = "com.tieproost.fitnessapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tieproost.fitnessapp"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    defaultConfig {
        buildConfigField(
            "String",
            "X_APP_ID",
            gradleLocalProperties(rootDir).getProperty("X_APP_ID"),
        )
        buildConfigField(
            "String",
            "X_APP_KEY",
            gradleLocalProperties(rootDir).getProperty("X_APP_KEY"),
        )
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("androidx.compose.material3:material3-window-size-class-android:1.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // extra icons
    implementation("androidx.compose.material:material-icons-extended")

    // navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")

    // AsyncImage
    implementation("io.coil-kt:coil-compose:2.5.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // room
    val roomVersion = "2.5.0"
    implementation("androidx.room:room-runtime:$roomVersion")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$roomVersion")
    // To use Kotlin Symbol Processing (KSP)
    ksp("androidx.room:room-compiler:$roomVersion")

    // workers
    implementation("androidx.work:work-runtime-ktx:2.9.0")

    // Kotlin coroutines test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

    // navigation testing
    androidTestImplementation("androidx.navigation:navigation-testing:2.7.5")

    // Dokka
    dokkaPlugin("org.jetbrains.dokka:android-documentation-plugin:1.9.10")
}
