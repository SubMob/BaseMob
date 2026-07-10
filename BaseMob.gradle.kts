/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    libs.plugins.apply {
        alias(androidLibrary).apply(false)
        alias(mavenPublish).apply(false)
    }
}

allprojects {
    project.dependencyLocking.lockAllConfigurations()

    // Group + POM metadata + Central Portal config come from gradle.properties (read by
    // com.vanniktech.maven.publish). Only the version is dynamic (git commit count).
    version = ProjectSettings.getVersionName(project)

    repositories {
        google()
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        compilerOptions {
            allWarningsAsErrors = true
        }
    }
}
