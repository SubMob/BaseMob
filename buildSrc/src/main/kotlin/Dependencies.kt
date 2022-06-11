/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

object Dependencies {
    object Android {
        const val ANDROID_MATERIAL =
            "com.google.android.material:material:${Versions.ANDROID_MATERIAL}"
        const val NAVIGATION = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    }

    object ClassPaths {
        const val ANDROID_GRADLE_PLUGIN =
            "com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_PLUGIN}"
        const val KOTLIN_GRADLE_PLUGIN =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    }

    object Plugins {
        const val ANDROID_LIB = "com.android.library"
        const val ANDROID = "android"
        const val DEPENDENCY_UPDATES = "com.github.ben-manes.versions"
        const val BUILD_HEALTH = "com.autonomousapps.dependency-analysis"
    }
}
