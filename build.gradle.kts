/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            allWarningsAsErrors = true
        }
    }
}
