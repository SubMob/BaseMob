/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id(Dependencies.Plugins.DEPENDENCY_UPDATES) version Versions.DEPENDENCY_UPDATES
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        with(Dependencies.ClassPaths) {
            classpath(ANDROID_GRADLE_PLUGIN)
            classpath(KOTLIN_GRADLE_PLUGIN)
        }
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.withType<DependencyUpdatesTask> {
    gradleReleaseChannel = "current"
    rejectVersionIf { candidate.version.isNonStable() }
}

fun String.isNonStable(): Boolean {
    val stableKeyword = listOf(
        "RELEASE",
        "FINAL",
        "GA"
    ).any {
        this.toUpperCase(java.util.Locale.ROOT).contains(it)
    }

    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(this)
    return isStable.not()
}
