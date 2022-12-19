plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "cat.alexgluque.sqliteerror.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "cat.alexgluque.sqliteerror.android"
        minSdk = 33
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:${findProperty("android.compose.version")}")
    implementation("androidx.compose.ui:ui-tooling:${findProperty("android.compose.version")}")
    implementation("androidx.compose.ui:ui-tooling-preview:${findProperty("android.compose.version")}")
    implementation("androidx.compose.foundation:foundation:${findProperty("android.compose.version")}")
    implementation("androidx.compose.material:material:${findProperty("android.compose.version")}")
    implementation("androidx.activity:activity-compose:1.6.1")
}