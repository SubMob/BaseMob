/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
@file:Suppress("DEPRECATION") // vanniktech AndroidSingleVariantLibrary(Boolean, Boolean) ctor — still valid

import com.vanniktech.maven.publish.AndroidSingleVariantLibrary

plugins {
    libs.plugins.apply {
        alias(androidLibrary)
        alias(mavenPublish)
    }
}

android {
    ProjectSettings.apply {
        namespace = "com.github.submob.basemob"
        compileSdk = COMPILE_SDK_VERSION
        defaultConfig.minSdk = MIN_SDK_VERSION

        compileOptions {
            sourceCompatibility = JAVA_VERSION
            targetCompatibility = JAVA_VERSION
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    libs.android.apply {
        implementation(navigation)
        implementation(androidMaterial)
    }
}

mavenPublishing {
    // Coordinates (GROUP + POM_ARTIFACT_ID), POM, host and auto-release come from gradle.properties.
    // Publishes the release AAR + sources + javadoc. Central Portal requires signed artifacts;
    // keys are provided in CI via ORG_GRADLE_PROJECT_* env.
    configure(AndroidSingleVariantLibrary(variant = "release", sourcesJar = true, publishJavadocJar = true))
    signAllPublications()
}
