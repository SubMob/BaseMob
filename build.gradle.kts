/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    libs.plugins.apply {
        alias(kotlinAndroid).apply(false)
        alias(androidLibrary).apply(false)
    }
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        libs.classpaths.apply {
            classpath(kotlinGradlePlugin)
        }
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            allWarningsAsErrors = true
        }
    }
}
