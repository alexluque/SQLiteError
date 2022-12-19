buildscript {

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${findProperty("kotlin.version")}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${findProperty("kotlin.version")}")

        classpath("com.android.tools.build:gradle:7.3.1")

        classpath("app.cash.sqldelight:gradle-plugin:${findProperty("sqlDelightVersion")}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jcenter.bintray.com")
        maven(url = "https://kotlin.bintray.com/kotlinx/")
        maven(url = "https://dl.bintray.com/timrs2998/maven")
        maven(url = "https://jitpack.io")
    }
}