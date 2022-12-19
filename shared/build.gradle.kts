plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
    id("app.cash.sqldelight")
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val coroutinesVersion = "1.6.4"

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common:${findProperty("kotlin.version")}")
                api("org.jetbrains.kotlinx:kotlinx-serialization-core:${findProperty("kotlinx.serialization.version")}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("app.cash.sqldelight:runtime:${findProperty("sqlDelightVersion")}")
                implementation("app.cash.sqldelight:coroutines-extensions:${findProperty("sqlDelightVersion")}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${findProperty("kotlin.version")}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
                implementation("app.cash.sqldelight:android-driver:${findProperty("sqlDelightVersion")}")
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion") {
                    version {
                        strictly(coroutinesVersion)
                    }
                }
                implementation("app.cash.sqldelight:native-driver:${findProperty("sqlDelightVersion")}")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
//        val iosTest by creating {
//            dependsOn(commonTest)
//            iosX64Test.dependsOn(this)
//            iosArm64Test.dependsOn(this)
//            iosSimulatorArm64Test.dependsOn(this)
        //}
    }
}

android {
    namespace = "cat.alexgluque.sqliteerror"
    compileSdk = 33
    defaultConfig {
        minSdk = 33
        targetSdk = 33
    }
    lint {
        abortOnError = false
    }
}

sqldelight {
    database("AppDatabase") {
        packageName = "cat.alexgluque.sqliteerror"
        version = 1
        schemaOutputDirectory = file("src/commonMain/sqldelight/cat/alexgluque/sqliteerror")
    }
}