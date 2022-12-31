/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.dependencyUpdates)
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        libs.classpaths.apply {
            classpath(androidGradlePlugin)
            classpath(kotlinGradlePlugin)
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
